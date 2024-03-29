## A Simple Spring Security Web Application

### Instructions

Using Java and Spring Framework (not Spring Boot please), write a simple web application that uses Spring Security for authentication.
 
Guidelines and Requirements

- The program should be written using Java 11 or higher.
- The program should implement the solution using Spring Security 5 or above.
- Use a development environment (IDE, OS platform) of your choice.

### Key Dependencies

- Java 17
- Spring 5.3.18
- Spring Security 5.6.2
- Tomcat Server v9.0
- JUnit 4.12
- Eclipse 2024-03 (4.31.0)

### Download the project
Run from the command line: git clone https://github.com/jellybean482/SimpleSpringSecurityWebApp.git<br>
This will create a SimpleSpringSecurityWebApp  folder and populate the content.

### Build the Project
- Import the project into Eclipse IDE:<br>
From Eclipse, Import -> Existing Projects into Workspace -> select the above SimpleSpringSecurityWebApp folder<br>
- Build the project:<br>
From Eclipse, Project -> enable "Build Automatically" or Project -> Build Project<br>

### Run the Project

- In Eclipse, RMB on Spring-MvcMaven project and select "Run As" -> "Run on Servers" -> Select "localhost: Tomcat v9.0 Server at localhost" -> Make sure that Spring-MvcMaven is added to the "Configured" pane -> Finish.<br>
- This will start the Tomcat server and deploy the Spring-MvcMaven web application on it. It will also bring up a web browser window at "localhost:8080/Spring-MvcMaven/" and return "Hello" in the browser content window. <br>
- Alternatively, you can bring up a web browser yourself and go to http://localhost:8080/Spring-MvcMaven/admin or http://localhost:8080/Spring-MvcMaven/user.<br>
- A logon popup screen will be displayed. Enter "user/password" for user and "admin/password" for admin user.<br>

### TODOs

- Create a WebMvcConfig and add View Controllers for customized display
- Implement Form login, landing pages on success and failure with redirect
