package io.nambm.sachviet.repository;

import io.nambm.sachviet.entity.CateRelation;
import io.nambm.sachviet.repository.generic.GenericRepository;

import java.util.List;

public interface CateRelationRepository extends GenericRepository<CateRelation> {

    List<String> searchBookIdByCategory(String cateId);
}
