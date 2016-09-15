package com.bear.web.model;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Created by Apple on 10/09/2016.
 */
@Document(indexName = "car")
public class Car {
    @Field( type = FieldType.Nested)
    private String name;
    @Field( type = FieldType.Nested)
    private String model;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
