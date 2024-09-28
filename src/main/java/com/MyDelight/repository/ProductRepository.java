package com.MyDelight.repository;

import com.MyDelight.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Products, Integer>{

}
