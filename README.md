# Interqu-Release

# Software Setup

1. Download & install Java 17
2. Download & install any Maven version that is compatible with Java 17
3. Download & install your preferred IDE (Eclipse, VSCode, IntelliJ)
4. Download & install the latest version of Postman

# Environment Variables

1. Ensure you have an application.properties file in /src/main/resources
2. Add system environment variables. Ask Derek for these variables.
   - ${MONGODB_URL}
   - ${SPRING_MAIL_PASSWORD}
   - ${SPRING_MAIL_USERNAME}
   - ${AWS_ACCESS_KEY}
   - ${AWS_SECRET_KEY}
   - ${INTERQU_API_KEY}
   - ${GET_EXPRESSION_API_URL}

# Project Setup

1. If not already done, create an /interqu folder to store all interqu related projects
2. Under the /interqu folder, git clone https://github.com/Interqu/interqu-release.git
3. Open up the project in your prefered IDE
4. In Eclipse/STS right click on the InterquReleaseApplication.java, and select: Run As > Spring Boot App
5. For cmd users, go to root directory of the app, and run "mvn clean install" to build, then run "mvn spring-boot:run" to start the app.

# Create new endpoint

1. Create a method within the appropriate API class
2. Decide on its mapping type and create a URI for it
3. If returning some data, create a response object for it and return it inside of the endpoint. It will automatically be converted to JSON
4. If you need some sort of user data, user SecurityContextHolder to get the current authenticated user.
5. By default, all endpoints are authenticated automatically. If this endpoint shouldnt be authenticated, please add it in the CustomWebConfig class.
6. Test your endpoint using PostMan

# Database

We are using MongoDB Atlas. It is hosted on an AWS server and is completely remote for development purposes. Heres how to create new tables/fields

1. If you wish to create a new table, first create an object to describe what the fields are
2. Create a interface - see the com.interqu.db package for more details
3. Send a .save request and check MongoDB Atlas to see if the data has been successfully stored.

# Docker-compose Deployment

Below is a sample docker-compose.yml configuration for creating the container.

```
---
version: "3.8"

services:
  interqu-spring-boot:
    container_name: interqu-fe
    image: lefanhu/interqu-spring-boot:latest
    stdin_open: true
    tty: true
    restart: unless-stopped
    ports:
      - '8080:8080'
    env_file:
      - './.env'
```
