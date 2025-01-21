# LogixGo

## Overview
LogixGo is a robust solution designed to streamline the management of deliveries, routes, and drivers for logistics companies. This project focuses on integrating advanced technologies for seamless operation management, data handling, and reporting.

## Key Features
- Efficient database management with **MySQL** for data storage and retrieval.
- Backend logic implemented in **Java**, ensuring reliability, scalability, and performance.
- Dynamic generation of Word documents using the **Apache POI** library, enabling customized report creation.
- Integration of modern development frameworks and tools to ensure best practices in code structure and maintainability.

## Technologies Used

### **1. Java**
- Core language for implementing backend logic.
- Supports multi-threading for optimized route and delivery assignment.
- Provides integration with database and document generation libraries.

### **2. MySQL**
- Relational database used for managing and storing data related to:
  - Drivers
  - Deliveries
  - Routes
  - Constants
- Advanced SQL queries for aggregations, filtering, and multi-table joins.

### **3. Apache POI**
- Library used for Word document generation.
- Supports creation, reading, and modification of `.docx` files.
- Enables the project to generate customized delivery and route reports dynamically, including:
  - Route summaries.
  - Delivery schedules.
  - Driver assignments.

### **4. JDBC (Java Database Connectivity)**
- Facilitates interaction between Java and MySQL.
- Ensures secure and efficient execution of SQL queries.
- Uses prepared statements to prevent SQL injection and enhance performance.

### **5. Eclipse IDEA**
- Primary development environment for writing, testing, and debugging the application.
- Supports Java and MySQL plugins for seamless integration and code assistance.

## Word Document Generation
The system dynamically generates Word documents for:
- Detailed delivery and route reports.
- Driver schedules and assignments.
- Summary of pending and completed deliveries.
This functionality is powered by **Apache POI**, enabling easy exportation of data into `.docx` format, which is compatible with Microsoft Word and other word processors.

## How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/oyinlola007/LogixGo.git
   ```

2. Set up the database:
- Import the provided schema.sql into your MySQL server.

3. Configure database credentials:
- Update the DBManagement.java file with your database credentials.

4. Build and run the project.

## Contributing
We welcome contributions to enhance the functionality of this system. To contribute:
1. Fork the repository.
2. Create a new branch for your feature.
3. Submit a pull request for review.

## License
This project is licensed under the MIT License.
