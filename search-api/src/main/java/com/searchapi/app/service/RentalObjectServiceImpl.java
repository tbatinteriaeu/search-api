package com.searchapi.app.service;

import com.searchapi.app.model.RentalObject;
import com.searchapi.app.repository.RentalObjectRepository;

import java.util.List;
import java.util.Optional;

public interface RentalObjectServiceImpl {

    RentalObjectRepository getRepository();

    List<RentalObject> getAll();

    String create(RentalObject object);

    Optional<RentalObject> get(String id);

    void delete(String id);

    RentalObject update(RentalObject object);

}
