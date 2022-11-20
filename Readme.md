
# Execucao
1. gerar o léxico  (gera Yylex.java ou arquivo especificado no %class)
> jflex <arq.flex>   OU
> java -jar JFlex.jar <arq.flex>

2. gerar o sintático  (gera Parser.java)
> byaccj -tv -J <arq.y>     OU
> ./yacc.llinux -tv -J <arq.y>

3. compilar código java
> javac Parser.java

4. testar:
>  java Parser    OU
> java Parser <teste.txt>

# Testes de Verificação

ID | Descrição | Resultado 
---|:---:|---
Caso1 | função escrita errado e retorno void | erro de **função não declarada** e indicativo de que função void não pode ser retornada.
Caso2 | tipo do argumento passado errado | erro de **divergência de tipo**: variável do tipo bool passada em uma função que espera inteiro.
Caso3 | declaração de variáveis com mesmo nome em escopos diferentes | **não deve ocorrer erro** se 'k' for buscado primeiro no escopo corrente: variável 'k' é tipo bool no escopo global, porém, é inteiro dentro da função.
Caso4 | número de argumentos passado errado | erro indicando que **função espera 2 argumentos, mas recebeu 1**.
Caso5 | uso de variável não declarada | erro indicando que **variável 'l' não foi encontrada** (deve buscar no escopo interno e global)
Caso6 | uso de um variável interna de uma função em outra | erro indicando que a **variável 'i' não foi encontrada.**
Caso7 | função simples que retorna inteiro mas especifica retorno booleano | erro que a função declarada **esperava retorno bool e recebeu int**. Também segundo erro com outro chamada onde uma variável inteira aguarda retorno, mas da **erro na atribuição já que o retorno esperado da função é um bool**.