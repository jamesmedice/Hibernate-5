package com.gft.global.model;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.gft.global.service.CompanyService;
import com.gft.global.service.FilterEventService;
import com.gft.global.util.JsonUtils;

public class AppMainRunner extends EntityProvider {

    static List<Company> companies = null;

    public static void main(String[] args) throws Exception {
	AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

	CompanyService service = (CompanyService) context.getBean("companyService");
	service.saveCompany(getCompany());

	FilterEventService filterEventService = (FilterEventService) context.getBean("filterEventService");

	companies = service.findAllCompanies();
	showProperties(companies);
	try {
	    for (Company item : companies) {
		String json = JsonUtils.serializeAsJsonString(item);

		FilterEvent filterEvent = new FilterEvent();
		filterEvent.setEventdoc(json);
		filterEventService.save(filterEvent);
	    }
	} catch (Exception e) {
	    throw new Exception("FAILURE");
	}
    }

}
