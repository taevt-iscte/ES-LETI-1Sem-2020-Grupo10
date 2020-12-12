# ES-LETI-1Sem-2020-Grupo10

João Polónio, 87367 //--//
Tiago Torres, 87353 //--//
José Raposo, 87183  //--//

### O que está na frente do código.

Exceltify, aplicação desenvolvida no âmbito da cadeira de Engenharia do Software, ao receber um ficheiro de excel com dados relativos a parâmetros lidos de um ou mais métodos
especificos, bem como a sua avaliação por parte de duas ferramentas avaliativas (iPlasma e PMD), decompondo o mesmo em blocos de informação a serem interpretados e reavaliados pela
mesma, de modo a verificar a qualidade de avaliação das ferramentas. Para além disso, disponibiliza ao utilizador a capacidade de abrir o excel numa janela limpa e simples, bem
como a de múltiplas maneiras de apresentar os valores finais de avaliação. A mais sofisticada funcionalidade desta aplicação permite ainda ao utilizador desenvolver as suas 
próprias regras avaliativas com as métricas existentes a fim de poder devolver uma avaliação das mesmas, funcionando por isso como uma ferramenta altamente customizável pelo 
utilizador.

### Problemas encontrados.

Ao princípio, o uso de JavaFX no desenvolvimento do ambiente gráfico mostrou-se ser altamente benéfico na construção do código, tanto a nivel de organização de código, como também
na complexidade do mesmo. No entanto, após a conclusão do do código, deparámo-nos com uma dimensão de problemas derivados de incompatibilidade para com o JavaFX, começou na geração
do Javadoc que mostrou-se ser mais complexo que o esperado mas, mais preocupante ainda, foi nos testes unitários, que acabou por ser uma fonte absoluta de desperdício de tempo para
simplesmente poder conseguir criar testes, quanto mais ainda corrê-los. Com uma sensibilidade absurda quanto às dependências a serem utilizadas que não causassem perturbios para 
com o JavaFX, chegámos à infeliz conclusão de que para ser possivel correr os testes utilizando um "test suite", era necessário tornar toda a aplicação inutilizada (retirando o 
ficheiro module-info.java), o que nos parece algo absurdo. Dito isto, as provas de enviadas da cobertura dos testes unitários foram realizadas com a aplicação sem funcionamento.
