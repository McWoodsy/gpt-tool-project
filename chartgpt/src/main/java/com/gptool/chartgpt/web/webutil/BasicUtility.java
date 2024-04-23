package com.gptool.chartgpt.web.webutil;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.gptool.chartgpt.service.serviceutil.StringFormatter;

@Component
@Primary
public class BasicUtility implements ControllerUtility{

    @Autowired
    private RestTemplate restTemplate;


        public ResponseEntity<JsonNode> createTable(String characteristics, String options) {
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
        @Override
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

    @Override
    public ResponseEntity<List<String>> createColorPalette(@PathVariable String query) {

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

    
}
