## Descrição do módulo
Avaliação de expressões matemáticas só conhecidas em tempo de execução, mas posteriormente reutilizada inúmeras vezes, possivelmente ao longo de meses e anos. Neste caso, a expectativa é que a expressão possa ser compilada para bytecodes, em tempo de execução, oferecendo um ganho em desempenho. Nesta proposta, a biblioteca Javassist deve ser utilizada.

### Requisitos (preliminares):
  - R1 Uma expressão matemática deve ser recebida como uma sequência de caracteres e ter seu valor avaliado, em conformidade com os valores das variáveis empregadas. 
  - R2 Caso não seja passado os valores das variáveis, a expressão não será avaliada.
  - R3 A expressão matemática está restrita a valores numéricos (ou tipo _Double_ em Java). 
  - R4 A expressão matemática recebida como entrada deve ser convertida para _bytecodes_ por meio do uso da biblioteca [Javassist](https://www.javassist.org/). Observação: embora seja uma decisão típica de _design_, esta é uma restrição imposta ao desenvolvimento do módulo.
  - R5 A expressão deve possuir um identificador para ser armazenada e chamada diversas vezes sem precisar de uma nova compilação
  
 ### Anotações gerais (_design_)
  #### Versão 1
  - Converter em bytecodes com a biblioteca Javassist
  - Será armazenado em um arquivo .class
  - Receberá os valores das variáveis e irá acessar o .class criado com ajuda do Java Reflection
  - Map < String, Double >
  
  #### Versão 2
  - Converter em bytecodes com a biblioteca Javassist
  - Será armazenado em um arquivo .class
  - Receberá os valores das variáveis e irá acessar o .class criado com ajuda do Java Reflection
  - _avalie_ é o método da interface _ExpEvaluate_, que receberá uma String _exp_ e um dicionário contendo String e Double de valores de variáveis das expressões a serem recebidas pelo usuário.
  - O método _avalie_ terá um _id_ sempre que uma nova expressão for colocada, para ser guardada e usada diversas vezes sem precisar de uma nova compilação
  - Expressao _carregar_ que recebe como parâmetros (String id)
  - Expressao _define_ que recebe como parâmetros (String _exp_)
  - avalie(Expressao exp, Map < String, Double > valores)

#### Versão 3 (melhor explicada)
  - Converter em bytecodes com a biblioteca Javassist
  - Os bytecodes serão armazenados em um arquivo .class
  - Receberá os valores das variáveis e irá acessar o .class criado com ajuda do Java Reflection
  - _avalie_ é o método da interface _ExpEvaluate_, que receberá uma String _exp_ e um dicionário contendo String e Double de valores de variáveis das expressões a serem recebidas pelo usuário.
  - O método _avalie_ terá um _id_ sempre que uma nova expressão for colocada, para ser guardada e usada diversas vezes sem precisar de uma nova compilação
  - Expressao _carregar_ irá receber como parâmetro uma String _id_
  - Expressao _define_ que irá receber como parâmetro uma String _exp_
  - Método Avalie consiste em:
    - _avalie_(Expressao exp, Map < String, Double > valores)
  - Caso seja passado um valor inválido para variável, uma excessão deverá ser gerada.
  
#### Versão 4
  - Converter a expressão matemática em bytecodes. 
  - Dada uma expressão, é criado um método específico para ela em uma dada classe (no caso de exemplo, a classe se chama _eval_). O corpo do método é:
    `````java
    public double eval (double x) { return (" + args[0] + ") ; }
    
  A fórmula que será avaliada é passada como um parâmetro args, e se torna parte do método.
  - Esse código gerado em bytecodes para a expressão já está compilado, e serão depositados em um arquivo .class.
  - Essa compilação é realizada pelo Javassist. **A rigor, o Javassist não realiza compilação, mas apenas facilita a interação com bytecodes. Um aspecto positivo é que esta biblioteca possui uma interface de alto nível.**
  - Veja o que encontrei em uma consulta no Google: https://javaranch.com/journal/200711/creating_java_classes_runtime_expression_evaluation.html. Se o que está no artigo citado é útil, você terá que investigar. Se afirmativo, seu _design_ pode se beneficiar de forma significativa da estratégia adotada. Isto porque, até este ponto, não há exatamente um _design_, mas "passos", ou "partes". 
  - Os bytecodes gerados, que serão armazenados em um arquivo .class, poderá seguir o seguinte [exemplo](https://stackoverflow.com/questions/6219829/method-to-dynamically-load-java-class-files) para "carregar" uma classe disponível em um diretório cujo nome é conhecido. Ou seja, usar este link para se basear na carga de uma classe gerada.
  - Após a criação da classe, o uso da biblioteca Javassist é concluído, e é possível utilizar a nova classe como qualquer outra classe carregada dinamicamente.
  - Com a classe carregada (veja passo anterior), pode ser criado uma instância dela. Uma forma de fazer isso é:
  ```` java
  Object obj = clazz.newInstance();
  ````
  - A instância criada (passo anterior), digamos _instancia_, deverá receber uma mensagem. A princípio chamada _instancia.avalie_, onde esse método tem assinatura _double avalie(Map < String, Double >)_
  ```` java
  public class NomeDefinidoPossivelmenteEmTempoDeExecucao {
  
    public static double x;
    public static double y;

    public static double avalie() {
       return x + y;
    }
  }
  ````
  ou 
  ```` java
  public class NomeDefinidoPossivelmenteEmTempoDeExecucao {
  
    public double avalie(Map<String, Double> contexto) {
       // Para cada variável v empregada na expressão 
      // Recuperar o valor de v, ou seja, contexto.get("v")
      // Atribuir o valor recuperado à variável correspondente
      // x = context.get("x")
      // A expressão recebida para ser avaliada  retornada abaixo
      return x + y; 
     }
  }
  ````
  ou utilizando reflexão
  ```` java
  Class[] formalParams = new Class[] { double.class };
  Method meth = clazz.getDeclaredMethod("eval", formalParams);
  ````
  - Para chamar o método, com um parâmetro e imprimi-lo pode seguir a sugestão abaixo, ou ver passo a passo detalhado em [link](https://javaranch.com/journal/200711/creating_java_classes_runtime_expression_evaluation.html):
  ```` java
  Object[] actualParams = new Object[] { new Double(17) };
  double result = ((Double) meth.invoke(obj, actualParams)).doubleValue();
  System.out.println(result);
  ````
  - Caso seja passado um valor inválido para variável, uma excessão deverá ser gerada.

