package com.mikhailtoukach.spring.springeshop.dao;

import com.mikhailtoukach.spring.springeshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
