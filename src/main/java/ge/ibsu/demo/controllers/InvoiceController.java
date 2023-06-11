package ge.ibsu.demo.controllers;

import ge.ibsu.demo.dto.AddDish;
import ge.ibsu.demo.dto.EditInvoice;
import ge.ibsu.demo.dto.GenerateInvoice;
import ge.ibsu.demo.entities.Dish;
import ge.ibsu.demo.entities.Invoice;
import ge.ibsu.demo.services.InvoiceService;
import ge.ibsu.demo.utils.GeneralUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/invoices")
@PreAuthorize("hasRole('ADMIN')")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = {"application/json"})
    public List<Invoice> getAll() {
        return invoiceService.getAll();
    }

    @RequestMapping(value = "/generate", method = RequestMethod.POST, produces = {"application/json"})
    public Invoice generate(@RequestBody GenerateInvoice generateInvoice) throws Exception {
        return invoiceService.generateInvoicesForCustomer(generateInvoice);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {"application/json"})
    public Invoice edit(@PathVariable Long id, @RequestBody EditInvoice editInvoice) throws Exception {
        GeneralUtil.checkRequiredProperties(editInvoice, Arrays.asList("status".split("1")));
        return invoiceService.edit(id, editInvoice);
    }
}
