package com.example.invoice_pdf.util;
import com.example.invoice_pdf.dto.Po;
import com.example.invoice_pdf.dto.PoLineItem;
import com.example.invoice_pdf.dto.Grn;
import com.example.invoice_pdf.dto.GrnLineItem;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JsonGenerator {

    private final OpenAIClient openAIClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public JsonGenerator(OpenAIClient openAIClient, ObjectMapper objectMapper) {
        this.openAIClient = openAIClient;
        this.objectMapper = objectMapper;
    }

    // Fetch item names using OpenAI's API
    private List<String> fetchRandomItemNames(int count) {
        String response = openAIClient.generateItemNames(count);
        return Optional.ofNullable(response)
                .map(res -> Arrays.stream(res.split(","))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toList()))
                .orElseGet(() -> Arrays.asList(
                        "Office Chair", "Desk Lamp", "Printer Paper", "Stapler", "File Cabinet",
                        "Whiteboard", "Laptop Stand", "Paper Clips", "Desk Calendar", "USB Drive"
                ));
    }

    // Fetch item IDs using OpenAI's API
    private List<String> fetchRandomItemIds(int count) {
        String response = openAIClient.generateItemIds(count);
        return Optional.ofNullable(response)
                .map(res -> Arrays.stream(res.split(","))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toList()))
                .orElseGet(() -> {
                    List<String> ids = new ArrayList<>();
                    for (int i = 1; i <= count; i++) {
                        ids.add(String.format("PO%04d", i));
                    }
                    return ids;
                });
    }

    // Generate Purchase Order Data and return as JSON
    public List<Po> generatePoData() throws IOException {
        List<Po> pos = new ArrayList<>();
        List<String> items = new ArrayList<>();
        List<String> itemIds = new ArrayList<>();

        // Retry mechanism for getting items and IDs
        for (int retry = 0; retry < 3 && items.size() < 10; retry++) {
            items = fetchRandomItemNames(10);
        }

        for (int retry = 0; retry < 3 && itemIds.size() < 10; retry++) {
            itemIds = fetchRandomItemIds(10);
        }

        // Ensure we have at least 10 items and IDs
        while (items.size() < 10) {
            items.add("Default Item " + (items.size() + 1));
        }

        while (itemIds.size() < 10) {
            itemIds.add(String.format("PO%04d", itemIds.size() + 1));
        }

        System.out.println("Generated Items: " + items);
        Random random = new Random();

        // Create root JSON node
        ArrayNode poArray = objectMapper.createArrayNode();

        // Generate 10 PO records
        for (int i = 0; i < 10; i++) {
            try {
                Po po = new Po();
                po.setPoId(itemIds.get(i));

                // Create multiple line items for each PO
                List<PoLineItem> lineItems = new ArrayList<>();

                // Generate 3-5 line items per PO
                int numberOfLineItems = random.nextInt(3) + 3;

                for (int j = 0; j < numberOfLineItems; j++) {
                    PoLineItem lineItem = new PoLineItem();
                    lineItem.setItemName(items.get(random.nextInt(items.size())));
                    lineItem.setQuantity(random.nextInt(91) + 10);  // 10-100
                    lineItem.setPrice(random.nextInt(101) + 50);    // 50-150
                    lineItems.add(lineItem);

                    // Add line item to JSON
                    ObjectNode poNode = objectMapper.createObjectNode();
                    poNode.put("POId", po.getPoId());
                    poNode.put("LineItemNumber", j + 1);
                    poNode.put("Item", lineItem.getItemName());
                    poNode.put("Quantity", lineItem.getQuantity());
                    poNode.put("Price", lineItem.getPrice());
                    poArray.add(poNode);
                }

                po.setLineItems(lineItems);
                pos.add(po);
            } catch (Exception e) {
                System.err.println("Error generating PO " + i + ": " + e.getMessage());
            }
        }

        // Write PO data to JSON file
        try {
            File file = new File("purchase_orders.json");
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, poArray);
        } catch (IOException e) {
            System.err.println("Error writing PO data to file: " + e.getMessage());
        }

        return pos;
    }

    // Generate Goods Receipt Note (GRN) Data based on PO data
    public List<Grn> generateGrnData(List<Po> poData) throws IOException {
        List<Grn> grns = new ArrayList<>();
        Random random = new Random();
        ArrayNode grnArray = objectMapper.createArrayNode();
        int grnCounter = 1;

        if (poData == null || poData.isEmpty()) {
            System.err.println("No PO data available for GRN generation");
            return grns;
        }

        for (Po po : poData) {
            try {
                if (po != null && po.getLineItems() != null && !po.getLineItems().isEmpty()) {
                    Grn grn = new Grn();
                    grn.setGrnId("GRN" + grnCounter++);
                    List<GrnLineItem> grnLineItems = new ArrayList<>();

                    for (PoLineItem poLineItem : po.getLineItems()) {
                        if (poLineItem != null) {
                            int remainingQuantity = poLineItem.getQuantity();

                            while (remainingQuantity > 0) {
                                GrnLineItem lineItem = new GrnLineItem();
                                lineItem.setPoId(po.getPoId());
                                lineItem.setItemName(poLineItem.getItemName());

                                int maxReceiveQty = Math.min(remainingQuantity, 20);
                                int receivedQty = Math.max(1, random.nextInt(maxReceiveQty) + 1);
                                lineItem.setQuantityReceived(receivedQty);
                                lineItem.setPrice(poLineItem.getPrice());

                                remainingQuantity -= receivedQty;
                                grnLineItems.add(lineItem);

                                ObjectNode grnNode = objectMapper.createObjectNode();
                                grnNode.put("GRNId", grn.getGrnId());
                                grnNode.put("POId", lineItem.getPoId());
                                grnNode.put("Item", lineItem.getItemName());
                                grnNode.put("QuantityReceived", lineItem.getQuantityReceived());
                                grnNode.put("Price", lineItem.getPrice());
                                grnArray.add(grnNode);
                            }
                        }
                    }

                    grn.setLineItems(grnLineItems);
                    grns.add(grn);
                }
            } catch (Exception e) {
                System.err.println("Error generating GRN for PO " + po.getPoId() + ": " + e.getMessage());
            }
        }

        try {
            File file = new File("goods_receipt_notes.json");
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, grnArray);
        } catch (IOException e) {
            System.err.println("Error writing GRN data to file: " + e.getMessage());
        }

        return grns;
    }
}
