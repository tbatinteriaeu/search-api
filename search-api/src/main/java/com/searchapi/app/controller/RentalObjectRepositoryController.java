package com.searchapi.app.controller;

import com.searchapi.app.model.RentalObject;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@ComponentScan(basePackageClasses = RentalObjectRepositoryController.class)
@RestController
@RequestMapping("/rest/rental-object")
public class RentalObjectRepositoryController {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    ElasticsearchOperations elasticsearchOperations;

    public RentalObjectRepositoryController(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }


//    @PostMapping("/create")
//    public String create(@RequestBody RentalObject object) throws IOException {
//
////        IndexResponse response = client.("object", object.getId())
////                .setSource(jsonBuilder()
////                        .startObject()
////                        .field("name", object.getName())
////                        .endObject()
////                )
////                .get();
////               System.out.println("response id:"+response.getId());
////        return response.getResult().toString();
//        return "";
//    }

    @PostMapping("/create")
    public String save(@RequestBody RentalObject rentalObject) {

        IndexQuery indexQuery = new IndexQueryBuilder()
                    .withObject(rentalObject)
                .build();

        String documentId = elasticsearchOperations.index(indexQuery);

        return documentId;
    }

    @GetMapping("/get/{id}")
    public String findById(@PathVariable final String id) {

        System.out.println("Starting get request..");
        System.out.println(String.format("Client class is (%s)", restHighLevelClient.getClass()));
        GetRequest getRequest = new GetRequest("rental_object", id);
        GetResponse getResponse = null;
        try {
            getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(getResponse.getSourceAsString());


        return getResponse.getSourceAsString();
    }

    @GetMapping("/get")
    public String findAll() {

        GetRequest getRequest = new GetRequest("rental_object");
        GetResponse getResponse = null;
        try {
            getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(getResponse.getSourceAsString());
        return getResponse.getSourceAsString();
    }


//    @PostMapping("/update/{id}")
//    public String update(@PathVariable final String id) throws IOException {
//
//        UpdateRequest updateRequest = new UpdateRequest();
//        updateRequest.index("users")
//                .type("employee")
//                .id(id)
//                .doc(jsonBuilder()
//                        .startObject()
//                        .field("name", "Rajesh")
//                        .endObject());
//        try {
//            UpdateResponse updateResponse = client.update(updateRequest).get();
//            System.out.println(updateResponse.status());
//            return updateResponse.status().toString();
//        } catch (InterruptedException e) {
//            System.out.println(e);
//        } catch (ExecutionException e) {
//        	System.out.println(e);
//        }
//        return "Exception";
//    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable final String id) {

        DeleteRequest deleteRequest = new DeleteRequest("rental_object", id);
        DeleteResponse deleteResponse = null;
        try {
            deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deleteResponse.getResult().toString();
    }
}