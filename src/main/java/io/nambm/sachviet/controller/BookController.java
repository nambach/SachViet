package io.nambm.sachviet.controller;

import io.nambm.sachviet.model.RawBook;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookController {

    ResponseEntity<List<RawBook>> searchBook(String searchValue);
}
