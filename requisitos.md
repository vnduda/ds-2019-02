## Descrição trabalho
- Avaliação de expressões matemáticas só conhecidas em tempo de execução, mas posteriormente reutilizada inúmeras vezes, possivelmente ao longo de meses e anos. Neste caso, a expectativa é que a expressão possa ser compilada para bytecodes, em tempo de execução, oferecendo um ganho em desempenho. Nesta proposta, a biblioteca Javassist deve ser utilizada.

- Requisitos:
  - Receber uma String contendo a expressão matemática
  - Converter em bytecodes com a biblioteca Javassist
  - Será armazenado em um arquivo .class
  - Receberá os valores das variáveis e irá acessar o .class criado com ajuda do Java Reflection
  
  
