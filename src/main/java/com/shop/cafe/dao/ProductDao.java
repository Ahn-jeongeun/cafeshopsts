package com.shop.cafe.dao;

import java.sql.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.shop.cafe.dto.Product;

@Component
public class ProductDao {
	
	@Value("${spring.datasource.driver-class-name}")
	private String DB_DRIVER;
	
	@Value("${spring.datasource.url}")
	private String DB_URL;
	
	@Value("${spring.datasource.username}")
	private String DB_USER;
	
	@Value("${spring.datasource.password}")
	private String DB_PW;
	
	public List<Product> getAllProducts() {
	    List<Product> list = new ArrayList<>();
	    try {
	        Class.forName(DB_DRIVER);
	        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
	             PreparedStatement stmt = con.prepareStatement("SELECT * FROM product");
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	                int prodcode = rs.getInt("prodcode");
	                String prodname = rs.getString("prodname");
	                int price = rs.getInt("price");
	                String pimg = rs.getString("pimg");
	                list.add(new Product(prodname, pimg, prodcode, price));
	            }

	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // 예외 발생 시 콘솔에 출력
	    }
	    return list;
	}
}