# AI bot for dispute resolution

## The project simulates a dispute resolution process, where a WhatsApp bot acts as a judge.
### Tech stack - Java 21, Spring Boot, Postgres, Anthropic AI, custom RAG.

The project structure is 2 external APIs and a backend for business logic. I used the API for Anthropic AI to generate text, and the Whatsapp API for messaging.

On the backend, I divided the code into three layers:

1. **Controller** for listening (long polling) to new messages.

2. **Services** for processing incoming messages and further processing:
- dividing them into new or existing disputes,
- determining the stage of the dispute,
- searching the database for relevant information about the user and the history of the dispute,
- forming a prompt,
- generating text - an additional question on the dispute or a decision.
3. Layer for working with the **database**.

According to the scenario, the initiator of the dispute is the first to speak. When a dispute is just started, it is assigned a Stage with the enum value NONE.

Then the application pulls all the information on this dispute from the DB and forms JSON, which then combines this information with a new message from the user and sends a request to Anthropic AI. As you can see from the prompt, the instructions indicate that the response should come in JSON format.

Then the response is parsed into 2 parts - the part that is sent to the user (additional question, UUID of the dispute to send to the opponent or the decision), and the part that goes to update the existing dispute.

You can put a regulatory document inside the prompt and the AI will resolve the dispute based on these documents.

## How to start the project

1. Clone the repository
2. Create a new database in Postgres
3. Create a new file application.properties in the resources folder and fill in the following fields:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update

anthropic.api.key=your_api_key
anthropic.api.url=https://api.anthropic.com/v1/completions
Green-API.instanceId=your_instance_id
Green-API.instanceToken=your_instance_token
Green-API.host = "https://api.green-api.com"
Green-API.hostMedia="https://media.green-api.com"
``` 
4. Run the docker command: 

> docker build . -t bot 
> 
> docker run -d --restart unless-stopped --name bot-api bot
