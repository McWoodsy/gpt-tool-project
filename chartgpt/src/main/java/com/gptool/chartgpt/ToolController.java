package com.gptool.chartgpt;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.gptool.chartgpt.pojo.Table;
import com.gptool.chartgpt.service.TableService;

@Controller
public class ToolController {

    @Autowired
    private TableService tableService;
    
    // @GetMapping("/")
    // public String index(Model model) {
    //     return "index.html";
    // }

    @GetMapping("/")
    public String barChart(Model model) {
        return "table_generator.html";
    }

    @GetMapping("/error")
    public String error(Model model) {
        return "error";
    }



    @PostMapping("/createTable/{characteristics}/{options}")
    public void createTable(@PathVariable String characteristics, @PathVariable String options) {
        String url = "http://127.0.0.1:5000/table-generator/"+ characteristics + "/"+ options;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JSONObject> response = restTemplate.postForEntity(url, null, JSONObject.class, characteristics, options);
        String responseBody = response.getBody().toString();
        System.out.println("\n\n\n\nSPRINGBOOT RESPONSE BODY:    " + responseBody + "\n\n\n\n");
        //  Render table()?
    } 
}
