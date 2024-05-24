package com.gptool.chartgpt.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    
    @GetMapping("/")
    public String index() {
        return "html/index.html";
    }

    @GetMapping("/barChart")
    public String barChart() {
        return "html/barChart.html";
    }

    @GetMapping("/tableGenerator")
    public String tableGenerator() {
        return "html/tableGenerator.html";
    }

    @GetMapping("/colorPalette")
    public String colorPalette() {
        return "html/colorPalette.html";
    }

}
