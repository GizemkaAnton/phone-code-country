# Getting Started

1) Before running the web application, you need to run the PostgreSQL database in a Docker container. The Docker framework must be pre-installed;
2) To run the PostgreSQL database in the terminal in the root package of the project, run "docker-compose up" command. PostgreSQL will rise to localhost:5432 and be available.
3) To run the web application in the terminal in the root package of the project, execute "mvn spring-boot:run" command. The application server will start at localhost:8088. The application will be available at the URL: http://localhost:8088/.
4) To run tests without generating a report in the terminal in the root package of the project, execute "mvn clean test" command.
5) To run tests with report generation in the terminal in the root package of the project, execute "mvn surefire-report:report" command. The test report will be located in target/site/Test-Report.html

