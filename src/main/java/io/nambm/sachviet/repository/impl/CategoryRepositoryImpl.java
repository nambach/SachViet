package io.nambm.sachviet.repository.impl;

import io.nambm.sachviet.entity.Category;
import io.nambm.sachviet.repository.CategoryRepository;
import io.nambm.sachviet.repository.generic.impl.GenericRepositoryImpl;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class CategoryRepositoryImpl extends GenericRepositoryImpl<Category> implements CategoryRepository {

    public CategoryRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
