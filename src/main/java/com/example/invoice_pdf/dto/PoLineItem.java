package com.example.invoice_pdf.dto;

public class PoLineItem {
    private String itemName;
    private int quantity;
    private int price;

    public PoLineItem() {
    }

    public String getItemName() {
        return this.itemName;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getPrice() {
        return this.price;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof PoLineItem)) return false;
        final PoLineItem other = (PoLineItem) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$itemName = this.getItemName();
        final Object other$itemName = other.getItemName();
        if (this$itemName == null ? other$itemName != null : !this$itemName.equals(other$itemName)) return false;
        if (this.getQuantity() != other.getQuantity()) return false;
        if (this.getPrice() != other.getPrice()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PoLineItem;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $itemName = this.getItemName();
        result = result * PRIME + ($itemName == null ? 43 : $itemName.hashCode());
        result = result * PRIME + this.getQuantity();
        result = result * PRIME + this.getPrice();
        return result;
    }

    public String toString() {
        return "PoLineItem(itemName=" + this.getItemName() + ", quantity=" + this.getQuantity() + ", price=" + this.getPrice() + ")";
    }
}