package com.mikhailtoukach.spring.springeshop.service;

import com.mikhailtoukach.spring.springeshop.domain.Bucket;
import com.mikhailtoukach.spring.springeshop.domain.User;
import com.mikhailtoukach.spring.springeshop.dto.BucketDTO;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long> products);

    void addProducts(Bucket bucket, List<Long> products);

    BucketDTO getBucketByUser(String name);
}
