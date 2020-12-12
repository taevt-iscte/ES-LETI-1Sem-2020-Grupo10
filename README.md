# ES-LETI-1Sem-2020-Grupo10

João Polónio, 87367 //--//
Tiago Torres, 87353 //--//
José Raposo, 87183  //--//

## O que está na frente do código.

Exceltify, aplicação desenvolvida no âmbito da cadeira de Engenharia do Software, ao receber um ficheiro de excel com dados relativos a parâmetros lidos de um ou mais métodos
especificos, bem como a sua avaliação por parte de duas ferramentas avaliativas (iPlasma e PMD), decompondo o mesmo em blocos de informação a serem interpretados e reavaliados pela
mesma, de modo a verificar a qualidade de avaliação das ferramentas. Para além disso, disponibiliza ao utilizador a capacidade de abrir o excel numa janela limpa e simples, bem
como a de múltiplas maneiras de apresentar os valores finais de avaliação. A mais sofisticada funcionalidade desta aplicação permite ainda ao utilizador desenvolver as suas 
próprias regras avaliativas com as métricas existentes a fim de poder devolver uma avaliação das mesmas, funcionando por isso como uma ferramenta altamente customizável pelo 
utilizador.

## Problemas encontrados.

Ao princípio, o uso de JavaFX no desenvolvimento do ambiente gráfico mostrou-se ser altamente benéfico na construção do código, tanto a nivel de organização de código, como também
na complexidade do mesmo. No entanto, após a conclusão do do código, deparámo-nos com uma dimensão de problemas derivados de incompatibilidade para com o JavaFX, tal como conversado com o professor. Podemos explicitar a complexidade do problema com o seguinte exemplo: para podermos correr os testes unitários temos de eliminar o module-info.java, porém isso impossibilita-nos de testar a GUI e outras funcionalidades diretamente derivadas da mesma, por exemplo o import excel, já que a mesma precisa do module-info.java para funcionar.

## Notas.
Para correr e verificar o código dos testes, estes encontram-se no caminho src/test/java, tal como apresentado nas aulas práticas. Para verificar o Javadoc, este encontra-se na pasta Javadoc. Para verificar os code smells, os ficheiros txt resultantes do JDeodorant encontram-se na pasta "Code Smells". Para verificar os resultados (print screen) dos testes unitários, estes encontram-se na pasta "JUnit Test Results".
