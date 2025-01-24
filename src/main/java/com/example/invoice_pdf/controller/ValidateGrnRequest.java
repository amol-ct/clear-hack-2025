package com.example.invoice_pdf.controller;

import com.example.invoice_pdf.dto.Grn;
import com.example.invoice_pdf.dto.Po;

public class ValidateGrnRequest
{
    private Po po;
    private Grn grn;

    public Po getPo() {
        return po;
    }

    public void setPo(Po po) {
        this.po = po;
    }

    public Grn getGrn() {
        return grn;
    }

    public void setGrn(Grn grn) {
        this.grn = grn;
    }
}
