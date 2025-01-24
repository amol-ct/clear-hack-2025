package com.example.invoice_pdf.services;

import com.example.invoice_pdf.dto.Grn;
import com.example.invoice_pdf.dto.Po;
import com.example.invoice_pdf.util.OpenAIClient;
import org.springframework.stereotype.Service;

@Service
public class GrnValidationService {

    private final OpenAIClient openAIClient;

    public GrnValidationService(OpenAIClient openAIClient) {
        this.openAIClient = openAIClient;
    }

    public String validateGrnData(Po po, Grn grn) {
        String poData = po.toString();
        String grnData = grn.toString();

        return openAIClient.validateGrn(poData, grnData);
    }


}

//public class GrnValidationService {
//
//    public String validateGrnData(Po po, Grn grn) {
//        // Implement your validation logic here
//        return "Validation successful!";
//    }
//}