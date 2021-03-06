package io.nambm.sachviet.repository.impl;

import io.nambm.sachviet.entity.SuggestGroup;
import io.nambm.sachviet.repository.SuggestGroupRepository;
import io.nambm.sachviet.repository.generic.impl.GenericRepositoryImpl;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class SuggestGroupRepositoryImpl extends GenericRepositoryImpl<SuggestGroup> implements SuggestGroupRepository {

    public SuggestGroupRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void updateMembers(SuggestGroup group) {
        this.update(group, "members");
    }
}
