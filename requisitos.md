## Descrição do módulo
Avaliação de expressões matemáticas só conhecidas em tempo de execução, mas posteriormente reutilizada inúmeras vezes, possivelmente ao longo de meses e anos. Neste caso, a expectativa é que a expressão possa ser compilada para bytecodes, em tempo de execução, oferecendo um ganho em desempenho. Nesta proposta, a biblioteca Javassist deve ser utilizada.

### Requisitos (preliminares):
  - R1 Uma expressão matemática deve ser recebida como uma sequência de caracteres e ter seu valor avaliado, em conformidade com os valores das variáveis empregadas. 
  - R2 Caso não seja passado os valores das variáveis, não será avaliado.
  - R3 A expressão matemática está restrita a valores numéricos (ou tipo _Double_ em Java). 
  - R4 A expressão matemática recebida como entrada deve ser convertida para _bytecodes_ por meio do uso da biblioteca [Javassist](https://www.javassist.org/). Observação: embora seja uma decisão típica de _design_, esta é uma restrição imposta ao desenvolvimento do módulo.
  
 ### Anotações gerais (_design_)
  - Converter em bytecodes com a biblioteca Javassist
  - Será armazenado em um arquivo .class
  - Receberá os valores das variáveis e irá acessar o .class criado com ajuda do Java Reflection
  - Map < String, Double >
  
