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
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.gptool.chartgpt.pojo.Table;
import com.gptool.chartgpt.service.TableService;
import com.gptool.chartgpt.service.utilities.Formatter;
import com.gptool.chartgpt.service.utilities.JSONutil;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;


@RestController
public class ToolController {

    @Autowired
    private TableService tableService;

    @Autowired
    private RestTemplate restTemplate;
    
    @GetMapping("/")
    public String barChart(Model model) {
        return "table_generator.html";
    }

    @GetMapping("/error")
    public String error(Model model) {
        return "error";
    }

    @PostMapping("/getTable")
    public ResponseEntity<JsonNode> getTable(@RequestBody String jsonString) throws JsonMappingException, JsonProcessingException {
        //  Need to reassess what this is doing. Will need to save to JSON folder in this and reassess what casting and conversion is needed
        Object jsonObject = tableService.parseToObject(jsonString , Formatter.OutputObjectType.JsonNode);
        System.out.println("\n\n\n\nSPRINGBOOT JSON node:    " + jsonObject + "\n\n\n\n");
        return new ResponseEntity<JsonNode>((JsonNode)jsonObject, HttpStatus.OK);
        //  could return a Table object, but for now stick to json object
    }

        //  From web Browser
        @PostMapping("/createBarChart/{metric}/{options}")
        public ResponseEntity<String> createBarChart(@PathVariable String metric, @PathVariable String options) {
            //  We need a util funciton to format the characteristics and options, or we can pass them in via the body
            String url = "http://127.0.0.1:5000/bar-chart/"+ metric + "/"+ options;
            try {
                restTemplate.postForEntity(url, null,String.class, metric, options);
            }
            catch (HttpServerErrorException e){
                System.out.println("\n\n\nERROR *** exception thrown ***" + "\n\n" + e.getStatusCode() + "\n\n" + e.getMessage() + "\n\n\n");
            }
            catch (Exception e){
                System.out.println("\n\n\nERROR *** exception thrown ***" + "\n\n" + e.getMessage() + "\n\n\n");
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }


    //  From web Browser
    @PostMapping("/createTable/{characteristics}/{options}")
    public ResponseEntity<String> createTable(@PathVariable String characteristics, @PathVariable String options) {
        //  We need a util funciton to format the characteristics and options, or we can pass them in via the body
        String url = "http://127.0.0.1:5000/table-generator/"+ characteristics + "/"+ options;
        try {
            restTemplate.postForEntity(url, null,String.class, characteristics, options);
        }
        catch (HttpServerErrorException e){
            System.out.println("\n\n\nERROR *** exception thrown ***" + "\n\n" + e.getStatusCode() + "\n\n" + e.getMessage() + "\n\n\n");
        }
        catch (Exception e){
            System.out.println("\n\n\nERROR *** exception thrown ***" + "\n\n" + e.getMessage() + "\n\n\n");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
