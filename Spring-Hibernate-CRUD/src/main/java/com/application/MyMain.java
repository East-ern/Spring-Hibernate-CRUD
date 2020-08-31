package com.application;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.ProductDAO;
import com.dto.Product;

public class MyMain {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		ProductDAO pdao = (ProductDAO)context.getBean("pdao");
		
		Product p=new Product();
		p.setName("Keyboard");
		p.setPrice(4000);
		
		if(pdao.insertProduct(p)) System.out.println("Saved");
		else System.out.println("Failed");
		

//		Product p=new Product();
//		p.setId(1);
//		p.setName("Mouse");
//		p.setPrice(3000);
//		
//		if(pdao.updateProduct(p)) System.out.println("Updated");
//		else System.out.println("Failed");
		
		
//		if(pdao.deleteProduct(4)) System.out.println("Deleted");
//		else System.out.println("Failed");
		
//		Product p = pdao.searchProduct(3);
//		System.out.println(p.getId() + " : " +p.getName() + " : " + p.getPrice());
		
//		List<Product> p1 = pdao.getAllProducts();
//		for(Product p : p1)
//		{
//			System.out.println(p.getId() + " : " +p.getName() + " : " + p.getPrice());
//		}
		
	}
}
