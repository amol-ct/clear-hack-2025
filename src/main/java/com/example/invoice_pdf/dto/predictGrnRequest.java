package com.example.invoice_pdf.dto;

public class predictGrnRequest
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
