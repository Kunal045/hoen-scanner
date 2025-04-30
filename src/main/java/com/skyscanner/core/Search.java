package com.skyscanner.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Search {
    @JsonProperty
    private String city;

    public Search() {} // Required for Jackson

    public String getCity() {
        return city;
    }
}

