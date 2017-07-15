package com.example.user.vernehelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 14.07.2017.
 */

public class Disease implements Serializable{

    private String name;
    private List<String> symptoms;
    private String description;

    public Disease() {
        name = "test";
        symptoms = new ArrayList<>();
        symptoms.add("ololo");
        description = "test";
    }

    public Disease(String name, List<String> symptoms, String description) {
        this.name = name;
        this.symptoms = symptoms;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<String> symptoms) {
        this.symptoms = symptoms;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
