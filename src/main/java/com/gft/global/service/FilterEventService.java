package com.gft.global.service;

import java.util.List;

import com.gft.global.model.FilterEvent;

public interface FilterEventService {

    void save(FilterEvent filterEvent);

    List<FilterEvent> findAll();

    FilterEvent findById(Integer id);

    void update(FilterEvent filterEvent);
}
