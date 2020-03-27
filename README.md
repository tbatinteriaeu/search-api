# Search API

Search API module

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- docker
- docker-compose
- curl or similar tool like postman to make http request from your host 

Please check the official docker docs to get docker installed on your machine https://docs.docker.com/docker-for-windows/install/

### Installing

A step by step series of examples that tell you how to get a development env running

At first ensure you have a proper memory settings for JVM on your host machine

```
sysctl -w vm.max_map_count=262144
```
Start docker containers

```
docker-compose up
```

Elastic search is running now. You can check if it alive by runing curl command

```
curl http://127.0.0.1:9200/_cat/health 
```
You should see response like 
```
1558560223 21:23:43 docker-cluster green 2 2 0 0 0 0 0 0 - 100.0
```
Now you can test also kibana by calling url in your browser
```
http://localhost:5601
```

Now you are ready to store and retreive data from ElasticSearch
## Examples of using
Store a document 
```
curl -X PUT "http://localhost:9200/football_field/_doc/1" -H 'Content-Type: application/json' -d' { "object_type" : "inhouse",  "address" : "Maltańska 22, 04-041 Warszawa" }' -m 5
```
That should give you response like 
```
{"_index":"football_field","_type":"_doc","_id":"1","_version":1,"result":"created","_shards":{"total":2,"successful":1,"failed":0},"_seq_no":0,"_primary_term":1}
```

Get data from elastic search

```
 curl -X GET "localhost:9200/object/_doc/_search" -H 'Content-Type: application/json' -d' {"query" : {"constant_score" : { "filter" : {"term" : {  "object_type" : "inhouse"}}}}}'
```
with response like
```
{"took":7,"timed_out":false,"_shards":{"total":1,"successful":1,"skipped":0,"failed":0},"hits":{"total":{"value":1,"relation":"eq"},"max_score":1.0,"hits":[{"_index":"football_field","_type":"_doc","_id":"1","_score":1.0,"_source": { "object_type" : "inhouse",  "address" : "Maltańska 22, 04-041 Warszawa" }}]}}
```

##Spring-boot Search API
for install go to ./search-api/search-api and run
``` 
mvnw install dockerfile:build
```
for starting application, (when docker image is already built and you have elasticsearch running)
```
mvn spring-boot:run
```
now you're able to connect to application http://localhost:8080

##Useful commands for docker
show awailable running containers
```
docker ps
```
log into container
```
docker exec -it searchapi_kibana_1 bash
```
docker remove
```
docker-compose down
```
to remove particular container image from your machine first check intalled images
```
docker ps -a
```
and remove it using its name
```
docker rm hash
```

## REST API USING
##### CREATE
POST: 
`
/rest/rental-object/create
`

e.g.
`localhost:8080/rest/rental-object/create
`

with body:
````
{
 
  "city" : "Warsaw",
  "street" : "Maltanska",
  "buildingNo" : "12a",
  "type" : "football_pitch",
  "name" : "Boisko szkoła nr 422",
  "location" : { "lat" : 34.118347, "lon" : -118.3026284 }
}
````
##### READ
GET: 
`
/rest/rental-object/get/{id}
`

e.g.
`localhost:8080/rest/rental-object/get/XCKvR28BxRSsFrBbcRSJ
`
##### UPDATE
##### DELETE
DELETE: 
`
/rest/rental-object/delete/{id}
`

e.g.
`localhost:8080/rest/rental-object/delete/XCKvR28BxRSsFrBbcRSJ
`

## Deployment
## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Tomasz Biedrzycki** - https://github.com/tbatinteriaeu

