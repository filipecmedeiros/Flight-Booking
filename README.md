# Flight-Booking


# Trabalho de Prática de Programação – Projeto II

# Venda de passagens aéreas

**Formato de entrega** ​: Ambiente Classroom do Curso de Prática de Programação
**Data da entrega** ​: 15/10/
A Brasil Linhas Aéreas é uma nova empresa de aviação que está atuando no
mercado brasileiro. Você foi contratado para criar o sistema de vendas de
passagens da empresa. O sistema, além de ser responsável pela venda de
passagens, manterá também um cadastro de clientes e um cadastro de vôos.
Desta forma, o sistema a ser desenvolvido deverá ter três módulos: um módulo de
clientes, um módulo de vôos e um módulo de venda de passagens.
(a) **Módulo de Clientes** - Este módulo será composto pelos sub-programas que
irão manipular o cadastro de clientes da empresa. O módulo terá as seguintes
funcionalidades:
(1)Cadastrar um cliente. O cadastro de clientes deve conter os seguintes
dados: cpf, nome, telefone de contato e e-mail. ​ **OBS** ​: Não podem existir
dois clientes com o mesmo cpf. O cadastro de clientes deve ser ordenado
por ordem alfabética crescente de nomes.
(2)Alterar dados de um cliente. Recebe como parâmetro um cpf, faz uma
busca e, caso o cliente exista, permite a alteração de seu telefone e/ou
e-mail.
(3)Exibir os dados de um cliente. Recebe como parâmetro um cpf, faz uma
busca e, caso o cliente exista, exibe nome, telefone e e-mail.
(4)Remover um cliente do cadastro. Recebe como parâmetro um cpf, faz uma
busca e, caso o cliente exista, remove o cliente. ​ **IMPORTANTE** ​: Só é
permitida a remoção de um cliente se ele não tiver reserva ativa em
nenhum vôo. A remoção de um cliente implica na remoção de todo o seu
histórico de passagens.
(5)Função buscar cliente: recebe como entrada o cpf do cliente e retorna como
saída uma referência para o nó onde o cliente se encontra. Caso não
encontre, retorna null.
**OBS1** ​: O programa deverá fazer a validação de ​ **TODOS** os dados informados pelo
usuário:
(i) O CPF deverá ser validado segundo as regras reais de
validação de CPF;


(ii) Os nomes devem ser validados para só conter letras e
espaços. Os nomes válidos devem passar por um processo
de “limpeza” que retire os espaços em excesso;
(iii) Os telefones devem conter apenas dígitos numéricos, no
mínimo, 10 dígitos (DDD+número);
(iv) O e-mail deve conter, obrigatoriamente, UM arroba (@)
**OBS2** ​: O cadastro de clientes da empresa deverá ser implementado, em memória,
através de uma lista encadeada. O referido cadastro deverá também ser mantido
em arquivo. No início do programa, os dados no arquivo de clientes devem ser
carregados para a lista. No final da execução do programa, os dados da lista
devem ser gravados no arquivo, sobrescrevendo-o.
(b) **Módulo de Vôos** - Este módulo será composto pelos sub-programas que irão
manipular o cadastro de vôos da empresa. O módulo terá as seguintes
funcionalidades:
(1)Cadastrar um vôo. O cadastro de vôos deve conter os seguintes dados:
código do vôo, origem, destino, data de partida, horário de partida,
quantidade de poltronas existentes, quantidade de poltronas disponíveis (no
instante do cadastro é igual a quantidade de poltronas existentes), mapa
das poltronas, valor da passagem e o status do vôo (ativo ou efetivado).
**OBS1** ​: Não podem existir dois vôos com o mesmo código. ​ **OBS2** ​: Por
simplicidade, defina que todos os vôos têm 36 poltronas (6 fileiras de 6
poltronas cada) e que as poltronas de uma fileira são identificadas pelas
letras de A a F. (A e F – janela, C e D – corredor, B e E – meio). ​ **OBS3** ​: Crie
uma tabela com as origens e os destinos possíveis para que o usuário
escolha entre um deles.
(2)Procurar por um vôo. Recebe como parâmetros uma origem, um destino e
uma data, faz uma busca e exibe o código, horário e quantidade de
poltronas disponíveis de todos os vôos com as características solicitadas.
**OBS** ​: Só exibir vôos com poltronas disponíveis e que ainda não ocorreram.
(3)Alterar valor da passagem. Recebe como parâmetro um código de vôo, faz
uma busca e, caso o vôo exista e não tenha ocorrido ainda, permite a
alteração do valor da passagem.
(4)Cancelar um vôo. Recebe como parâmetro um código de vôo, faz uma
busca e, caso o vôo exista e não tenha ocorrido ainda, remove o vôo.


**IMPORTANTE** ​: Só é possível remover um vôo que esteja com todas as
suas poltronas disponíveis.
(5)Função consultar vôo: recebe como entrada o código e retorna como saída
uma referência para o nó onde vôo se encontra. Caso não encontre, retorna
null.
**OBS1** ​: O programa deverá fazer a validação de ​ **TODOS** os dados informados pelo
usuário:
(i) Os códigos dos vôos devem ser validados para conter
exatamente 7 caracteres: os três primeiros devem ser letras e
os quatro últimos devem ser números.
(ii)A data da partida deve ser uma data válida posterior ao dia
atual.
**OBS2** ​: O cadastro de vôos da empresa deverá ser implementado, em memória,
através de uma lista encadeada. O referido cadastro deverá também ser mantido
em arquivo. No início do programa, os dados no arquivo de vôos devem ser
carregados para a lista. No final da execução do programa, os dados da lista
devem ser gravados no arquivo, sobrescrevendo-o.
**OBS3** ​: Ao copiar os dados do arquivo para a lista, alterar para “efetivado” o status
de todos os vôos cuja data seja anterior à data atual.
(c) **Módulo de Controle de Vendas de Passagens** – Este módulo será composto
pelos sub-programas que irão manipular o cadastro de vendas da empresa. O
cadastro de passagens vendidas deve conter os seguintes dados: o código da
reserva, o cliente que comprou a passagem, o vôo, o número da poltrona
reservada e status da reserva (ativa, efetivada ou cancelada). O módulo terá
as seguintes funcionalidades:
(1)Vender passagem. Este procedimento deverá receber como parâmetros o
cpf do cliente e o código do vôo. Para realizar uma venda de passagem é
necessário verificar se o código é de um vôo cadastrado, se o cpf é de um
cliente cadastrado e se no vôo solicitado existe poltrona disponível. O
cliente deverá escolher entre uma das poltronas disponíveis. Efetuada a
venda, o registro do vôo (quantidade de poltronas disponíveis e mapa das
poltronas) deve ser atualizado, deve ser gerado automaticamente um
código de reserva e a venda deve ser inserida no cadastro de vendas de


passagem. Ao final, deve ser gerado um documento com as informações da
reserva e o valor da passagem. ​ **OBS1** ​: Não podem existir duas reservas
com o mesmo código. ​ **OBS2** ​: O código da reversa gerado pelo programa
deve conter apenas letras maiúsculas e dígitos numéricos. ​ **OBS3** ​: Em
memória, o cadastro de vendas deve conter o código da reserva, uma
referência ao cliente que efetuou a compra, uma referência ao vôo e o
número da poltrona.
(2)Consultar passagens de um cliente. Recebe como parâmetro o cpf de um
cliente e exibe todas as passagens ativas do cliente.
(3)Consultar histórico de um cliente. Recebe como parâmetro o cpf de um
cliente e exibe todas as passagens do cliente, informando o status de cada
uma delas.
(4)Cancelar venda de passagem de um cliente. Recebe como parâmetro o
código da reserva e, caso a reserva exista, faz o cancelamento, alterando o
status da reserva para cancelada, e atualiza os dados do vôo, liberando a
poltrona correspondente. Ao final, deve ser gerado um documento de
crédito com as informações do cancelamento e o valor do crédito que
corresponderá a 50% do valor da passagem cancelada.
(5)Consultar passageiros de um vôo. Recebe como parâmetro o código do vôo
e exibe o cpf, o nome e a poltrona de todos os passageiros que possuem
reservas ativas naquele vôo.
**OBS1** ​: O programa deverá fazer a validação de ​ **TODOS** os dados informados pelo
usuário.
**OBS2** ​: O cadastro de vendas da empresa deverá ser implementado, em memória,
através de uma lista encadeada. O referido cadastro deverá também ser mantido
em arquivo. No início do programa, os dados no arquivo de vendas devem ser
carregados para a lista. No final da execução do programa, os dados da lista
devem ser gravados no arquivo, sobrescrevendo-o.
**OBS3** ​: Em arquivo, o cadastro de vendas deve conter o código da reserva, o cpf
do cliente que efetuou a compra, o código do vôo e o número da poltrona.
**OBS4** ​: Ao copiar os dados do arquivo para a lista, alterar o status da reserva para
“efetivada” em todos os vôo cuja data seja anterior à data atual.


