package com.example.invoice_pdf.dto;

import java.util.List;

public class Grn {
    private String grnId;
    private List<GrnLineItem> lineItems;

    public Grn() {
    }

    public String getGrnId() {
        return this.grnId;
    }

    public List<GrnLineItem> getLineItems() {
        return this.lineItems;
    }

    public void setGrnId(String grnId) {
        this.grnId = grnId;
    }

    public void setLineItems(List<GrnLineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Grn)) return false;
        final Grn other = (Grn) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$grnId = this.getGrnId();
        final Object other$grnId = other.getGrnId();
        if (this$grnId == null ? other$grnId != null : !this$grnId.equals(other$grnId)) return false;
        final Object this$lineItems = this.getLineItems();
        final Object other$lineItems = other.getLineItems();
        if (this$lineItems == null ? other$lineItems != null : !this$lineItems.equals(other$lineItems)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Grn;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $grnId = this.getGrnId();
        result = result * PRIME + ($grnId == null ? 43 : $grnId.hashCode());
        final Object $lineItems = this.getLineItems();
        result = result * PRIME + ($lineItems == null ? 43 : $lineItems.hashCode());
        return result;
    }

    public String toString() {
        return "Grn(grnId=" + this.getGrnId() + ", lineItems=" + this.getLineItems() + ")";
    }
}
