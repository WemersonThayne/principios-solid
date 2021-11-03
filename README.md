
#Princípios S.O.L.I.D

  

> SOLID é um acrônimo criado por Michael Feathers, após observar que cinco princípios da orientação a objetos e design de código — Criados por Robert C. Martin (a.k.a. Uncle Bob) e abordados no artigo The Principles of OOD — poderiam se encaixar nesta palavra.

  

- [S]ingle Responsibility Principle (Princípio da Responsabilidade Única)

- [O]pen/Closed Principle (Princípio do Aberto/Fechado)

- [L]iskov Substitution Principle (Princípio da Substituição de Liskov)

- [I]nterface Segregation Principle (Princípio da Segregação de Interfaces)

- [D]ependency Inversion Principle (Princípio da Inversão de Dependências)

  

Esses princípios ajudam o programador a escrever códigos mais limpos separando responsabilidades, diminuindo acoplamentos, facilitando na refatoração e estimulando o reaproveitamento do código.

  

1. ### Princípio da Responsabilidade Única (SRP)

  

- Esse primeiro princípio diz que _“uma classe deve ter apenas um motivo para mudar”,_ ou seja_,_ deve ter uma única responsabilidade. Basicamente, esse princípio trata especificamente a **coesão**.

- A coesão é definida como a afinidade funcional dos elementos de um módulo. Se refere ao relacionamento que os membros desse módulo possuem, se possuem uma relação mais direta e importante. Dessa forma, quanto mais bem definido o que sua classe faz, mais coesa ela é.

  

> A class FuncionarioGoldClass ele tem várias responsabilidades: calcula o salário do funcionário e o salva no banco de dados.

  

- Imagine o seguinte cenário: Por exemplo, ao evoluir esse sistema, se quisermos persistir outra entidade desse modelo, como um Produto, um Pedido, vamos ter que ficar replicando o trecho de código do salva(). Ou até mesmo, se quisermos calcular o salário de outros funcionários de outros cargos, que tem descontos de imposto diferentes, seriamos obrigados a criar métodos diferentes de cálculo dentro da mesma classe.

- Podemos quebrar essas responsabilidades da classe funcionário em mais classes:
	- ConnectionDAO (para gerenciar as conexões com o banco);
	- FuncionarioDAO (para implementar todos os métodos de persistência específicos do funcionário);
	- Enum Cargo (para guardar as regras de cálculo por cargo);
	- RegraDeCalculo (que é uma interface que possui o método de cálculo) e para cada cenário de desconto no imposto.

  #### A violação do Single Responsibility Principle pode gerar alguns problemas, sendo eles:

- Falta de coesão:  Uma classe não deve assumir responsabilidades que não são suas;
- Alto acoplamento: Mais responsabilidades geram um maior nível de dependências, deixando o sistema engessado e frágil para alterações;
- Dificuldades na implementação de testes automatizados: É difícil de “mockar” esse tipo de classe;
- Dificuldades para reaproveitar o código;


2. ### Princípio do Aberto/Fechado (OCP)

  > Princípio Aberto-Fechado — “as entidades de software (classes, módulos, funções etc.) devem ser abertas para ampliação, mas fechadas para modificação”.

- De forma mais detalhada, diz que podemos estender o comportamento de uma classe, quando for necessário, por meio de herança, interface e composição, mas não podemos permitir a abertura dessa classe para fazer pequenas modificações.

- Para exemplificar o cenário de OCP, temos uma classe FolhaPagamento precisa verificar o funcionário para aplicar a regra de negócio correta na hora do pagamento. Supondo que a empresa cresceu e resolveu trabalhar com funcionários PJ, obviamente seria necessário modificar essa classe! Sendo assim, estaríamos quebrando o princípio Open-Closed do SOLID.

- O que devemos fazer é concentrar nos aspectos essências do contexto, abstraindo-os para uma interface. Se as abstrações são bem definidas, logo o software estará aberto para extensão.

 #### Aplicando OCP na prática

- Voltando para o exemplo, podemos concluir que o contexto que estamos lidando é a remuneração dos contratos de trabalho, aplicando as premissas de se isolar o comportamento extensível atrás de uma interface, podemos criar uma interface com o nome Remuneravel contendo o método remuneracao(), e fazer com que nossas classes de contrato de trabalho implementem essa interface. Além disso, iremos colocar as regras de calculo de remuneração para suas respectivas classes, dentro do método remuneracao(), fazendo com que a classe FolhaDePagamento dependa somente da interface Remuneravel que iremos criar.

- Open-Closed Principle também é base para o padrão de projeto Strategy

> Sua principal vantagem é a facilidade na adição de novos requisitos, diminuindo as chances de introduzir novos bugs ou bugs de menor expressão pois o novo comportamento fica isolado, e o que estava funcionando provavelmente continuara funcionando.

3. ### LSP— Liskov Substitution Principle:

Princípio da substituição de Liskov — Uma classe derivada deve ser substituível por sua classe base.

- O princípio da substituição de Liskov foi introduzido por Barbara Liskov em sua conferência “Data abstraction” em 1987. A definição formal de Liskov diz que:

> Se para cada objeto o1 do tipo S há um objeto o2 do tipo T de forma que, para todos os programas P definidos em termos de T, o comportamento de P é inalterado quando o1 é substituído por o2 então S é um subtipo de T

> Se S é um subtipo de T, então os objetos do tipo T, em um programa, podem ser substituídos pelos objetos de tipo S sem que seja necessário alterar as propriedades deste programa. — Wikipedia.

Em outra palavras: 
Diz que “Os subtipos devem ser substituíveis pelos seus tipos base”, e que as classes/tipos base podem ser substituídas por qualquer uma das suas subclasses, ponderando sobre os cuidados para usar a herança no seu projeto de software.

Para exemplicar, analisaremos as classes ContaCorrenteComum e ContaSalario. A ContaCorrenteComum representa, dentro do nosso contexto simplificado, uma conta de banco qualquer. Tem os métodos deposita(), getSaldo(), saca() e rende().

> A única diferença entra as classe é o método rende(), no cenário em que se tentar acessar o método  de todas as contas do Banco usando um loop, por exemplo, e uma delas é uma ContaSalario, pronto, nossa aplicação não funciona, porque para qualquer conta salário uma exceção é lançada nesse método. Logo essa abordagem é uma má prática. 

Para resolver esse problema vamos criar uma classe que fazer a gestão e controle de todas as contas do banco 

> “O Uncle Bob explica que o LSP é o princípio capacitador do Princípio do Aberto/Fechado, pois a possibilidade de substituição de subtipos permite que um módulo, expresso em termos de um tipo base, possa ser extensível sem modificações.“

4. ### Princípio da Segregação de Interfaces (ISP)

> “Muitas interfaces específicas são melhores do que uma interface geral”. 

Esse princípio trata da coesão em interfaces, da construção de módulos enxutos, ou seja, com poucos comportamentos. Interfaces que possuem muitos comportamentos são difíceis de manter e evoluir, e devem ser evitadas.

Para entender esse principio vamos usar a classe Funcionario.java e temos dois cargos, que vão estender essa classe Funcionario: o Vendedor e o Desenvolvedor.

Observando o que a classe Funcionario possui um comportamento que não faz sentido para o cargo Desenvolvedor: getComissao(). O correto seria que o salário do desenvolvedor é calculado com base nas horas trabalhadas e contratadas.

Refatorando o código para quebrar esses comportamentos em duas interfaces: Comissionavel e Convencional. Assim, o Funcionario passa a implementar a interface Convencional, fazendo com que a classe Desenvolvedor nem precise existir, já que o Desenvolvedor é um Funcionario com regime Convencional. Da mesma forma, a classe Vendedor passa a implementar a interface Comissionavel, que agora terá como comportamento o método getComissao(), que é específico desse tipo de Funcionario.

5. ### Princípio da Inversão de Dependências (DIP)

> “Depender de abstrações e não de classes concretas”.
 Uncle Bob quebra a definição desse princípio em dois sub-itens:
 - “Módulos de alto nível não devem depender de módulos de baixo nível.”
 - “As abstrações não devem depender de detalhes. Os detalhes devem depender das abstrações.”


“No contexto da programação orientada a objetos, é comum que as pessoas confundam a Inversão de Dependência com a Injeção de Dependência, porém são coisas distintas, mas que relacionam entre si com um proposito em comum, deixar o código desacoplado.”

> Importante: Inversão de Dependência não é igual a Injeção de Dependência, fique ciente disso! A Inversão de 		Dependência é um princípio (Conceito) e a Injeção de Dependência é um padrão de projeto (Design Pattern).


“A ideia de se aplicar os princípios em projetos de software é tirar proveito dos benefícios do uso correto da orientação a objetos, evitando problemas como: falta de padronização do código, duplicação (lembra do “Don’t repeat yourself”?), dificuldade de isolar funcionalidades que podem ser comuns para vários pontos do código e dificuldade de manutenção (código muito frágil, em que uma modificação pode quebrar uma funcionalidade já testada e funcional)”.

6. ### Referências
 - http://butunclebob.com/ArticleS.UncleBob.PrinciplesOfOod
 - https://medium.com/backticks-tildes/the-s-o-l-i-d-principles-in-pictures-b34ce2f1e898
 - https://medium.com/desenvolvendo-com-paixao o-que-%C3%A9-solid-o-guia-completo-para-voc%C3%AA-entender-os-5-princ%C3%ADpios-da-poo-2b937b3fc530
 - https://mari-azevedo.medium.com princ%C3%ADpios-s-o-l-i-d-o-que-s%C3%A3o-e-porque-projetos-devem-utiliz%C3%A1-los-bf496b82b299
