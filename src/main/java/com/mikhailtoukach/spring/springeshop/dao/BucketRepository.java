package com.mikhailtoukach.spring.springeshop.dao;

import com.mikhailtoukach.spring.springeshop.domain.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<Bucket, Long> {
}
