package com.feedo.rest_web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class ViewController {

    static final String MAIN_TEMPLATE = "index";
    //private final FileDBResource monitorService;

    @GetMapping("/view")
    public String index(Model model) {
        model.addAttribute("name", "John"); // set 'John' value for 'name' attribute
        return MAIN_TEMPLATE;
    }

}
