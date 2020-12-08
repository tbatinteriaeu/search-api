package com.searchapi.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.geo.Point;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Document(indexName = "rental_object")
public class RentalObject {

    @Id String id;
    String type;

    String name;

    Date creationDate = new Date();

    String city;

    String street;

    String buildingNo;

    Point location;

    @Transient
    public String[] allowedTypes = {"football_pitch"};

    private Map<String, String> objectSettings = new HashMap<String, String>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (!Arrays.stream(allowedTypes).anyMatch(type::equals)) {
            throw new IllegalArgumentException(type);
        }
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Date getCreationDate() {

        return creationDate;
    }

    public void setCreationDate(Date creationDate) {

        this.creationDate = creationDate;
    }

    public Map<String, String> getObjectSettings() {

        return objectSettings;
    }

    public void setObjectSettings(Map<String, String> objectSettings) {

        this.objectSettings = objectSettings;
    }

    public String[] getAllowedTypes() {

        return allowedTypes;
    }

    public void setAllowedTypes(String[] allowedTypes) {

        this.allowedTypes = allowedTypes;
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public String getStreet() {

        return street;
    }

    public void setStreet(String street) {

        this.street = street;
    }

    public String getBuildingNo() {

        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {

        this.buildingNo = buildingNo;

    }

    public Point getLocation() {

        return location;
    }

    public void setLocation(Point location) {

        this.location = location;
    }
}
