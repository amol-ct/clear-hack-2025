package com.example.invoice_pdf.dto;

import java.util.List;

public class Po {
    private String poId;
    private List<PoLineItem> lineItems;

    public Po() {
    }

    public void setPoId(String poId) {
        this.poId = poId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Po)) return false;
        final Po other = (Po) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$poId = this.getPoId();
        final Object other$poId = other.getPoId();
        if (this$poId == null ? other$poId != null : !this$poId.equals(other$poId)) return false;
        final Object this$lineItems = this.getLineItems();
        final Object other$lineItems = other.getLineItems();
        if (this$lineItems == null ? other$lineItems != null : !this$lineItems.equals(other$lineItems)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Po;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $poId = this.getPoId();
        result = result * PRIME + ($poId == null ? 43 : $poId.hashCode());
        final Object $lineItems = this.getLineItems();
        result = result * PRIME + ($lineItems == null ? 43 : $lineItems.hashCode());
        return result;
    }

    public String toString() {
        return this.getPoId() + "," + this.getLineItems() ;
    }

    public String getPoId() {
        return this.poId;
    }

    public List<PoLineItem> getLineItems() {
        return this.lineItems;
    }

    public void setLineItems(List<PoLineItem> lineItems) {
        this.lineItems = lineItems;
    }
}