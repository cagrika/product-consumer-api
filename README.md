
**Requirements** 

For building and running the application you need:

- JDK 1.8
- Maven 3
- DynamoDB

**Running the application locally**

You can use the Spring Boot Maven plugin like so:

`mvn spring-boot:run
`
 
 _You need to set dynamodb properties from application.yml_

**What is this project for ?**
 
- creates product and writes into DynamoDB. 
- increases watch count of product advertisement
- changes like count of products
- rates products

**Swagger**

`http://localhost:8080/swagger-ui.html`