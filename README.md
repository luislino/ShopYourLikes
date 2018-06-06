# ShopYourLikes

The SYL Link Journal provides a convenient and detailed web interface for investigating analytics of ShopYourLikes links. ShopYourLikes is primarily used by invited social influencers to generate monetized links to retailer items. The current SYL web interface does not provide certain desirable features such as batch URL conversion or specific link analysis, so the SYL Link Journal provides a way for social influencers to access this data if desired. The SYL Link Journal extends the functionality of the main SYL website, so while the journal exists independently of the main website, the journal’s users remain the same: invited social influencers.

To accomplish its tasks, the SYL Link Journal integrates with the ShopYourLikes API so that the users can view the effectiveness of their individual URLs and groups of URLs. For each SYL link, the SYL Link Journal provides detailed information such as the number of clicks, earnings generated, and primary geographic origin of clicks. This data can also be viewed at a group level, in which links can be categorized by retailer, popularity, and other factors.

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

## Endpoints

The database schema is available at the following REST API endpoints, with CRUD operations available through GET, POST, and DELETE requests:

Overall:
* /clicks - *GET* with optional params linkID; *POST* with required params linkID, orderAmount, orderNumber, unitsOrdered, convertedToSale, redirectDate, ipAddress, dma (linkID must actually exist)
* /links - *GET* with optional params publisherID, merchantID; *POST* with required params publisherID, merchantID, earnings, originalURL, imageRedirectPermahashlink and optional params customTitle (publisherID and merchantID must actually exist) 
* /merchants - *GET* with optional params merchantName; *POST* with required params merchantName
* /publishers - *GET* with optional params username; *POST* with required params username, apiKey

ID specific:
* /clicks/{id} - *GET* with clickID as {id} (returns null if unavailable), *DELETE*
* /links/{id} - *GET* with linkID as {id} (returns null if unavailable), *DELETE* (also deletes associated clicks), *POST* with optional params earnings, customTitle to update data with given id
* /merchants/{id} - *GET* with merchantID as {id} (returns null if unavailable), *DELETE* (also deletes associated clicks and links), *POST* with required params merchantName
* /publishers/{id} - *GET* with publisherID as {id} (returns null if unavailable), *DELETE* (also deletes associated clicks and links), *POST* with optional params username, apiKey
