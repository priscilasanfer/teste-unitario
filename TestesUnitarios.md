# Introdução a testes automatizados

### *01. Começando com testes de unidade*

*P*: Qual a ordem correta dos parâmetros do assertEquals() (e de todos os outros métodos similares) da classe Assert?  
*R*: A ordem é sempre (esperado, calculado).
Apesar de não fazer diferença nenhuma (afinal, esperamos que os dois números sejam iguais), é importante manter essa ordem.
Quando o teste falha, o JUnit usa esses valores para exibir uma mensagem de erro amigável. 
Por exemplo, expected 10, but was 20. Ou seja, esperava 10, mas veio 20. Se invertêssemos os valores, a mensagem ficaria errada!

*P*: Para que serve o terceiro parâmetro do assertEquals(esperado, calculado, 0.00001)?
Veja que ele é recomendado. Tire esse parâmetro do seu código e veja o que acontece.  
*R:* Como double tem limites de precisão, a versão mais nova do JUnit pede para você passar o "tamanho da precisão aceitável".
No caso, estamos passando 0.00001. Ou seja, a diferença entre o esperado e o calculado pode ser de até 0.00001, 
que o JUnit entenderá como valor dentro da precisão aceitável.
Números de ponto flutuante (float e double), ao contrário de números inteiros, raramente representam números exatos, 
mas sim números dentro de um determinado limite de precisão.
Além disso, alguns números de ponto flutuante são representados como dízimas periódicas quando convertidos para binário. 
Por isso, pode ocorrer ainda algum arredondamento no dígito menos significativo.

*P:* Em sua opinião, quais são as vantagens do teste de unidade? Você vê alguma desvantagem também?  
*R:* Testes automatizados de unidade nos trazem diversas vantagens.
Nesse momento, as vantagens que são fáceis de ver:
1) Um teste de unidade executa muito rápido. Veja que ele gasta apenas alguns milissegundos para ser executado. 
   Imagine o tempo que um humano levaria.

2) Um humano pode eventualmente executar um teste incorreto (montar o cenário ou validar a saída errada, por exemplo). 
   A máquina nunca fará isso. A partir do momento que você escreveu o teste, ela sempre vai executar o mesmo teste.

3) É muito mais divertido escrever um teste automatizado do que testar manualmente.
   Existem mais! Mas vamos estudá-las ao longo do curso!
   
*P:* Um ponto muito comum, levantado por estudantes que estão aprendendo testes automatizados, 
é: "hoje eu escrevo 100 linhas por dia; com testes, escreverei 50 linhas de código de produção e 50 linhas de código de testes. 
Ou seja, serei mais improdutivo".  
O que você acha dessa afirmação? Somos menos produtivos quando escrevemos testes de unidade?  
*R:* Depende da sua definição de produtividade. 
Se produtividade significa linhas de código de produção escritas por dia, talvez você seja menos produtivo.   
Agora, se sua definição é algo como "linhas de código escritas com qualidade", então, muito provavelmente, você será mais produtivo com testes.  
É difícil garantir qualidade de um sistema sem testes automatizados, por todos os motivos já citados ao longo deste capítulo.  
Além disso, alguns estudos mostram que programadores que escrevem testes, a longo prazo, são mais produtivos do que os 
que não escrevem e até gastam menos tempo depurando o código!   
Isso faz sentido: imagine um desenvolvedor que testa manualmente.   
Quantas vezes por dia ele executa o MESMO teste?   
O programador que automatiza o teste gasta seu tempo apenas 1 vez: escrevendo-o. 
Depois, executar o teste é rápido e barato.   
Ou seja, ao longo do tempo, escrever testes automatizados vai fazer você economizar tempo.

### *02. Testando o que realmente é necessário*

*P:* Ao testar uma lista, quantas verificações (quantidade de asserts) geralmente fazemos?  
*R:* 1 + N, onde o primeiro é para garantir o tamanho da lista, e depois N asserts para garantir o conteúdo interno completo dessa lista
Precisamos sempre garantir todo o conteúdo da lista retornada. 
Veja que só garantir o tamanho da lista não nos ajuda muito, afinal a lista pode ter o tamanho certo, mas ter o conteúdo inválido.

*P:* É sempre interessante tratar de casos especiais no teste. Por exemplo, tratamos o caso da lista com um elemento separado do caso da lista com vários elementos.   
Isso faz sentido? Por que? Consegue ver outros casos como esse, que merecem atenção especial?  
*R:* Tratar o caso da lista com um elemento separado do caso da lista com vários elementos faz todo sentido. É muito comum, durante a implementação, pensarmos direto no caso complicado, e esquecermos de casos simples, mas que acontecem. Por esse motivo é importante testarmos esses casos.
Quando lidamos com listas, por exemplo, é sempre interessante tratarmos o caso da lista cheia, da lista com apenas um elemento, e da lista vazia.  
Se estamos lidando com algoritmos cuja ordem é importante, precisamos testar ordem crescente, decrescente, e randômica.  
Um código que apresente um if(salario>=2000), por exemplo, precisa de três diferentes testes:
- Um cenário com salário menor do que 2000
- Um cenário com salário maior do que 2000
- Um cenário com salário igual a 2000
Afinal, quem nunca confundiu um > por um >= ?

O grande desafio da área dos testadores é encontrar todos as classes de equivalência; tarefa essa que não é fácil !  

*P:* Durante o vídeo, implementamos a regra de negócio para cálculo dos três maiores lances, e quebramos a regra de negócio anterior.  
Será que sem a bateria de testes perceberíamos esse erro? Como? Nesse momento, quais são as vantagens de uma bateria de testes automatizados?  
*R:* A bateria de testes automatizados nos ajuda a encontrar problemas na nossa implementação de forma muito rápida: 
basta clicarmos em um botão, e alguns segundos depois sabemos se nossa implementação realmente funciona ou não.  
Sem uma bateria de testes, dificilmente pegaríamos esse bug em tempo de desenvolvimento.   
Testes manuais são caros e, por esse motivo, o desenvolvedor comumente testa apenas a funcionalidade atual, deixando de 
lado os testes de regressão (ou seja, testes para garantir que o resto do sistema ainda continua funcionando mesmo após a implementação da nova funcionalidade).  

*P:* Em sua opinião, qual a parte mais difícil na hora de escrever um teste de unidade?  
*R:* Desenvolvedores que estão aprendendo a testar geralmente sentem dificuldades no momento de levantar e escrever cenários para o teste.
Lembre-se que um teste automatizado é muito parecido com um teste manual. 
Do mesmo jeito que você pensa no cenário de um teste manual (por exemplo, visitar a página de cadastro, preencher o campo CPF com "123", 
clicar no botão, e etc), você faz no automatizado.
Foque-se na classe que você está testando. 
Pense sobre o que você espera dela.   
Como ela deve funcionar? Se você passar tais parâmetros para ela, como ela deve reagir?  
Continue praticando!  

### *03. Praticando Test-Driven Development (TDD)*

*P:* O termo dado para a ideia de dar sempre passos pequenos e escrever código simples o tempo todo é conhecido por baby steps.  
O que você achou dessa ideia? Quais as vantagens e as desvantagens que você acha que essa metodologia pode trazer?  
*R:* Dar passos pequenos pode ser muito benéfico para a sua implementação.   
Começar pelo teste mais simples nos possibilita evoluir o código aos poucos (geralmente gostamos de começar pelo caso mais difícil, o que pode dificultar).  
Além disso, ao implementar códigos simples, aumentamos a facilidade de manutenção do nosso código.    
Afinal, código simples é muito mais fácil de manter do que códigos complexos.  
Muitas vezes nós programadores escrevemos códigos complicados desnecessariamente.   
TDD nos lembra o tempo todo de ser simples.  
Muitas pessoas, no entanto, dizem que tomar passos de bebê o tempo todo pode ser contraproducente.   
Segundo o próprio autor da prática, Kent Beck, você deve tomar passos pequenos sempre que sua "confiança sobre o código" estiver baixa.   
Se você está trabalhando em um trecho de código e está bastante confiante sobre ele, você pode dar passos um pouco maiores.   
Mas cuidado, passos grandes não devem ser a regra, mas sim a exceção.

*P:* Um ponto que muitos praticantes de TDD questionam é a necessidade de ver o teste falhar antes de implementar o código de produção.  
O que você acha? Você vê alguma vantagem em ver o teste falhar antes de implementar?    
*R:* Devemos ver o teste falhar, pois é uma das maneiras que temos para garantir que nosso teste foi implementado corretamente.   
Afinal, um teste automatizado é código, e podemos implementá-lo incorretamente.  
Ao ver que o teste que esperamos falha, realmente falha, temos a primeira garantia de que implementamos ele de maneira correta.   
Imagine se o teste que esperamos que falhe, na prática não falha. O que aconteceu?  
Para completar o "teste" do teste, podemos escrever o código de produção mais simples que o faz passar.   
Dessa forma, garantimos que o teste fica vermelho quando deve, e fica verde quando deve.    


*P:* Agora que você já conhece a prática, você acha que devemos praticar TDD 100% do tempo?  
*R:* Não. Como toda prática de engenharia de software, ela deve ser usada no momento certo.   
TDD faz muito sentido ao implementar novas funcionalidades, ao corrigir bugs, ao trabalhar em códigos complexos, etc.  
Mas às vezes não precisamos seguir o fluxo ideal da prática de TDD.   
Por exemplo, às vezes queremos só escrever um conjunto de testes que faltaram para determinada funcionalidade.   
Nesse momento, não faríamos TDD, mas sim escreveríamos testes.  
Em códigos extremamente simples, talvez a prática de TDD não seja necessária.   
Mas lembre-se: cuidado para não achar que "tudo é simples", e nunca praticar TDD.  


*P:* O que é TDD? Descreva com suas próprias palavras.  
TDD é uma prática de desenvolvimento de software na qual o programador escreve um teste antes do código.   
TDD nos traz segurança e feedback constante sobre a qualidade do nosso código.  
É uma boa prática para todo desenvolvedor de software!  

*P:* Em sua opinião, o que ganhamos ao manter nosso código de teste sempre legível e fácil de dar manutenção?  
E o que perdemos quando isso não acontece?  
*R:*  Nosso código de teste é altamente acoplado ao nosso código de produção. 
Isso significa que uma mudança no código de produção pode impactar profundamente em nosso código de testes.  
Se não cuidarmos dos nossos testes, uma simples mudança pode impactar em MUITAS mudanças no código de testes.  
É por isso que neste capítulo discutimos métodos auxiliares e test data builders.   
Todas elas são maneiras para fazer com que nosso código de testes evolua mais facilmente.

### *05. Testando exceções*
*P:* O que você achou do Hamcrest? Ele realmente facilita a leitura dos testes?
*R:* Apesar do desenvolvedor precisar conhecer os matchers dele para que consiga utilizar bem o framework, os testes que fazem uso de Hamcrest geralmente são mais fáceis de ler.  
Por esse motivo, o uso de Hamcrest é muito comum entre os desenvolvedores, e é inclusive encorajado.  