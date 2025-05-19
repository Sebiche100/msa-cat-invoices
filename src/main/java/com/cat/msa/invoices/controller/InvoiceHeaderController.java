package com.cat.msa.invoices.controller;

import com.cat.msa.invoices.domain.InvoiceHeader;
import com.cat.msa.invoices.services.InvoiceHeaderService;
import com.cat.msa.invoices.services.impl.InvoiceHeaderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

import java.util.List;

@RestController
public class InvoiceHeaderController implements InvoiceHeaderApi {

    private final InvoiceHeaderService invoiceHeaderService;

    public InvoiceHeaderController(InvoiceHeaderService invoiceHeaderService) {
        this.invoiceHeaderService = invoiceHeaderService;
    }

    @Override
    public ResponseEntity<InvoiceHeader> save(InvoiceHeader invoiceHeader) {
        InvoiceHeader savedInvoiceHeader = invoiceHeaderService.createInvoiceHeader(invoiceHeader);
        return new ResponseEntity<>(savedInvoiceHeader, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<InvoiceHeader>> findAll() {
        List<InvoiceHeader> invoiceHeaders = invoiceHeaderService.getAll();
        return new ResponseEntity<>(invoiceHeaders, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InvoiceHeader> findByNumber(String number) {
        Optional<InvoiceHeader> invoiceOpt = invoiceHeaderService.findByNumber(number);
        return invoiceOpt.map(invoice -> new ResponseEntity<>(invoice, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));

    }

    @Override
    public ResponseEntity<Void> deleteByNumber(String number) {
        invoiceHeaderService.deleteByNumber(number);
        return ResponseEntity.ok().build();
    }
}
