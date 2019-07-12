package com.viktoriia.utils;

import java.util.Properties;  

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.viktoriia.entity.DepartmentEntity;
import com.viktoriia.entity.Employee;
import com.viktoriia.entity.EquipmentEntity;
import com.viktoriia.entity.EquipmentTypeEntity;
import com.viktoriia.entity.GoodsEntity;
import com.viktoriia.entity.GoodsTypeEntity;
import com.viktoriia.entity.Order;
import com.viktoriia.entity.OrderStateEntity;
import com.viktoriia.entity.Person;
import com.viktoriia.entity.Shipment;
import com.viktoriia.entity.ShipmentStateEntity;
import com.viktoriia.entity.Storage;
import com.viktoriia.entity.User;
import com.viktoriia.entity.UserRoleEntity;
import com.viktoriia.service.impl.EmployeeServiceImpl;
import com.viktoriia.service.impl.EquipmentServiceImpl;
import com.viktoriia.service.impl.GoodsServiceImpl;
import com.viktoriia.service.impl.OrderServiceImpl;
import com.viktoriia.service.impl.ShipmentServiceImpl;
import com.viktoriia.service.impl.UserServiceImpl;

public class HibernateSessionFactoryUtil {
	
	private static SessionFactory sessionFactory;

	private HibernateSessionFactoryUtil() {
		
	}
	
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				
				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "org.postgresql.Driver");
				settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/ACMEWarehouse");
				settings.put(Environment.USER, "viktoriia");
				settings.put(Environment.PASS, "vikadziuba18");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL9Dialect");
				settings.put(Environment.POOL_SIZE, "100");
				settings.put(Environment.SHOW_SQL, "true");
				//settings.put(Environment.HBM2DDL_AUTO, "create");
				
				configuration.setProperties(settings);
				configuration.addAnnotatedClass(Employee.class);
				configuration.addAnnotatedClass(EquipmentTypeEntity.class);
				configuration.addAnnotatedClass(EquipmentEntity.class);
				configuration.addAnnotatedClass(GoodsEntity.class);
				configuration.addAnnotatedClass(Order.class);
				configuration.addAnnotatedClass(Person.class);
				configuration.addAnnotatedClass(Shipment.class);
				configuration.addAnnotatedClass(Storage.class);
				configuration.addAnnotatedClass(User.class);
				configuration.addAnnotatedClass(UserRoleEntity.class); 
				configuration.addAnnotatedClass(DepartmentEntity.class);
				configuration.addAnnotatedClass(GoodsTypeEntity.class);
				configuration.addAnnotatedClass(OrderStateEntity.class);
				configuration.addAnnotatedClass(ShipmentStateEntity.class);
				
				StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
				sessionFactory = configuration.buildSessionFactory(builder.build());
				
//				EmployeeServiceImpl.insertAllDepartments();
//				EquipmentServiceImpl.insertAllEquipmentTypes();
//				OrderServiceImpl.insertAllOrderStates();
//				ShipmentServiceImpl.insertAllShipmentStates();
//				UserServiceImpl.insertAllUserRoles();
//				GoodsServiceImpl.insertAllGoodsTypes();
			} catch(Exception e) {
				//catch case
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}

}
