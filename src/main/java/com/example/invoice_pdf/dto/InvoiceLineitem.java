package com.example.invoice_pdf.dto;


public class InvoiceLineitem {
    String invoiceitemid;
    Po po;
    Grn grn;
    PoLineItem purchaseorderlineitem;
    Integer quantity;

    public InvoiceLineitem() {
    }

    public String getInvoiceitemid() {
        return this.invoiceitemid;
    }

    public Po getPo() {
        return this.po;
    }

    public Grn getGrn() {
        return this.grn;
    }

    public PoLineItem getPurchaseorderlineitem() {
        return this.purchaseorderlineitem;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setInvoiceitemid(String invoiceitemid) {
        this.invoiceitemid = invoiceitemid;
    }

    public void setPo(Po po) {
        this.po = po;
    }

    public void setGrn(Grn grn) {
        this.grn = grn;
    }

    public void setPurchaseorderlineitem(PoLineItem purchaseorderlineitem) {
        this.purchaseorderlineitem = purchaseorderlineitem;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof InvoiceLineitem)) return false;
        final InvoiceLineitem other = (InvoiceLineitem) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$invoiceitemid = this.getInvoiceitemid();
        final Object other$invoiceitemid = other.getInvoiceitemid();
        if (this$invoiceitemid == null ? other$invoiceitemid != null : !this$invoiceitemid.equals(other$invoiceitemid))
            return false;
        final Object this$po = this.getPo();
        final Object other$po = other.getPo();
        if (this$po == null ? other$po != null : !this$po.equals(other$po)) return false;
        final Object this$grn = this.getGrn();
        final Object other$grn = other.getGrn();
        if (this$grn == null ? other$grn != null : !this$grn.equals(other$grn)) return false;
        final Object this$purchaseorderlineitem = this.getPurchaseorderlineitem();
        final Object other$purchaseorderlineitem = other.getPurchaseorderlineitem();
        if (this$purchaseorderlineitem == null ? other$purchaseorderlineitem != null : !this$purchaseorderlineitem.equals(other$purchaseorderlineitem))
            return false;
        final Object this$quantity = this.getQuantity();
        final Object other$quantity = other.getQuantity();
        if (this$quantity == null ? other$quantity != null : !this$quantity.equals(other$quantity)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof InvoiceLineitem;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $invoiceitemid = this.getInvoiceitemid();
        result = result * PRIME + ($invoiceitemid == null ? 43 : $invoiceitemid.hashCode());
        final Object $po = this.getPo();
        result = result * PRIME + ($po == null ? 43 : $po.hashCode());
        final Object $grn = this.getGrn();
        result = result * PRIME + ($grn == null ? 43 : $grn.hashCode());
        final Object $purchaseorderlineitem = this.getPurchaseorderlineitem();
        result = result * PRIME + ($purchaseorderlineitem == null ? 43 : $purchaseorderlineitem.hashCode());
        final Object $quantity = this.getQuantity();
        result = result * PRIME + ($quantity == null ? 43 : $quantity.hashCode());
        return result;
    }

    public String toString() {
        return "InvoiceLineitem(invoiceitemid=" + this.getInvoiceitemid() + ", po=" + this.getPo() + ", grn=" + this.getGrn() + ", purchaseorderlineitem=" + this.getPurchaseorderlineitem() + ", quantity=" + this.getQuantity() + ")";
    }
}
