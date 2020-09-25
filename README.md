# Sainsberry's Scraper


This app was designed to scrape berry info and calculate total gross and vat from this default [test page](https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html)
or a command line user provided page that follow a similar html dom structure.

The app is written in [Java](https://java.com/en/download/manual.jsp) and relies on the following frameworks 
and tools:

* [Gradle 6+](https://gradle.org/) build tool
* [Spring Boot 2+](https://spring.io/projects/spring-boot/) DI Container
* [Lombok](http://jnb.ociweb.com/jnb/jnbJan2010.html) Multipurpose library (Java syntax)
* [Jackson](https://github.com/FasterXML/jackson) Json library


## Getting Started

### Build
Once the above tooling is in place, build the application by issuing the following command
from project's root folder:

```bash
$ ./gradlew clean build
```

This will build the application, running all tests in the process.

### Run
In order to run the app, you will need open your terminal and type:
```bash
# scrape default page
$ java -jar build/libs/sainsberrys-scraper-1.0-SNAPSHOT.jar 

# scrape user provided url (suppressing the logging)
$ java -Dlogging.level.my.repo.sainsburys=WARN -jar build/libs/sainsberrys-scraper-1.0-SNAPSHOT.jar <user url>

# pipe through "jq" app to get a better prettyprint of the returned json
$ java -jar build/libs/sainsberrys-scraper-1.0-SNAPSHOT.jar | jq 
```