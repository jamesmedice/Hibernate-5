package com.gft.global.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.gft.global.dao.AbstractDao;
import com.gft.global.dao.CompanyDAO;
import com.gft.global.model.Company;

@Repository("companyDAO")
public class CompanyDAOImpl extends AbstractDao implements CompanyDAO {

    @Override
    public void saveEmployee(Company company) {
	persist(company);
    }

    @Override
    public List<Company> findAllCompanies() {
	Criteria criteria = getSession().createCriteria(Company.class);
	return (List<Company>) criteria.list();
    }

    @Override
    public void deleteCompanyById(Integer company_id) {
	Query query = getSession().createSQLQuery("delete from Company where company_id = :company_id");
	query.setInteger("company_id", company_id);
	query.executeUpdate();
    }

    @Override
    public void deleteCompany(Company company) {
	getSession().delete(company);
    }

    @Override
    public Company findById(Integer id) {
	Criteria criteria = getSession().createCriteria(Company.class);
	criteria.add(Restrictions.eq("company_id", id));
	return (Company) criteria.uniqueResult();
    }

    @Override
    public void updateCompany(Company company) {
	getSession().update(company);
    }

}
