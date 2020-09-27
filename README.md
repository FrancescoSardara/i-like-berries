# Sainsberry's Scraper


This app was designed to scrape berry info and calculate total gross and vat from this default [test page](https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html)
or a command line user provided page that follow a similar html dom structure.

The app is written in [Java](https://java.com/en/download/manual.jsp) and relies on the following frameworks 
and tools:

* [Gradle 6+](https://gradle.org/) Build Automation Tool
* [Spring Boot 2+](https://spring.io/projects/spring-boot/) DI Container
* [Lombok](http://jnb.ociweb.com/jnb/jnbJan2010.html) Multipurpose Library (Java syntax)
* [Jackson](https://github.com/FasterXML/jackson) Json Library
* [Docker](https://docs.docker.com/engine/install/) Run & Share apps in containers


## Getting Started

### Build
There's no need to install additional tools. All required dependencies will be automatically downloaded during the build.
Next command executes the following tasks:
 * compile the source code into classes 
 * run tests to validate requirements
 * assemble artifacts into an executable jar

```bash
$ ./gradlew clean build
```

With the following you're going to creates a docker image (_sainsberrys-scraper_), which represents a convenient way 
to run the application without fussing over required runtime and dependencies. For that you now need to have
Docker engine installed (see link): 
```bash
$ ./gradlew dockerBuildImage
```

### Run
In order to run the app, you will need to open your terminal and type:
```bash
# scrape default (test) page
$ docker run -it --rm sainsberrys-scraper

# scrape user provided url 
$ docker run -it --rm sainsberrys-scraper  <user url>

# Alternatively you can run (maybe suppressing the logging):
$ java -jar build/libs/sainsberrys-scraper-1.0-SNAPSHOT.jar 
$ java -Dlogging.level.my.repo.sainsburys=WARN -Dspring.main.banner-mode=off -jar build/libs/sainsberrys-scraper-1.0-SNAPSHOT.jar <user url>
```
