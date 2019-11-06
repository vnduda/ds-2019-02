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
  - Converter a expressão matemática em bytecodes. Dada uma expressão, é criado um método específico para ela em uma dada classe.
  - Esse código gerado para a expressão seria compilado, gerando os bytecodes, que serão depositados em um arquivo .class.
  - Essa compilação é realizada pelo Javassist
  - Os bytecodes gerados, que serão armazenados em um arquivo .class, poderá seguir o seguinte [exemplo](https://stackoverflow.com/questions/6219829/method-to-dynamically-load-java-class-files) para "carregar" uma classe disponível em um diretório cujo nome é conhecido.
  - Com a classe carregada (veja passo anterior), pode ser criado uma instância dela, da seguinte [forma]().
  - A instância criada (passo anterior), diagamos _instancia_, deverá receber uma mensagem. A princípio chamada _instancia.avalie_, onde esse método tem assinatura _double avalie(Map < String, Double >)_
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
  
  - É necessário, a partir de uma dada expressão, obter uma lista dos nomes das variáveis (List).
  - Caso seja passado um valor inválido para variável, uma excessão deverá ser gerada.
