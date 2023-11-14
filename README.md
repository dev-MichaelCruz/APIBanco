# API Banco

API desenvolvida usando a linguagem Java em conjunto com o framework Quarkus com ralizaçãode operações básicas de uma conta bancária.
 - Criar conta;
 - Depositar;
 - Sacar;
 - Transferir entre contas;
 
 ## Para executar
 Deve-se ter o maven e a cli do quarkus instalada em sua máquina;
 Instruções no link a seguir
 >https://quarkus.io/get-started/

Após instalada a cli, na pasta da aplicação digite o comando a seguir no terminal
`quarkus dev` e aguarde a aplicação inicializar

## Caminho raiz da aplicação
>http://localhost:8080

## Endpoints
### `POST` `/contacorrente/criarconta`
Cria uma nova conta corrente através dos parâmetros:
> nome

> cpf

### `POST` `/contacorrente/depositar`
Deposita um valor em uma conta corrente existente exigindo:
> numeroConta;

> valorDeposito;

### `PATCH` `/contacorrente/sacar`
Realiza saque de conta corrente, desde que haja valor disponível para tal, exigindo:
>numeroConta;

>valorSaque;

### `PATCH` `/contacorrente/transferir`
Realiza a transferência de valores entre duas contas válidas, desde que a conta de origem tenha valor suficiente para a transação, exigindo:
>contaOrigem;

>contaDestino;

>valorTransferencia;

### `GET` `/contacorrente/listacontas`
Apresenta todas as contas e seus respectivos saldos


### Testes das rotas
Para executar os testes, utilizei o Insomnia, mas você pode utilizar qualquer plataforma de consumo de API que lhe for mais conveniente.
