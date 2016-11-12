package com.mirror2.csd.hibernate;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleStateException;
import org.hibernate.cfg.Configuration;

public class SaveData {
	public static void main(String[] ag){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		System.out.println("Created !");
		Session session = sessionFactory.openSession();
		
		UserDetails user = new UserDetails();
		//user.setId(1);
		//user.setUsername("Motin Mia 122");
		user = (UserDetails) session.get(UserDetails.class, 2);
		
		Car car = new Car();
		car.setCarName("Toyota 3");
		user.getAddressList().add(car);
		
		Car car2 = new Car();
		car2.setCarName("Mitsubishi 3");
		user.getAddressList().add(car2);
		
		Car car3 = new Car();
		car3.setCarName("Others 3");
		user.getAddressList().add(car3);
		
		car.setUser(user);
		car2.setUser(user);
		car3.setUser(user);
		

		
		session.beginTransaction();
		try{
			
			session.save(car);
			session.save(car2);
			session.save(car3);
			//session.save(user);
			//session.delete(user);
			//session.merge(user);
			//UserDetails details = (UserDetails) session.get(UserDetails.class, 1);
			//System.out.println(details.getUsername());
			
			/*
			List<UserDetails> list = session.createCriteria(UserDetails.class).list();			
			for(UserDetails li : list){
				System.out.println(li.getUsername());
			}
			*/
		}catch (StaleStateException ex){
			System.out.println("EX= "+ex);
		}
		session.getTransaction().commit();
		session.close();
	}
	
}
