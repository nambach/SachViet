package io.nambm.sachviet.repository.impl;

import io.nambm.sachviet.entity.CateRelation;
import io.nambm.sachviet.repository.CateRelationRepository;
import io.nambm.sachviet.repository.generic.impl.GenericRepositoryImpl;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@SessionScope
public class CateRelationRepositoryImpl extends GenericRepositoryImpl<CateRelation> implements CateRelationRepository {

    public CateRelationRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<String> searchBookIdByCategory(String cateId) {
        List<CateRelation> list = searchExactColumn(Collections.singletonList(cateId), "cateId");
        List<String> result;
        result = list.stream().map(CateRelation::getCompareGroupId).collect(Collectors.toList());
        return result;
    }
}
