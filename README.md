# Web Search API Service

## Twitter Recent Search API

This Spring Boot Application will use Twitter Recent Search API to search the latest tweets related
to keyword. Note that Twitter Recent Search API only supports tweet search within past 7 days.

## Steps to run the app

1 Export the twitter Bearer token

```
   set BEARER_TOKEN=xxxxx
```

2 Run the app

```
   mvn clean spring-boot:run 
```

3. Open a browser, type the following URL, and you should see some results.

```
   http://localhost:8080/twitter/search/recent/depression
```
