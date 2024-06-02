## Start Postgres:

   docker run --rm --name pg-docker \
   -e POSTGRES_PASSWORD=pwd \
   -e POSTGRES_USER=usr \
   -e POSTGRES_DB=demoDB \
   -p 5430:5432 \
   postgres:16

## Http example:

GET http://localhost:8080/jdbcDemo
Accept: */*
Content-Type: application/json
Cache-Control: no-cache

GET http://localhost:8080/hi?name=Ivan
Accept: */*
Content-Type: application/json
Cache-Control: no-cache

###
POST http://localhost:8080/response/Ivan
Accept: */*
Content-Type: application/json
Cache-Control: no-cache

{
"param1": "value1",
"param2": "value2"
}

###
GET http://localhost:8080/actuator/health
Accept: */*
Content-Type: application/json
Cache-Control: no-cache

###
GET http://localhost:8080/actuator/health/liveness
Accept: */*
Content-Type: application/json
Cache-Control: no-cache

###
GET http://localhost:8080/actuator/health/readiness
Accept: */*
Content-Type: application/json
Cache-Control: no-cache