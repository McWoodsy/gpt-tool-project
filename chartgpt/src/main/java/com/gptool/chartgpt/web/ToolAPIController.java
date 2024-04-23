package com.gptool.chartgpt.web;
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
import com.gptool.chartgpt.service.utilities.StringFormatter;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;


@RestController
public class ToolAPIController {

    @Autowired
    private TableService tableService;

    @Autowired
    private RestTemplate restTemplate;


    @PostMapping("/createColorPalette/{query}")
    public ResponseEntity<List<String>> createColorPalette(@PathVariable String query) {
       // query = urlFormatter(utilities.StringFormatter.urlFormatter(query));

        String url = "http://127.0.0.1:5000/palette/"+query;

        try {
          
            List<String> response = restTemplate.postForObject(url, null, List.class);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (HttpServerErrorException e){
            System.out.println("\n\n\nERROR *** exception thrown ***" + "\n\n" + e.getStatusCode() + "\n\n" + e.getMessage() + "\n\n\n");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
        catch (Exception e){
            System.out.println("\n\n\nERROR *** exception thrown ***" + "\n\n" + e.getMessage() + "\n\n\n");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
        catch (Exception e){
            System.out.println("\n\n\nERROR *** exception thrown ***" + "\n\n" + e.getMessage() + "\n\n\n");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/createTable/{characteristics}/{options}")
    public ResponseEntity<JsonNode> createTable(@PathVariable String characteristics, @PathVariable String options) {
        //  We need a util funciton to format the characteristics and options, or we can pass them in via the body
        characteristics = StringFormatter.urlFormatter(characteristics);
        options = StringFormatter.urlFormatter(options);
        String url = "http://127.0.0.1:5000/table-generator/"+ characteristics + "/"+ options;
        try {
            //  For rendering, we dont need to return the response, but for making objects and storing it in repo we do need it returned
            JsonNode response = restTemplate.postForObject(url, null,JsonNode.class, characteristics, options);
            //  
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (HttpServerErrorException e){
            System.out.println("\n\n\nERROR *** exception thrown ***" + "\n\n" + e.getStatusCode() + "\n\n" + e.getMessage() + "\n\n\n");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
        catch (Exception e){
            System.out.println("\n\n\nERROR *** exception thrown ***" + "\n\n" + e.getMessage() + "\n\n\n");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}
