package com.gptool.chartgpt.web.webutil;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;


public interface ControllerUtility {

    ResponseEntity<JsonNode> createTable(String characteristics, String options);
    ResponseEntity<String> createBarChart(String metric, String options);
    ResponseEntity<List<String>> createColorPalette(String query);

}
