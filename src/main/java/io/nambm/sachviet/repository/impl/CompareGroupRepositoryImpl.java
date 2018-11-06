package io.nambm.sachviet.repository.impl;

import io.nambm.sachviet.entity.CompareGroup;
import io.nambm.sachviet.repository.CompareGroupRepository;
import io.nambm.sachviet.repository.generic.impl.GenericRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@SessionScope
public class CompareGroupRepositoryImpl extends GenericRepositoryImpl<CompareGroup> implements CompareGroupRepository {

    public CompareGroupRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void updateMembers(CompareGroup group) {
        this.update(group, "members");
    }

    public List<CompareGroup> searchByNameOrAuthor(String name) {
        Map<String, String> keyValues = new HashMap<>();
        keyValues.put("title", name);
        keyValues.put("authors", name);
        keyValues.put("keyword", name);

        return searchAlikeColumn(keyValues);
    }

    public List<CompareGroup> searchByIds(List<String> idList) {
        return searchExactColumn(idList, "groupId");
    }
}
