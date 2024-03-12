package com.mikhailtoukach.spring.springeshop.service;

import com.mikhailtoukach.spring.springeshop.domain.Bucket;
import com.mikhailtoukach.spring.springeshop.domain.User;
import com.mikhailtoukach.spring.springeshop.dto.BucketDTO;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long> productIds);

    void addProducts(Bucket bucket, List<Long> productIds);

    BucketDTO getBucketByUser(String name);
}
