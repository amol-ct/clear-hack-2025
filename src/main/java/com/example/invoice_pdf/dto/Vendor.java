package com.example.invoice_pdf.dto;

public class Vendor {
    private String vendorCode;
    private String vendorName;

    public Vendor() {
    }

    public String getVendorCode() {
        return this.vendorCode;
    }

    public String getVendorName() {
        return this.vendorName;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Vendor)) return false;
        final Vendor other = (Vendor) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$vendorCode = this.getVendorCode();
        final Object other$vendorCode = other.getVendorCode();
        if (this$vendorCode == null ? other$vendorCode != null : !this$vendorCode.equals(other$vendorCode))
            return false;
        final Object this$vendorName = this.getVendorName();
        final Object other$vendorName = other.getVendorName();
        if (this$vendorName == null ? other$vendorName != null : !this$vendorName.equals(other$vendorName))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Vendor;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $vendorCode = this.getVendorCode();
        result = result * PRIME + ($vendorCode == null ? 43 : $vendorCode.hashCode());
        final Object $vendorName = this.getVendorName();
        result = result * PRIME + ($vendorName == null ? 43 : $vendorName.hashCode());
        return result;
    }

    public String toString() {
        return "Vendor(vendorCode=" + this.getVendorCode() + ", vendorName=" + this.getVendorName() + ")";
    }
}
