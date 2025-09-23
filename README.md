![Imagem](https://drive.google.com/uc?export=view&id=1y0OVp1R6Pjebxr2gRE9mv9GaiEawC8aF)

![Java](https://img.shields.io/badge/Java-17+-blue?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-brightgreen?logo=spring&logoColor=white)
![Spring Web](https://img.shields.io/badge/Spring%20Web-REST-blue?logo=spring&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-blueviolet?logo=springboot&logoColor=white)
![Databases](https://img.shields.io/badge/Database-H2-blue?logo=postgresql&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-%23EE2C2C?logo=lombok&logoColor=white)
![Bean Validation](https://img.shields.io/badge/Bean%20Validation-jakartaee-orange)
![SLF4J](https://img.shields.io/badge/SLF4J-logging-blue)
![JPA/Hibernate](https://img.shields.io/badge/JPA%2FHibernate-darkgreen?logo=hibernate&logoColor=white)
![Insomnia](https://img.shields.io/badge/Insomnia-REST%20Client-orange?logo=insomnia&logoColor=white)

API REST desenvolvida em Java com Spring Boot para operações bancárias básicas como **depósito, saque e transferência via PIX entre contas.**

## Estrutura do projeto

- **controller**

Endpoints REST:

- `DepositController`: endpoint **POST** `/deposits` para realizar depósitos. Recebe `accountId` e `amount` no corpo da requisição.
- `WithdrawalController`: endpoint **POST** `/withdrawals` para realizar saques. Recebe `accountId` e `amount`.
- `PixController`: endpoint **POST** `/pix` para transferências entre contas. Recebe `originAccountId`, `destinationAccountId` e `amount`.

- **model**

Contém a entidade `Account` que representa a conta bancária com saldo e dados essenciais.

- **repository**

Contém o `AccountRepository` que estende **JpaRepository** para persistência e consultas.

- **service**

Camada de negócio com serviços

- `DepositService`: lógica para realizar depósitos em contas.
- `WithdrawalService`: lógica para realizar saques de contas.
- `PixService`: lógica para transferências (PIX) entre contas, atualizando saldos.

## Principais endpoints

**POST /deposits**

- Realiza depósito somando valor ao saldo.
- Corpo JSON:

```json
{
  "accountId": 1,
  "amount": 150.00
}
```

Retorna conta atualizada com saldo alterado.

---

**POST /withdrawals**

- Realiza saque subtraindo valor do saldo (se saldo suficiente).
- Corpo JSON:

```json
{
  "accountId": 1,
  "amount": 100.00
}
```

Retorna conta atualizada ou erro em saldo insuficiente.

---

**POST /pix**

- Realiza transferência entre duas contas (origem e destino).
- Corpo JSON:

```json
{
  "originAccountId": 1,
  "destinationAccountId": 2,
  "amount": 200.00
}
```

Retorna conta de origem atualizada ou erro se saldo insuficiente ou contas inexistentes.

---

## Testes

- Recomenda-se utilizar ferramentas como **Insomnia ou Postman** para testar os endpoints.

- Exemplo de requisição para depósito no Insomnia:

POST `https://localhost:8080/deposits` com o corpo JSON mostrado acima.

## Como rodar

- Configure o acesso ao h2-console no `application.properties`

- Compile e rode a aplicação:

```bash
./mvnw spring-boot:run
```

- Use uma ferramenta de testes de sua preferência para interagir com a API pelos endpoints mencionados.
