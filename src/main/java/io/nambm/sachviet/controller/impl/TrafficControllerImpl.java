package io.nambm.sachviet.controller.impl;

import io.nambm.sachviet.controller.TrafficController;
import io.nambm.sachviet.entity.CompareGroup;
import io.nambm.sachviet.repository.TrafficRepository;
import io.nambm.sachviet.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TrafficControllerImpl implements TrafficController {

    private final HttpServletRequest request;

    private final TrafficRepository trafficRepository;
    private final BookService bookService;

    @Autowired
    public TrafficControllerImpl(HttpServletRequest request, TrafficRepository trafficRepository, BookService bookService) {
        this.request = request;
        this.trafficRepository = trafficRepository;
        this.bookService = bookService;
    }

    @PostMapping("/traffic/compare")
    public ResponseEntity<String> logCompareTraffic(@RequestParam("value") String compareGroupId) {
        String ip = request.getRemoteAddr();
        trafficRepository.addTrafficForCompareGroup(ip, compareGroupId);
        return new ResponseEntity<>("nothing", HttpStatus.OK);
    }

    @GetMapping("/traffic/search")
    public ResponseEntity<String> logSearchTraffic(@RequestParam("value") String searchContent) {
        String ip = request.getRemoteAddr();
        trafficRepository.addTrafficForSearchContent(ip, searchContent);
        return new ResponseEntity<>("nothing", HttpStatus.OK);
    }

    @GetMapping("/books/top5")
    public ResponseEntity<List<CompareGroup>> getTopBooks() {
        List<CompareGroup> list = bookService.searchTopFiveBooks();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
