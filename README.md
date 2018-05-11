# ShopYourLikes

Insert intro text

## Build and Run

1. Git clone the repo.
2. Build the database (see below).
3. Run `mvn package && java -jar target/spring18-0.0.1-SNAPSHOT.jar` (first part to build and second to run). Replace `0.0.1-SNAPSHOT.jar` with the current version of the project as found in `pom.xml`.

## Building Database

In order to run, this application requires a working database. Enter `mysql` from the command line and run the following commands to match the `application.properties` file of the build (Note that these configurations are for development purposes, and as such they do not pretend to have any security.).

1. `CREATE DATABASE syldb;`
2. `CREATE USER 'syl'@'localhost' identified by 'password';`
3. `GRANT ALL ON syldb.* to 'syl'@'localhost';`

After this, the application needs to be initialized. In `application.properties`, there is a `spring.jpa.hibernate.ddl-auto` variable currently set to `none`. Set this to `create` for the first run of the application. On subsequent runs, set it to `none` (or `update` if you wish to change the schema).
