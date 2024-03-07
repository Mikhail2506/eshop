package com.mikhailtoukach.spring.springeshop.service;

import com.mikhailtoukach.spring.springeshop.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAll();
}
