package com.gft.global.service;

import java.util.List;

import com.gft.global.model.Company;

public interface CompanyService {

    void saveCompany(Company employee);

    List<Company> findAllCompanies();

    void deleteCompanyById(Integer id);

    void deleteCompany(Company company);

    Company findById(Integer id);

    void updateCompany(Company company);
}
