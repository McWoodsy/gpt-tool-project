package com.gptool.chartgpt;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ToolController {
    
    @GetMapping("/")
    public String index(Model model) {
        return "index.html";
    }

    @GetMapping("/error")
    public String error(Model model) {
        return "error";
    }

    @PostMapping("/createTable")
    public void createTable() {
        //  Contacts the python endpoint and returns a JSON String
        //  Maybe in here we can also render the table with Thymeleaf or have a funciton that does that from service
    } 
}
