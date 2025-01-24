package com.example.invoice_pdf.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

@Component
public class OpenAIClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiKey = "";
    private final String url = "https://api.openai.com/v1/chat/completions";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String generateItemNames(int count) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        String prompt = String.format("Generate exactly %d office supply items. Respond with only the items separated by commas. Keep each item name to 2-3 words. The response should be in this exact format: item1, item2, item3", count);

        String jsonPayload = "{"
                + "\"model\": \"gpt-3.5-turbo\","
                + "\"messages\": ["
                + "  {\"role\": \"system\", \"content\": \"You are a helpful assistant that generates exactly the requested number of items. Always respond with exactly the number of items requested, separated by commas.\"},"
                + "  {\"role\": \"user\", \"content\": \"" + prompt + "\"}"
                + "],"
                + "\"temperature\": 0.5,"
                + "\"max_tokens\": 150,"
                + "\"presence_penalty\": 0.0,"
                + "\"frequency_penalty\": 0.0"
                + "}";

        HttpEntity<String> request = new HttpEntity<>(jsonPayload, headers);
        
        try {
            String response = restTemplate.postForObject(url, request, String.class);
            JsonNode jsonNode = objectMapper.readTree(response);
            
            if (jsonNode != null && jsonNode.has("choices") && jsonNode.get("choices").size() > 0) {
                String content = jsonNode.path("choices").get(0).path("message").path("content").asText("");
                
                // Validate that we got the correct number of items
                String[] items = content.split(",");
                if (items.length != count) {
                    return getFallbackItems(count); // Return fallback if count doesn't match
                }
                
                return content;
            }
            return getFallbackItems(count);
        } catch (Exception e) {
            e.printStackTrace();
            return getFallbackItems(count);
        }
    }

    public String generateItemIds(int count) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        String prompt = String.format("Generate exactly %d unique IDs in format 'PO####' where # is a digit. Respond with only the IDs separated by commas.", count);

        String jsonPayload = "{"
                + "\"model\": \"gpt-3.5-turbo\","
                + "\"messages\": ["
                + "  {\"role\": \"system\", \"content\": \"You are a helpful assistant that generates exactly the requested number of unique IDs. Always respond with exactly the number of IDs requested, separated by commas.\"},"
                + "  {\"role\": \"user\", \"content\": \"" + prompt + "\"}"
                + "],"
                + "\"temperature\": 0.5,"
                + "\"max_tokens\": 150,"
                + "\"presence_penalty\": 0.0,"
                + "\"frequency_penalty\": 0.0"
                + "}";

        HttpEntity<String> request = new HttpEntity<>(jsonPayload, headers);
        
        try {
            String response = restTemplate.postForObject(url, request, String.class);
            JsonNode jsonNode = objectMapper.readTree(response);
            
            if (jsonNode != null && jsonNode.has("choices") && jsonNode.get("choices").size() > 0) {
                String content = jsonNode.path("choices").get(0).path("message").path("content").asText("");
                
                // Validate that we got the correct number of IDs
                String[] ids = content.split(",");
                if (ids.length != count) {
                    return getFallbackIds(count);
                }
                
                return content;
            }
            return getFallbackIds(count);
        } catch (Exception e) {
            e.printStackTrace();
            return getFallbackIds(count);
        }
    }

    private String getFallbackItems(int count) {
        List<String> fallbackItems = Arrays.asList(
            "Office Chair", "Desk Lamp", "Printer Paper", "Stapler", "File Cabinet",
            "Whiteboard", "Laptop Stand", "Paper Clips", "Desk Calendar", "USB Drive",
            "Monitor Stand", "Keyboard", "Mouse Pad", "Desk Phone", "Wall Clock"
        );
        
        return fallbackItems.subList(0, Math.min(count, fallbackItems.size()))
                          .stream()
                          .collect(java.util.stream.Collectors.joining(", "));
    }

    private String getFallbackIds(int count) {
        List<String> ids = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            ids.add(String.format("PO%04d", i));
        }
        return String.join(", ", ids);
    }

    public String validateGrn(String poData, String grnData) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        String jsonPayload = "{"
                + "\"model\": \"gpt-3.5-turbo\","
                + "\"messages\": ["
                + "  {\"role\": \"system\", \"content\": \"You are a helpful assistant that validates GRN data.\"},"
                + "  {\"role\": \"user\", \"content\": \"Validate the GRN data against the PO data.\"}"
                + "],"
                + "\"temperature\": 0.7"
                + "}";

        HttpEntity<String> request = new HttpEntity<>(jsonPayload, headers);
        
        try {
            return restTemplate.postForObject(url, request, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error validating GRN data";
        }
    }
}
