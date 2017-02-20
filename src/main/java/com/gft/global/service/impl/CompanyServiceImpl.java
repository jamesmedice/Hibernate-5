package com.gft.global.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gft.global.dao.CompanyDAO;
import com.gft.global.model.Company;
import com.gft.global.service.CompanyService;

@Service("companyService")
@Transactional
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDAO companyDAO;

    @Override
    public void saveCompany(Company company) {
	companyDAO.saveEmployee(company);
    }

    @Override
    public List<Company> findAllCompanies() {
	return companyDAO.findAllCompanies();
    }

    @Override
    public void deleteCompanyById(Integer id) {
	companyDAO.deleteCompanyById(id);
    }

    @Override
    public Company findById(Integer id) {
	return companyDAO.findById(id);
    }

    @Override
    public void updateCompany(Company company) {
	companyDAO.updateCompany(company);
    }

    @Override
    public void deleteCompany(Company company) {
	companyDAO.deleteCompany(company);
    }

}
