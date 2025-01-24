package com.example.invoice_pdf.controller;

import com.example.invoice_pdf.dto.Grn;
import com.example.invoice_pdf.dto.Po;
import com.example.invoice_pdf.services.GrnPredictionService;
import com.example.invoice_pdf.services.GrnValidationService;
import com.example.invoice_pdf.util.JsonGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Component
public class GrnController {

    @Autowired
    private GrnValidationService validationService;

    @Autowired
    private JsonGenerator jsonGenerator;

    @Autowired
    private GrnPredictionService predictionService;

//    @GetMapping("/hello")
//    public String hello(@RequestParam(value = "username", defaultValue = "World") String username) {
//        return String.format("Hello %s!", username);
//    }

    @GetMapping("/generate")
    public String generateGrnCsv() {
        try {
            // Generate random PO data
            List<Po> poData = jsonGenerator.generatePoData();

            // Generate random GRN data based on the PO data
            List<Grn> grnData = jsonGenerator.generateGrnData(poData);

            return String.format("PO and GRN data have been successfully generated! Check the files: %s, %s",
                    "goods_receipt_notes.json","purchase_orders.json");
        }
        catch (IOException e) {
            e.printStackTrace();
            return "An error occurred while generating the CSV files.";
        }
    }

    @PostMapping("/validate")
    public String validateGrn(@RequestBody ValidateGrnRequest request) {
        return validationService.validateGrnData(request.getPo(), request.getGrn());
    }

//    @PostMapping("/predict")
//    public String prediction(@RequestBody predictGrnRequest request) {
//        return predictionService.predictGrnData(request.getPo(), request.getGrn());
//    }
}