🚀 Exciting News: Leveling Up Our Microservices Architecture! 4th part  Add Discovery Server🚀 
Hi LinkedIn community! 👋 I'm thrilled to share the latest update in our microservices journey – the introduction of the Discovery Server! 🌐
📁 Project Overview:
In this phase, we've taken a significant leap by integrating the Discovery Server into our microservices ecosystem. This server, powered by Eureka, enhances our system's scalability and resilience.
If you missed the previous parts, catch up here:
Part 3: [https://lnkd.in/efYCQ9Hb]
Part 2: [https://lnkd.in/e3xr6pDa]
Part 1: [https://lnkd.in/eDTYSqUt]

🌐 Discovery Server:

Our Discovery Server acts as the central hub for service registration and discovery. By implementing Eureka from Spring Cloud, we've empowered our microservices to dynamically locate and communicate with each other.

🚀 Key Components:

Main Class: 
The main class of the Discovery Server application. It's annotated with @SpringBootApplication to indicate that this is the main entry point of the Spring Boot application. The @EnableEurekaServer annotation configures this application as a Eureka Server, while @EnableDiscoveryClient enables service discovery capabilities.

POM File:
The Project Object Model (POM) file manages the project's build configuration. In this section, it includes dependencies necessary for implementing the Eureka Server and Client functionality. Dependencies could include those from the Spring Cloud Netflix Eureka libraries.-- Relevant dependencies for Eureka Server and Client 

Application Properties: 
These properties are specified in the application properties file, providing essential configurations for the Discovery Server.
eureka.instance.hostname: Sets the hostname for the Eureka Server.
eureka.client.register-with-eureka: Indicates whether this service should register itself with Eureka.
eureka.client.fetch-registry: Specifies if this service should fetch the service registry from Eureka.
server.port: Defines the port on which the Eureka Server runs.
spring.application.name: Assigns a unique name to the Eureka Server application.

🔄 Microservices Integration:
To fully leverage the Discovery Server, we've updated our microservices:
Inventory Microservice:
Added @EnableDiscoveryClient to the main class.
Set Eureka properties in the application properties file.

Properties: 
add these properties to property file in project

eureka.client.service-url.defaultZone=http://localhost:8761/eureka
spring.application.name=inventory-service

✨Repeat this process for other microservices.

⭐ GitHub Repository:

Explore the code on my GitHub: [https://lnkd.in/eptNe5bk]
Your support through stars and feedback fuels our journey in the dynamic world of microservices. Let's continue growing and learning together! 🌍✨
hashtag#Microservices hashtag#Eureka hashtag#SpringCloud hashtag#TechUpdate hashtag#GitHub

part 3
🚀 Embarking on a Multi-Microservices Adventure! Third part 🚀
Greetings, LinkedIn community! 👋 I'm thrilled to introduce the latest addition to our microservices family – the Inventory Microservice! 🌐

📁 Project Overview:

In this phase of our microservices journey, we've seamlessly integrated the Inventory Microservice into our shopping application ecosystem. This microservice plays a pivotal role in managing product inventories, providing real-time information about stock availability.What makes this even more noteworthy is the establishment of a robust foundation – the Shopping Application Parent Microservice. This parent microservice is designed to streamline the development and management of our microservices architecture.

If you missed the first part, check it out
part 2. [https://lnkd.in/e3xr6pDa]
part 1.[https://lnkd.in/eDTYSqUt]

🌐 Shopping Application Parent Microservice:
To foster a modular and organized approach, we've introduced a parent microservice that acts as a central hub for managing dependencies and configurations shared across multiple microservices.

🚀 Key Components:

1. Inventory Controller:
The InventoryController serves as the gateway for inventory-related 
operations.
With the ability to check stock availability for a given product (skuCode), this controller ensures smooth communication between the client and the microservice.

2. Inventory Service:
The InventoryService encapsulates the business logic for determining stock availability.
By leveraging the InventoryRepository, this service retrieves product information and transforms it into a concise InventoryDto.

3. Inventory Entity and Repository:
The Inventory entity represents the structure of our inventory records.
The InventoryRepository facilitates seamless interaction with the database.

4. Exception Handling:
To ensure robust error handling, an NotExistInInventoryException has been implemented.

🔧 Tech Stack:
Spring Boot
Spring Data JPA
MySQL (leveraging Testcontainers for testing)
Lombok for streamlined Java development
🛠️ Testing:

Comprehensive testing using JUnit 5 and Testcontainers guarantees the reliability and correctness of the inventory management processes.

📚 GitHub Repository:

Shopping Microservice [https://lnkd.in/eCSmQiNm]

⭐ Star the Repository:

If you find this microservice intriguing, your support by starring the repository on GitHub is immensely appreciated! ⭐

⭐
Your feedback, insights, likes, or reposts contribute to our collective growth in the dynamic realm of microservices.

Let's continue learning and growing together in the fascinating world of microservices! 🌍✨

hashtag#Java hashtag#Microservices hashtag#SpringBoot hashtag#MySQL hashtag#LearningJourney hashtag#TechEnthusiast hashtag#Docker hashtag#DockerCompose hashtag#Testcontainers
