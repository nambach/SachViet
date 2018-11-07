package io.nambm.sachviet.repository;

import io.nambm.sachviet.entity.Traffic;
import io.nambm.sachviet.repository.generic.GenericRepository;

import java.util.List;

public interface TrafficRepository extends GenericRepository<Traffic> {

    void addTrafficForCompareGroup(String ip, String compareGroupId);

    void addTrafficForSearchContent(String ip, String searchContent);

    List<String> getTopFiveCompareGroup();
}
