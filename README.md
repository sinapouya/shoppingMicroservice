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
