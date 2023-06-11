package ge.ibsu.demo.dto;

import ge.ibsu.demo.entities.enums.InvoiceStatus;

public class EditInvoice {
    private InvoiceStatus status;

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }
}
