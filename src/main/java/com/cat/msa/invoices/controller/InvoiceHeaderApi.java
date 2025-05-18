package com.cat.msa.invoices.controller;

import com.cat.msa.invoices.domain.InvoiceHeader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/invoices-headers")
public interface InvoiceHeaderApi {

    @PostMapping
    ResponseEntity<InvoiceHeader> save(@RequestBody InvoiceHeader invoiceHeader);

    @GetMapping("/{number}")
    ResponseEntity<InvoiceHeader> findByNumber(@PathVariable String number);
}
