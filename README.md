# Home-beauty-application

## Requirements
For building and running the application you need:
 - [JDK 1.8]()
 - [Maven 3]()

## Running the application locally
There are several ways to run our Springboot application on your local machine. One way is to execute the 'main' method in the 'com.ourapp.salonapp.SalonApplication' class from your IDE.

Alternatively,
   
   This application is embedded with Tomcat 8 so there is no need to install tomcat on your local machine.
   
   1. Clone th project into local repository.
   2. Make sure you are having JDK 1.8 and Maven 3.6.3.
   3. You can build the project by running ```mvn clean install```.
   4. Once installed you can find ```SalonApp.0.0.1.jar``` inside the target folder.
   5. Run the application by ```java -jar SalonApp-0.0.1.jar``` command.
   6. Visit the [http://localhost:8095](http://localhost:8095).
   
### Usage Modules
    1. Customer Module - Customers can book SPA appointments.
    2. Stylist Module - Stylists can view their slots and Bill of the customers.
    3. Admin Module - Admin/Manager can view Stylist work nature.
