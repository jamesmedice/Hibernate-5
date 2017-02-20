package com.gft.global.model;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.gft.global.interceptor.AuditLogInterceptor;
import com.gft.global.util.HibernateUtil;

public class Entities extends EntityProvider {

    static List<Company> companies = null;

    public static void main(String[] args) {
	AuditLogInterceptor interceptor = new AuditLogInterceptor();

	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	SessionBuilder sessionBuilder = sessionFactory.withOptions();
	Session session = sessionBuilder.interceptor(interceptor).openSession();
	interceptor.setSessionBuilder(sessionBuilder);
	interceptor.setSession(session);

	Transaction transaction = session.beginTransaction();

	session.saveOrUpdate(getCompany());
	transaction.commit();

	Query query = session.createQuery("select new Company (name, iva, businessArea, coreBusiness)  from Company");
	companies = query.list();

	Criteria criteria = session.createCriteria(Company.class);
	companies = criteria.list();

	Employee employee = (Employee) session.get(Employee.class, 1);
	employee.setSalary(BigDecimal.valueOf(1500));
	employee.getProfile().setLevel_description("Seasoned");
	transaction = session.beginTransaction();
	session.saveOrUpdate(employee);
	transaction.commit();

	showProperties(companies);
    }

}
