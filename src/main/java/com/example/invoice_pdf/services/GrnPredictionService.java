package com.example.invoice_pdf.services;

import com.example.invoice_pdf.dto.Grn;
import com.example.invoice_pdf.dto.Po;
import com.example.invoice_pdf.util.OpenAIClient;
import org.springframework.stereotype.Service;

@Service
public class GrnPredictionService
{

    private final OpenAIClient openAIClient;

    public GrnPredictionService(OpenAIClient openAIClient) {
        this.openAIClient = openAIClient;
    }

    public String predictGrnData(Po po, Grn grn)
    {
        String poData = po.toString();
        String grnData = grn.toString();

        String validation= openAIClient.validateGrn(poData, grnData);
        return "";
    }


}