Prerequisites:
Maven: 3.6.3
Java: 13
MySQL: 8.0.19

Instructions to follow during the execution of the program:
1. Open MySQL and execute the create-db.sql
	Make sure that the username and password are root and the connection is localhost:3306
2. run mvn clean
3. run mvn compile
4. run mvn datanucleus:schema-create
5. Execute the add-items.sql script in MySQL
6. run mvn exec:java
7. user: jorge pass:pass

To do the tests and check their functionality: mvn test