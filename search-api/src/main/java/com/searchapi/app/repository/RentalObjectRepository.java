package com.searchapi.app.repository;

import com.searchapi.app.model.RentalObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface RentalObjectRepository extends ElasticsearchRepository<RentalObject, String> {
    List<RentalObject> findAll();

    Page<RentalObject> findByName(String name, Pageable pageable);
}
