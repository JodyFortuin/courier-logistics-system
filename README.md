# courier-logistics-system
1. Download the project from the following repo: https://github.com/JodyFortuin/courier-logistics-system

2. Install postgres
Create a db named ‘courier-logistics-db’. Host port is 5432.

3. Install postman
Import the collection provided

4. Open the project in an IDE.
Create a configuration, set jdk version to Java 17, and ‘Main Class’ to: com.example.courierLogisticsSystem.CourierLogisticsSystemApplication

5. Database and mock data setup:
Navigate to src/main/java/com.example.courierLogisticsSystem
In the ‘db’ folder, open DB.java
In the connect() method, update the ‘schema’ and ‘data’ paths.( These need to be absolute file paths for your machine, and can be found by uncommenting the System.out.Lines and running the project to log out the file paths.)

After the above setup, API calls can then be made from PostMan.
