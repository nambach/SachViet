package io.nambm.sachviet.repository.impl;

import io.nambm.sachviet.model.RawBook;
import io.nambm.sachviet.repository.generic.impl.GenericRepositoryImpl;
import org.hibernate.SessionFactory;

public class RawBookRepositoryImpl extends GenericRepositoryImpl<RawBook> {

    public RawBookRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
