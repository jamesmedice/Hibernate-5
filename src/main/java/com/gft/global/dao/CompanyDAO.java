package com.gft.global.dao;

import java.util.List;

import com.gft.global.model.Company;

public interface CompanyDAO {

    void saveEmployee(Company company);

    List<Company> findAllCompanies();

    void deleteCompanyById(Integer id);

    void updateCompany(Company company);

    void deleteCompany(Company company);

    Company findById(Integer id);

}
