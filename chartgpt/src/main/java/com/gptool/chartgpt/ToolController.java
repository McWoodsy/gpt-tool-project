package com.gptool.chartgpt;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ToolController {
    
    @GetMapping("/")
    public String index(Model model) {
        return "hello";
    }

    @GetMapping("/error")
    public String error(Model model) {
        return "error";
    }

}
