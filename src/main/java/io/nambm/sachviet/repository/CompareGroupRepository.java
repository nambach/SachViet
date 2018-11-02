package io.nambm.sachviet.repository;

import io.nambm.sachviet.entity.CompareGroup;
import io.nambm.sachviet.repository.generic.GenericRepository;

import java.util.List;

public interface CompareGroupRepository extends GenericRepository<CompareGroup> {

    void updateMembers(CompareGroup group);

    List<CompareGroup> searchByNameOrAuthor(String name);

    List<CompareGroup> searchByIds(List<String> idList);
}
