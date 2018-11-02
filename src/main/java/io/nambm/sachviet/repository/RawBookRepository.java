package io.nambm.sachviet.repository;

import io.nambm.sachviet.entity.RawBook;
import io.nambm.sachviet.repository.generic.GenericRepository;

import java.util.List;

public interface RawBookRepository extends GenericRepository<RawBook> {

    List<RawBook> searchByNameOrAuthor(String name);

    List<RawBook> searchByIds(List<String> idList);
}
