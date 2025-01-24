package com.example.invoice_pdf.dto;

public class GrnLineItem {
    private String poId;
    private String itemName;
    private int quantityReceived;
    private int price;

    public GrnLineItem() {
    }

    public String getPoId() {
        return this.poId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public int getQuantityReceived() {
        return this.quantityReceived;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPoId(String poId) {
        this.poId = poId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setQuantityReceived(int quantityReceived) {
        this.quantityReceived = quantityReceived;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof GrnLineItem)) return false;
        final GrnLineItem other = (GrnLineItem) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$poId = this.getPoId();
        final Object other$poId = other.getPoId();
        if (this$poId == null ? other$poId != null : !this$poId.equals(other$poId)) return false;
        final Object this$itemName = this.getItemName();
        final Object other$itemName = other.getItemName();
        if (this$itemName == null ? other$itemName != null : !this$itemName.equals(other$itemName)) return false;
        if (this.getQuantityReceived() != other.getQuantityReceived()) return false;
        if (this.getPrice() != other.getPrice()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof GrnLineItem;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $poId = this.getPoId();
        result = result * PRIME + ($poId == null ? 43 : $poId.hashCode());
        final Object $itemName = this.getItemName();
        result = result * PRIME + ($itemName == null ? 43 : $itemName.hashCode());
        result = result * PRIME + this.getQuantityReceived();
        result = result * PRIME + this.getPrice();
        return result;
    }

    public String toString() {
        return "GrnLineItem(poId=" + this.getPoId() + ", itemName=" + this.getItemName() + ", quantityReceived=" + this.getQuantityReceived() + ", price=" + this.getPrice() + ")";
    }
}
