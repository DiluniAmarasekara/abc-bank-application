#Technology stack

		Java8
		Spring Boot
		PostgreSQL

#Create database by name 'abc_bank_db'

#To be able to run Spring Boot app, first you will need to build it 
#If you are not using any IDE, to build Spring Boot app into a single executable Jar file with a Maven, use the below command
#You will need to run it from the project folder which contains the pom.xml file

	mvn install
	
#You can use below command to run Spring Boot app with Maven plugin.

	mvn spring-boot:run

#Then in the database, three tables will be created. In order to get a proper response you have to insert data

#Then by using any API development tool (Postman), send a request to below "GET" REST API

	http://localhost:8080/getTransactionSummery

#You will get today's transaction summery 

Ex- {
		"Minuri": {
			"DEPOSIT": 6000.0,
			"WITHDRAWAL": 11000.0
		},
		"Sarasi": {
			"DEPOSIT": 3535.0,
			"WITHDRAWAL": 17880.0
		},
		"Diluni": {
			"DEPOSIT": 4000.0,
			"WITHDRAWAL": 3560.0
		}
	}
