package com.gptool.chartgpt;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.gptool.chartgpt.pojo.Table;
import com.gptool.chartgpt.service.JSONutil;
import com.gptool.chartgpt.service.TableService;

@Controller
public class ToolController {

    @Autowired
    private TableService tableService;

    @Autowired
    private RestTemplate restTemplate;
    
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

    // @PostMapping("/getTable")
    // public ResponseEntity<JsonNode> getTable(@RequestBody String jsonString) throws JsonMappingException, JsonProcessingException {


    //     System.out.println("\n\n\n\nSPRINGBOOT RESPONSE BODY:    " + jsonString + "\n\n\n\n");

    //     JsonNode jsonObject = JSONutil.parse(jsonString);

    //     System.out.println("\n\n\n\nSPRINGBOOT JSON node:    " + jsonObject + "\n\n\n\n");

    //     return new ResponseEntity<JsonNode>(jsonObject, HttpStatus.OK);

    //     //  could return a Table object, but for now stick to json objects

    // }


    // Maybe this has to return a table object so we can build the Thymeleaf table?
    @PostMapping("/createTable/{characteristics}/{options}")
    public void createTable(@PathVariable String characteristics, @PathVariable String options) {
        String url = "http://127.0.0.1:5000/table-generator/"+ characteristics + "/"+ options;
        //RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, null,String .class, characteristics, options);
        //System.out.println("\n\n\n\n JUST A CHECK\n\n\n\n" + response);
        String responseBody = response.getBody();
       // System.out.println(responseBody);

       System.out.println("\n\n\n\nSPRINGBOOT RESPONSE BODY:    " + responseBody + "\n\n\n\n");
       // tableService.parseTableFromJSON((JSONObject)response.getBody());
        //  Render table()?
    } 
}
