# TrackPet - Animais perdidos #

Requisitos do componente que será feito o design
> Você sugere que estes requisitos sejam implementados em um único componente, pela afirmação. Eu diria que é precipitada.
> O texto abaixo faz parte do domínio do problema. Um componente faz parte da solução. O simples mapeamento um para um talvez não 
> seja adequado. É possível que tenhamos vários componentes ou módulos para implementar estes requisitos. Veja seção "Design (ideias)" 
> abaixo.

#### RF01
Como _DONO DE UM ANIMAL_, desejo _CADASTRAR OS DADOS DO MEU ANIMAL PERDIDO_, para que _ALGUÉM POSSA RECONHECÊ-LO_.
Os dados obrigatórios são: foto, espécie, nome, sexo do animal, dia que desapareceu, onde desapareceu e informações de contato.

#### RF02
Como _USUÁRIO_, desejo _CADASTRAR OS DADOS DO ANIMAL QUE EU ENCONTREI_, para que _ALGUÉM POSSA RECONHECÊ-LO_. 
Os dados obrigatórios são: foto, sexo do animal, dia que foi encontrado, onde foi encontrado e informações de contato.

#### RF03
Como _DONO DE ANIMAL PERDIDO_, desejo que _TENHA UMA ABA PARA ANIMAIS ENCONTRADOS_, para tornar a busca filtrada apenas para animais que foram encontrados por outros usuários.

#### RF04
Como _PESSOA QUE ENCONTROU O ANIMAL_, desejo que _TENHA UMA ABA PARA ANIMAIS PERDIDOS_, para tornar a busca filtrada apenas para animais que foram encontrados por outros usuários.


## Design (ideias)
Eu não sei se estou empregando os termos corretos, não conheço o domínio.

### Módulo para cadastro de animais perdidos. 
Permitir o cadastro de um animal perdido e a busca neste cadastro para atender demais requisitos funcionais especificados como estórias de usuário (acima). 

O _design_, análise preliminar neste momento, deve incluir o _design_ dos dados (modelo de dados) necessário para atender os requisitos e o _design_ (diagrama de classes indicando as classes e os métodos, e as relações entre as classes) do software que, de fato, irá interagir com um SGBD (estou supondo) para atender as necessidades da aplicação. 


