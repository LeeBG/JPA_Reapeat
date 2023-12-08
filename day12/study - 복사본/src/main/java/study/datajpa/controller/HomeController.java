package study.datajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.http.HttpRequest;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
