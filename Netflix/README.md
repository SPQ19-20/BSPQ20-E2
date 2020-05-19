Prerequisites:
Maven: 3.6.3
Java: 13
MySQL: 8.0.19
Group: BSPQ20-E2
Authors: Iñigo Orue, Jorge El Busto, Rubén Leiva, Annette Tsatsampa, Diego Rojo

Instructions to follow during the execution of the program:
1. Open MySQL and execute the create-db.sql
	Make sure that the username and password are root and the connection is localhost:3306
	Open the file in "File, Open..." click the script, and click the thunder icon located in the toolbar in the upper part of the workbench.
2. run mvn clean (To do it, open the Windows command shell and type those commands, making sure that the MAVEN_HOME is already set to the folder where Maven is installed). 
3. run mvn compile (same process as step 2)
4. run mvn datanucleus:schema-create
5. Execute the add-items.sql script in MySQL 
6. run mvn exec:java
7. user: jorge pass:pass (it's the default user added by add-items.sql script)

NOTE: In order to do the tests, run mvn test. Tests will be done, the Jacoco report will be generated in the target/site folder and the Contiperf reports will be generated. In order to solve possible dependency issues with packages, run mvn install.

DOCUMENTATION: It's generated in the root folder of the project with Doxygen. Outputs are in HTML, LaTeX and RTF, and the HTML output can be seen as local files or in the following website: https://spq19-20.github.io/BSPQ20-E2/files.html

To do the tests and check their functionality: mvn test
