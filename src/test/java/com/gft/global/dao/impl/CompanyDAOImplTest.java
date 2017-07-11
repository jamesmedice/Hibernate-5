package com.gft.global.dao.impl;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import com.gft.global.model.AppConfig;
import com.gft.global.model.Company;
import com.gft.global.service.CompanyService;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(classes = { AppConfig.class })
public class CompanyDAOImplTest {

    private static final Integer ID_COMPANY_1 = 10;
    private static final Integer ID_COMPANY_5 = 4;

    @Autowired
    private ApplicationContext context;

    @Autowired
    @Qualifier(value = "companyService")
    private CompanyService companyService;

    @Before
    public void init() {

    }

    @Test
    public void findAllCompaniesTest() {
	List<Company> items = companyService.findAllCompanies();
	Assert.notEmpty(items);
    }

    @Test
    public void findCompanyByIdTest() {
	Company item = companyService.findById(ID_COMPANY_1);
	Assert.notNull(item);
    }

    @Test
    public void removeCompanyTest() {
	Company item = companyService.findById(ID_COMPANY_5);
	Assert.notNull(item);
	companyService.deleteCompany(item);
    }

}