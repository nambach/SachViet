package io.nambm.sachviet.controller.impl;

import io.nambm.sachviet.configuration.AppConfig;
import io.nambm.sachviet.controller.LoginController;
import io.nambm.sachviet.entity.User;
import io.nambm.sachviet.service.UserService;
import io.nambm.sachviet.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final String USER_ATTR = "user";

    private final UserService userService;

    @Autowired
    public LoginControllerImpl(UserService userService) {
        this.userService = userService;
    }

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

        if (AppConfig.getSession().getAttribute(USER_ATTR) != null) {
            try {
                User user = (User) AppConfig.getSession().getAttribute(USER_ATTR);
                if (!User.ROLE_ADMIN.equals(user.getRole())) {
                    modelAndView.setViewName("redirect:/404");
                    throw new FileNotFoundException("invalid role");
                }

                File file = ResourceUtils.getFile("classpath:static/xsl/book-search.xsl");
                String xsl = FileUtils.readTextContent(file.getAbsolutePath());

                modelAndView.addObject("xsl", xsl);
            } catch (FileNotFoundException ignored) {
            }
        } else {
            modelAndView.setViewName("redirect:/404");
        }

        return modelAndView;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login2(@RequestParam String username,
                                         @RequestParam String password) {
        User user = userService.checkLogin(username, password);
        if (user != null) {
            AppConfig.getSession().setAttribute(USER_ATTR, user);
            return new ResponseEntity<>(user.getRole(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/logout")
    public ModelAndView logout() {
        AppConfig.getSession().removeAttribute(USER_ATTR);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/404")
    public ModelAndView notFound() {
        return new ModelAndView("error/404");
    }
}
