package io.nambm.sachviet.repository.impl;

import io.nambm.sachviet.entity.Traffic;
import io.nambm.sachviet.repository.TrafficRepository;
import io.nambm.sachviet.repository.generic.impl.GenericRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@SessionScope
public class TrafficRepositoryImpl extends GenericRepositoryImpl<Traffic> implements TrafficRepository {

    public TrafficRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void addTrafficForCompareGroup(String ip, String compareGroupId) {
        Traffic traffic = new Traffic(ip, System.currentTimeMillis(), compareGroupId, Traffic.RS_COMPARE_GROUP);
        insertOrReplace(traffic);
    }

    public void addTrafficForSearchContent(String ip, String searchContent) {
        Traffic traffic = new Traffic(ip, System.currentTimeMillis(), searchContent, Traffic.RS_SEARCH_CONTENT);
        insertOrReplace(traffic);
    }

    public List<String> getTopFiveCompareGroup() {
        List<String> list = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery("select [resource] from Traffic where resourceType='"
                    + Traffic.RS_COMPARE_GROUP
                    + "' group by [resource] order by COUNT(*) DESC");

            list = query.list();

            list = list.stream().limit(5).collect(Collectors.toList());

            session.close();
            return list;
        } catch (Exception ignored) {
            return list;
        }
    }
}
