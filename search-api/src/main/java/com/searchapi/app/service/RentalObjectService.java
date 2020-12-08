package com.searchapi.app.service;

import com.searchapi.app.model.RentalObject;
import com.searchapi.app.repository.RentalObjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalObjectService implements RentalObjectServiceImpl {
    private final RentalObjectRepository repository;

    public RentalObjectService(RentalObjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public RentalObjectRepository getRepository() {
        return this.repository;
    }

    @Override
    public List<RentalObject> getAll() {

        List<RentalObject> allResults = repository.findAll();

        return allResults;
    }

    @Override
    public Optional<RentalObject> get(String id) {

        Optional<RentalObject> result = repository.findById(id);

        return result;
    }

    @Override
    public String create(RentalObject object) {
        RentalObject result = repository.save(object);

        return  result.getId();
    }

    @Override
    public RentalObject update(RentalObject object) {
        RentalObject result = repository.save(object);

        return result;
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
