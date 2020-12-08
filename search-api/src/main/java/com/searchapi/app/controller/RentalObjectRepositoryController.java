package com.searchapi.app.controller;

import com.searchapi.app.config.RestClientConfig;
import com.searchapi.app.model.RentalObject;
import com.searchapi.app.repository.RentalObjectRepository;
import com.searchapi.app.service.RentalObjectService;
import com.searchapi.app.service.RentalObjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ComponentScan(basePackageClasses = RentalObjectRepositoryController.class)
@RestController
@RequestMapping("api/v1/rental-object/")
public class RentalObjectRepositoryController {

    @Autowired
    RestClientConfig restHighLevelClient;

    @Autowired
    RentalObjectService rentalObjectService;

    private  ElasticsearchOperations elasticsearchOperations;

    public RentalObjectRepositoryController(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody RentalObject object) throws IOException {

        String result = rentalObjectService.create(object);
        return new ResponseEntity(result, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RentalObject> findById(@PathVariable final String id) {

        Optional<RentalObject> object = rentalObjectService.get(id);

        return object.map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/get")
    public List<RentalObject> findAll() {

        List<RentalObject> list = new ArrayList<>();
        list = rentalObjectService.getAll();

        return list;
    }


    @PutMapping("/update")
    public ResponseEntity update(@RequestBody final RentalObject object) throws IOException {

        Optional<RentalObject> subject = rentalObjectService.get(object.getId());

        if (subject.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        rentalObjectService.update(object);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable final String id) {

        Optional<RentalObject> subject = rentalObjectService.get(id);

        if (subject.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        rentalObjectService.delete(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}