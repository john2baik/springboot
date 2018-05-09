package com.example.springbootdockdoor.service;

import com.google.cloud.spanner.DatabaseClient;
import org.springframework.stereotype.Service;

@Service
public interface SpannerService {
    DatabaseClient getDatabaseClient();
}
