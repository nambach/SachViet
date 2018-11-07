package io.nambm.sachviet.controller;

import io.nambm.sachviet.entity.CompareGroup;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TrafficController {

    ResponseEntity<String> logCompareTraffic(String asd);

    ResponseEntity<String> logSearchTraffic(String asd);

    ResponseEntity<List<CompareGroup>> getTopBooks();
}
