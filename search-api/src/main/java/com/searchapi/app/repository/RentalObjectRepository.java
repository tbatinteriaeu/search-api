package com.searchapi.app.repository;

import com.searchapi.app.model.RentalObject;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RentalObjectRepository extends ElasticsearchCrudRepository<RentalObject, String> {

}
