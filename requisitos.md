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
  ##### Requisitos 
   - R1 Uma expressão matemática deve ser recebida como uma sequência de caracteres e ter seu valor avaliado, em conformidade com os valores das variáveis empregadas. 
  - R2 Caso não seja passado os valores das variáveis, a expressão não será avaliada.
  - R3 A expressão matemática está restrita a valores numéricos (ou tipo _Double_ em Java). 
  - R4 A expressão matemática recebida como entrada deve ser convertida para _bytecodes_ por meio do uso da biblioteca [Javassist](https://www.javassist.org/). Observação: embora seja uma decisão típica de _design_, esta é uma restrição imposta ao desenvolvimento do módulo.
  - R5 A expressão deve possuir um identificador para ser armazenada e chamada diversas vezes sem precisar de uma nova compilação
  
  ##### Visão geral do design
   A visão geral do design desse problema consiste em, primeiramente criar uma classe principal que irá receber a expressão (do tipo String) gerada em tempo de execução. Caso essa expressão nunca tenha sido utilizada anteriormente, é feito a conversão da expressão em bytecodes (é importante que as expressões sejam guardadas em disco após serem convertidas, para poderem ser utilizadas posteriormente) e um método específico para ela é criado em uma classe, dentro de um arquivo .class. E em caso da expressão já ter sido utilizada anteriormente, usar o Java Reflection para criar a instância e chamar o método que calcula a expressão. A biblioteca Javassist entra como um facilitador na hora de interagir com os bytecodes gerados. Após criar o método que calcula a expressão, é necessário chamar o mesmo com parâmetros, e imprimir o resultado. 
    
 ##### Design detalhado
  ###### D01
  - Inicialmente deve-se criar uma classe Main, onde os dados da expressão recebida posteriormente será executado.  
  ###### D02
  - Converter a expressão matemática em bytecodes. 
  ###### D03
  - Dada uma expressão, é criado um método específico para ela na classe Main criada inicialmente (no caso de exemplo, o método se chama _avalie_). O corpo do método é:
    `````java
    public double avalie (double x) { return (" + args[0] + ") ; }  
    
  - A fórmula que será avaliada é passada como um parâmetro args, e se torna parte do método.  
  ###### D04
  - Esse código gerado em bytecodes para a expressão já está compilado, e serão depositados em um arquivo .class.  
  ###### D05
  - Essa compilação e a interação com os bytecodes é facilitada pelo uso do Javassist. Um aspecto positivo é que esta biblioteca possui uma interface de alto nível.  
  ###### D06
  - Os bytecodes gerados, que deverão ser armazenados em um arquivo .class, poderá seguir o seguinte [exemplo](https://stackoverflow.com/questions/6219829/method-to-dynamically-load-java-class-files) para "carregar" uma classe disponível em um diretório cujo nome é conhecido. Ou seja, usar este link para se basear na carga de uma classe gerada.  
  ###### D07
  - Após a criação da classe, o uso da biblioteca Javassist é concluído, e é possível utilizar a nova classe como qualquer outra classe carregada dinamicamente.  
  ###### D08
  - Com a classe carregada (veja passo anterior), pode ser criado uma instância dela. Uma forma de fazer isso é utilizando o Java Reflection.  
    ```` java
    Object obj = clazz.newInstance();
    ````  
  ###### D09
  - Deve-se ressaltar que o Java Reflection também facilita manipularmos o método _avalie_ como feito no trecho abaixo:
    ```` java
    Class[] formalParams = new Class[] { double.class };
    Method meth = clazz.getDeclaredMethod("avalie", formalParams);
    ````  
  ###### D10
  - A instância criada (passo anterior), digamos _instancia_, deverá receber uma mensagem. A princípio chamada _instancia.avalie_, onde esse método tem assinatura _double avalie(Map < String, Double >)_  
    ```` java
    public class Main {
  
      public static double x;
      public static double y;

      public static double avalie() {
         return x + y;
      }
    }
    ````  
    ou  
    ```` java
    public class Main {
  
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
  ###### D11
  - Após utilizar da instânciação da classe, deve utilizar do Java Reflection para manipular o método _avalie_   
  
    ````java
    Class[] formalParams = new Class[] { double.class };
    Method meth = clazz.getDeclaredMethod("avalie", formalParams);
    ````  
  ###### D12
  - Para chamar o método, com um parâmetro e imprimi-lo pode seguir a sugestão abaixo, ou ver passo a passo detalhado em [link](https://javaranch.com/journal/200711/creating_java_classes_runtime_expression_evaluation.html):  
    ```` java
    Object[] actualParams = new Object[] { new Double(17) };
    double result = ((Double) meth.invoke(obj, actualParams)).doubleValue();
    System.out.println(result);
    ````  
  ###### D13
  - Caso seja passado um valor inválido para variável, uma excessão deverá ser gerada.  


