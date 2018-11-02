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
import java.util.List;

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
        List<CompareGroup> list = new ArrayList<>();

        if (name == null || name.trim().equals("")) {
            return list;
        }

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<CompareGroup> query = session.createQuery("FROM " + tableName + " b WHERE b.title LIKE :search_value OR b.authors LIKE :search_value", CompareGroup.class);

            query.setParameter("search_value", "%" + name + "%");

            list = query.list();

            session.close();
        } catch (Exception ignored) {
        }
        return list;
    }

    public List<CompareGroup> searchByIds(List<String> idList) {
        return searchExactColumn(idList, "groupId");
    }
}
