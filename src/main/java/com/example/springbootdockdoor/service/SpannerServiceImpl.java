package com.example.springbootdockdoor.service;

import com.example.springbootdockdoor.model.Door;
import com.google.cloud.spanner.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpannerServiceImpl implements SpannerService {

    public DatabaseClient getDatabaseClient(){
        SpannerOptions options = SpannerOptions.newBuilder().build();
        Spanner spanner = options.getService();


        // Creates a database client
        DatabaseClient dbClient = spanner.getDatabaseClient(DatabaseId.of(
                "dock-doors", "test-spanner", "test-database"));
        return dbClient;
    }
}
