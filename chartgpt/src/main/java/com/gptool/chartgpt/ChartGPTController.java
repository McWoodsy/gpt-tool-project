package com.gptool.chartgpt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@SpringBootApplication
public class ChartGPTController {

    public static void main(String[] args) {
        SpringApplication.run(ChartGPTController.class, args);
    }

    // Define your Flask application's base URL
    private static final String FLASK_BASE_URL = "http://localhost:5000";

    @PostMapping("/palette")
    public ResponseEntity<String> promptToPalette(@RequestParam("query") String query) {
        // Make HTTP POST request to Flask application for palette
        RestTemplate restTemplate = new RestTemplate();
        String paletteUrl = FLASK_BASE_URL + "/palette";
        ResponseEntity<String> response = restTemplate.postForEntity(paletteUrl, query, String.class);
        return response;
    }

    @PostMapping("/bar-chart")
    public ResponseEntity<String> promptToBarChart(@RequestParam("options") String options,
                                                   @RequestParam("metric") String metric) {
        // Make HTTP POST request to Flask application for bar chart
        RestTemplate restTemplate = new RestTemplate();
        String barChartUrl = FLASK_BASE_URL + "/bar-chart";
        ResponseEntity<String> response = restTemplate.postForEntity(barChartUrl, options + "&" + metric, String.class);
        return response;
    }

        @GetMapping("/error")
    public String handleError() {
        return "error"; // return the name of your custom error page
    }
}
