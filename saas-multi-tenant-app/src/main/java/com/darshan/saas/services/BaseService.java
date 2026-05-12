package com.darshan.saas.services;

import java.util.List;

public interface BaseService<I, O> {
    void create(final I request);

    void update(final String id, final I request);

    List<O> findAll();

    O findById(final String id);

    void delete(final String id);

}
