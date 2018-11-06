package io.nambm.sachviet.controller.impl;

import io.nambm.sachviet.configuration.AppConfig;
import io.nambm.sachviet.controller.LoginController;
import io.nambm.sachviet.utils.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@Controller
@SessionScope
public class LoginControllerImpl implements LoginController {

    private int a = 0;

    @GetMapping("/back-door")
    public ModelAndView defaultPage() {
        return new ModelAndView("login/login");
    }

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("search/search");
    }

    @GetMapping("/admin")
    public ModelAndView admin() {
        ModelAndView modelAndView = new ModelAndView("admin/admin");

        try {
            File file = ResourceUtils.getFile("classpath:static/xsl/book-search.xsl");
            String xsl = FileUtils.readTextContent(file.getAbsolutePath());

            modelAndView.addObject("xsl", xsl);
        } catch (FileNotFoundException ignored) {
        }

        return modelAndView;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login2(@RequestParam String username,
                                         @RequestParam String password) {
        try {
            if ("user".equals(username) && "pwd".equals(password)) {
                AppConfig.getSession().setAttribute("username", "Nam Bach");
                AppConfig.getSession().setAttribute("number", a++);
                return new ResponseEntity<>("success", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/404")
    public ModelAndView notFound() {
        return new ModelAndView("error/404");
    }
}
