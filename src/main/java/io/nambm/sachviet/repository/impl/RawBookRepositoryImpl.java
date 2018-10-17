package io.nambm.sachviet.repository.impl;

import io.nambm.sachviet.model.RawBook;
import io.nambm.sachviet.repository.RawBookRepository;
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
public class RawBookRepositoryImpl extends GenericRepositoryImpl<RawBook> implements RawBookRepository {

    public RawBookRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<RawBook> searchByNameOrAuthor(String name) {
        List<RawBook> list = new ArrayList<>();

        if (name == null || name.trim().equals("")) {
            return list;
        }

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<RawBook> query = session.createQuery("FROM " + tableName + " b WHERE b.title LIKE :search_value OR b.authors LIKE :search_value", RawBook.class);

            query.setParameter("search_value", "%" + name + "%");

            list = query.list();

            session.close();
        } catch (Exception ignored) {
        }
        return list;
    }
}
