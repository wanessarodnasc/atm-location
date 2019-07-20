# ING ATM API

Tech stack

* Java 8
* Spring boot 2.1.0
* Maven
* Rest Template
* Hazel cast
* H2 database
* REST Assured and JUnit
* Swagger


## Information

To start the API run the Application class.

The application use cache and the configuration of that are in hazelcast.xml

## Access

The application implements JTW.

The right way to use the API is creating a user and validate the token with this new use but this step use a google email 
account to send the password to avoid possible problem to test the application,  when the application starts a new user are created. 
So if you have any problem to register your own user you can test with the user bellow.

		username: teste
		password: 123456
		
## Use postman

Import the collection and execute in the order

	

