package com.gptool.chartgpt.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gptool.chartgpt.service.TableService;
import com.gptool.chartgpt.service.serviceutil.StringFormatter;
import com.gptool.chartgpt.web.webutil.BasicUtility;
import com.gptool.chartgpt.web.webutil.ControllerUtility;



@RestController
public class ToolAPIController {

    @Autowired
    private ControllerUtility controllerUtility;

    // @Autowired
    // private ObjectMapper objectMapper;


    @PostMapping("/createColorPalette/{query}")
    public ResponseEntity<List<String>> createColorPalette(@PathVariable String query) {
        return controllerUtility.createColorPalette(query);
    }


    @PostMapping("/createBarChart/{metric}/{options}")
    public ResponseEntity<String> createBarChart(@PathVariable String metric, @PathVariable String options) {
            return controllerUtility.createBarChart(metric,options);
    }


    @PostMapping("/createTable/{characteristics}/{options}")
    public ResponseEntity<JsonNode> createTable(@PathVariable String characteristics, @PathVariable String options) throws JsonProcessingException { 
        // ResponseEntity<JsonNode> responseResponseEntity = controllerUtility.createTable(characteristics, options);
        // JsonNode response = responseResponseEntity.getBody();
        // String reponseString = objectMapper.writeValueAsString(response);
        

        //  REASESS THE STRUCTURE AND GO DOWN THE LINE TO HAVE THIS HAPPENING LOWER DOWN
        //  THEN ADD A PRINT STATEMENT SOMEWHERE TO VERIFY THAT EACH ENTRY OBJECT IS BEING CORRECTLY SAVED


        return controllerUtility.createTable(characteristics, options);
     
    }

}
