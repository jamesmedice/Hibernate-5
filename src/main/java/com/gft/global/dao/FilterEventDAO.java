package com.gft.global.dao;

import java.util.List;

import com.gft.global.model.FilterEvent;

public interface FilterEventDAO {

    void save(FilterEvent filterEvent);

    List<FilterEvent> findAll();

    FilterEvent findById(Integer id);

    void update(FilterEvent filterEvent);

}
