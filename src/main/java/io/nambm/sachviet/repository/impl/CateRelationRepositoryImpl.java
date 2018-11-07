package io.nambm.sachviet.repository.impl;

import io.nambm.sachviet.entity.CateRelation;
import io.nambm.sachviet.repository.CateRelationRepository;
import io.nambm.sachviet.repository.generic.impl.GenericRepositoryImpl;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class CateRelationRepositoryImpl extends GenericRepositoryImpl<CateRelation> implements CateRelationRepository {

    public CateRelationRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
