🚀 Exciting News: Leveling Up Our Microservices Architecture - Part 7 api gateway ! 🚀

Hello LinkedIn community! 👋 I'm back with another thrilling update in our microservices journey. In Part 6, we witnessed the Code Evolution within our OrderService microservice. Now, in Part 7, get ready for a game-changer as we introduce an API Gateway to streamline access to our microservices.

📦 **Code Evolution Continues: API Gateway Integration:**
Our microservices architecture has reached new heights with the integration of an API Gateway. Let's explore the enhancements made in the OrderService:

**1. API Gateway Integration:**
 - We've seamlessly integrated an API Gateway into our architecture, serving as a central entry point for external requests. This not only simplifies the access to our microservices but also enhances security and manageability.

**2. Docker Compose Modification:**
 - To align with the API Gateway implementation, our Docker Compose files have been updated. Services are now accessed exclusively through the API Gateway, offering a unified and organized access point for consumers.

**3. Streamlined Microservices Access:**
 - Whether you're interacting with the OrderService, InventoryService, or any other microservice, it's now done through the API Gateway. This streamlines communication and provides a cohesive interface for all consumers.

⚙️ **Testing Assurance:**

Ensuring the reliability of our microservices remains a top priority. Rest assured, our testing strategy has been reinforced to validate the seamless interaction facilitated by the API Gateway. The comprehensive Integration tests have been updated to reflect this architectural evolution.

⚠️ **Note:** If you encounter the "intellij FATAL:gpu data manager mpl private.cc GPU process isn't usable. Goodbye" error, consider disabling the Markdown plugin in IntelliJ plugin settings. Alternatively, follow the instructions in this [Stack Overflow post](https://lnkd.in/eesp6ikK).

📚 **GitHub Repository Update:**
Curious to see the changes? Dive into the latest updates in our GitHub repository: [GitHub](https://lnkd.in/dhfr9G8X)

⭐ **Your Feedback Matters:**
As always, your feedback, insights, likes, or reposts contribute to our collective growth in the dynamic realm of microservices. Let's continue learning and growing together in the fascinating world of microservices! 🌍✨
If you missed the previous parts, catch up here:
Part 6: [https://lnkd.in/dx5SHg5J]
Part 5: [https://lnkd.in/e_q7jZb8]
Part 4: [https://lnkd.in/ecG3PQ_F]
Part 3: [https://lnkd.in/efYCQ9Hb]
Part 2: [https://lnkd.in/e3xr6pDa]
Part 1: [https://lnkd.in/eDTYSqUt]

hashtag#Microservices hashtag#Java hashtag#SpringBoot hashtag#APIGateway hashtag#Docker hashtag#DockerCompose hashtag#TechUpdate hashtag#DevOps hashtag#CodeEvolution hashtag#TechEnthusiast hashtag#GitHub

🚀 Exciting News: Evolving Our Microservices Architecture - Part 6 Code Evolution ! 🚀

Hello LinkedIn community! 👋 I'm thrilled to share the latest update in our ongoing microservices journey. In Part 5, we took a giant leap by Dockerizing our microservices architecture. Now, let's dive into the exciting changes and enhancements we've made in Part 6.

🔍 Code Evolution: Microservices OrderService Update

📦 **OrderEntityService Transformation:**
I've revamped the `OrderEntityService` in the OrderService microservice to enhance functionality and integration. Here are the key changes:

**1. Integration with InventoryService:**
 - Introduced `InventoryService` integration to validate inventory availability before placing an order.

**2. Improved Order Placement Logic:**
 - Enhanced the order placement logic to validate inventory availability based on SKU codes.

**3. Updated Exception Handling:**
 - Implemented detailed exception handling for scenarios where SKUs are out of stock or invalid.

⚙️ **Testing Reinforcement:**

To ensure the reliability of our microservices, I've created comprehensive test cases using JUnit and Testcontainers. These tests cover scenarios such as valid orders, invalid SKUs, and zero quantity orders.

⚠️ **Note:** If you encounter the "intellij FATAL:gpu data manager mpl private.cc GPU process isn't usable. Goodbye" error, consider disabling the Markdown plugin in IntelliJ plugin settings. Alternatively, follow the instructions in this [Stack Overflow post](https://lnkd.in/eesp6ikK).

📚 **GitHub Repository Update:**
Explore the code changes on my GitHub branch: [https://lnkd.in/eDjiQ4tx]

⭐ **Your Feedback Matters:**
Your feedback, insights, likes, or reposts contribute to our collective growth in the dynamic realm of microservices. Let's continue learning and growing together in the fascinating world of microservices! 🌍✨
🚀 **Stay tuned for more updates as we continue to evolve and refine our microservices architecture!** 🚀
If you missed the previous parts, catch up here:
Part 5: [https://lnkd.in/e_q7jZb8]
Part 4: [https://lnkd.in/ecG3PQ_F]
Part 3: [https://lnkd.in/efYCQ9Hb]
Part 2: [https://lnkd.in/e3xr6pDa]
Part 1: [https://lnkd.in/eDTYSqUt]

hashtag#Microservices hashtag#Java hashtag#SpringBoot hashtag#Testing hashtag#DevOps hashtag#CodeEvolution hashtag#TechUpdate
hashtag#Docker hashtag#DockerCompose hashtag#DevOps hashtag#TechEnthusiast hashtag#GitHub

🚀 Exciting News: Leveling Up Our Microservices Architecture! 5th part  Dockerizing Our Microservices 🚀 
Hi LinkedIn community! 👋 I'm thrilled to share the latest update in our microservices journey – the Dockerization of our modules and the introduction of Dockerfiles for each module, along with a related service in the Docker Compose configuration! 🐳
📁 Project Overview:
In this phase, I've taken a significant step forward by containerizing microservices using Docker. This not only enhances the portability and consistency of our applications but also simplifies the deployment process. Let's dive into the key highlights of this Dockerization journey.

If you missed the previous parts, catch up here:
Part 4: [https://lnkd.in/ecG3PQ_F]
Part 3: [https://lnkd.in/efYCQ9Hb]
Part 2: [https://lnkd.in/e3xr6pDa]
Part 1: [https://lnkd.in/eDTYSqUt]

🐳 Dockerization:
**Dockerfiles:** I've created Dockerfiles for each module in our microservices architecture. These Dockerfiles specify the necessary dependencies, configurations, and steps to build a Docker image for the respective module.

**Docker Compose:** To orchestrate the deployment of our microservices, I've introduced a Docker Compose configuration file. This file defines the services, networks, and volumes required for microservices ecosystem.

🚀 Building and Running:

1. **Maven Clean and Install:**
 Before containerizing  modules, I ensure a clean build by executing Maven clean and install commands. This guarantees that our applications are compiled and packaged correctly.
 mvn clean install
2. **Docker Compose Up:**
 To start microservices and related services, run the following Docker Compose command. This command builds the Docker images and launches the containers in detached mode.
sudo docker-compose up --build -d

 🌐 Visit [http://localhost:8761/](http://localhost:8761/) to explore the Eureka service registry and witness microservices in action!

 ⚠️ **Note:** If you encounter the "intellij FATAL:gpu data manager mpl private.cc GPU process isn't usable. Goodbye " error, consider disabling the Markdown plugin in IntelliJ plugin settings. Alternatively, follow the instructions in this [Stack Overflow post](https://lnkd.in/eesp6ikK).


📚 GitHub Repository:

Explore the code changes on my GitHub branch: [https://lnkd.in/eZ7F2AZD]


⭐ Your feedback, insights, likes, or reposts contribute to our collective growth in the dynamic realm of microservices.

Let's continue learning and growing together in the fascinating world of microservices! 🌍✨

hashtag#Java hashtag#Microservices hashtag#SpringBoot hashtag#Docker hashtag#DockerCompose hashtag#DevOps hashtag#TechEnthusiast hashtag#GitHub


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
