package com.skyscanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.skyscanner.resources.SearchResource;
import com.skyscanner.core.SearchResult;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HoenScannerApplication extends Application<HoenScannerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new HoenScannerApplication().run(args);
    }

    @Override
    public String getName() {
        return "HoenScanner";
    }

    @Override
    public void run(HoenScannerConfiguration hoenScannerConfiguration, Environment environment) throws Exception {
        System.out.println("Welcome to Hoen Scanner!");

        // Load data from JSON files
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<SearchResult>> typeRef = new TypeReference<>() {};

        InputStream hotelsStream = getClass().getClassLoader().getResourceAsStream("hotels.json");
        InputStream carsStream = getClass().getClassLoader().getResourceAsStream("rental_cars.json");

        List<SearchResult> hotels = mapper.readValue(hotelsStream, typeRef);
        List<SearchResult> rentalCars = mapper.readValue(carsStream, typeRef);

        List<SearchResult> allResults = new ArrayList<>();
        allResults.addAll(hotels);
        allResults.addAll(rentalCars);

        // Register the SearchResource
        environment.jersey().register(new SearchResource(allResults));
    }

    @Override
    public void initialize(final Bootstrap<HoenScannerConfiguration> bootstrap) {
        // No initialization needed
    }


}
