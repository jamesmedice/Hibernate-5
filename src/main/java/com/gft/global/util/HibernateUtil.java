package com.gft.global.util;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.gft.global.model.Ambassor;
import com.gft.global.model.AuditLog;
import com.gft.global.model.Branch;
import com.gft.global.model.Client;
import com.gft.global.model.Company;
import com.gft.global.model.Director;
import com.gft.global.model.Employee;
import com.gft.global.model.ProfessionalSkills;
import com.gft.global.model.Profile;
import com.gft.global.model.Project;
import com.gft.global.model.Staffing;

public class HibernateUtil {

    static Logger logger = Logger.getLogger(HibernateUtil.class);

    private static final String FAILED_TO_CREATE_SESSION_FACTORY_OBJECT = "Failed to create sessionFactory object.";
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static ServiceRegistry serviceRegistry;

    private static SessionFactory buildSessionFactory() {

	try {
	    Configuration configuration = new Configuration();
	    configuration.configure("hibernate.cfg.xml");
	    configuration.addAnnotatedClass(Ambassor.class);
	    configuration.addAnnotatedClass(AuditLog.class);
	    configuration.addAnnotatedClass(Branch.class);
	    configuration.addAnnotatedClass(Client.class);
	    configuration.addAnnotatedClass(Company.class);
	    configuration.addAnnotatedClass(Director.class);
	    configuration.addAnnotatedClass(Employee.class);
	    configuration.addAnnotatedClass(Profile.class);
	    configuration.addAnnotatedClass(ProfessionalSkills.class);
	    configuration.addAnnotatedClass(Project.class);
	    configuration.addAnnotatedClass(Staffing.class);
  
	    serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
 
	    return configuration.buildSessionFactory(serviceRegistry);

	} catch (Throwable ex) {

	    logger.debug(FAILED_TO_CREATE_SESSION_FACTORY_OBJECT + ex);
	    throw new ExceptionInInitializerError(ex);
	}
    }

    public static SessionFactory getSessionFactory() {
	return sessionFactory;
    }
}
