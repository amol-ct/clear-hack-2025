package com.example.invoice_pdf.dto;

import java.util.HashMap;
import java.util.List;

public class Invoice {
    String InvoiceId;
    List<InvoiceLineitem> lineItemList;
    Integer TotalAmount;
    HashMap<InvoiceLineitem,Integer> map; //amount

    public Integer CalculateTotal(){
        TotalAmount=0;
        for(InvoiceLineitem it:lineItemList){
            int quan=it.quantity;
//            int price=it.Po
//            TotalAmount+=quan*price;
        }
        return TotalAmount;
    }
}
