package com.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.dto.Product;

public class ProductDAO implements Serializable{
	
	private HibernateTemplate myTemplate;
	
	public ProductDAO() {
		super();
	}

	public HibernateTemplate getMyTemplate() {
		return myTemplate;
	}

	public void setMyTemplate(HibernateTemplate myTemplate) {
		this.myTemplate = myTemplate;
	}
	
	public boolean insertProduct(Product prd)
	{
		boolean b = false;
		
		try {
			myTemplate.save(prd);
			b = true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return b;
	}
	
	public boolean updateProduct(Product prd) {
		boolean b = false;
		
		try {
			int count = myTemplate.execute(new HibernateCallback<Integer>() {
				@Override
				public Integer doInHibernate(Session session) throws HibernateException {
					Query q = session.createQuery("UPDATE Product p SET p.name = :prname, p.price = :prc WHERE p.id = :pid");
					q.setParameter("prname", prd.getName());
					q.setParameter("prc", prd.getPrice());
					q.setParameter("pid", prd.getId());
					
					return q.executeUpdate();
				}
			});
			
			if(count > 0) b = true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	public boolean deleteProduct(int prdid) {
		boolean b = false;
		
		try {
			int count = myTemplate.execute(new HibernateCallback<Integer>() {
				@Override
				public Integer doInHibernate(Session session) throws HibernateException {
					Query q = session.createQuery("DELETE FROM Product p WHERE p.id = :pid");
					q.setParameter("pid", prdid);
					
					return q.executeUpdate();
				}
			});
			
			if(count > 0) b = true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	public Product searchProduct(int prdid) {
		Product p = null;
		try {
			p = myTemplate.get(Product.class, prdid);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return p;
	}
	
	public List<Product> getAllProducts(){
		List<Product> p = new ArrayList<Product>();
		
		try {
			p = myTemplate.execute(new HibernateCallback<List<Product>>() {
			@Override
			public List<Product> doInHibernate(Session session) throws HibernateException {
				Criteria c= session.createCriteria(Product.class);
				return c.list();
			}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return p;
	}
}
