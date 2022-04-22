# WCC Group Java Assignment

# Running the project

To run the project execute the `mvn spring-boot:run` command.
To run the tests execute the  `mvn test` command.

Before running the project make sure the Postgres database is populated and running.

You can do that with Docker using `docker-compose up` in the project root folder 
Or, add manually with `schema.sql` and `data.sql` located inside the database-init folder.

You can also access the swagger ui interface in [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

# API Documentation

## GET /location/{postcodeA}/{postcodeB}

*Calculates the distance between two postcodes*

### Parameters

|Name|Type|Description|
|---|---|---|
|postcodeA|string|string related to the first postcode|
|postcodeB|string|string related to the second postcode|

### Responses

|Status|Meaning|Description|
|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|successful operation|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|wrong input parameters|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|postcode not found|

## PUT /location/{locationId}

*Updates location with new latitude and longitude*

### Path/Body Parameters

|Name|Type|Description|
|---|---|---|
|locationId|long|id for the location that will be updated|
|latitude|string|string that will be updated to the location|
|longitude|string|string that will be updated to the location|

### Responses

|Status|Meaning|Description|
|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|successful operation|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|wrong input parameters|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|location not found|
