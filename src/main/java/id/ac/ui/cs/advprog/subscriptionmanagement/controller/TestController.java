package id.ac.ui.cs.advprog.subscriptionmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("")
public class TestController {
    @GetMapping("")
    public String createUserPage(Model model) {

        return "Homepage";
    }
}