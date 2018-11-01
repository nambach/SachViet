package io.nambm.sachviet.repository;

import io.nambm.sachviet.entity.SuggestGroup;
import io.nambm.sachviet.repository.generic.GenericRepository;

public interface SuggestGroupRepository extends GenericRepository<SuggestGroup> {

    void updateMembers(SuggestGroup group);
}
