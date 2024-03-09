package com.mikhailtoukach.spring.springeshop.service;

import com.mikhailtoukach.spring.springeshop.dao.ProductRepository;
import com.mikhailtoukach.spring.springeshop.dto.ProductDTO;
import com.mikhailtoukach.spring.springeshop.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductMapper mapper = ProductMapper.MAPPER;

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public List<ProductDTO> getAll() {
        return mapper.fromProductList(productRepository.findAll());
    }
}
