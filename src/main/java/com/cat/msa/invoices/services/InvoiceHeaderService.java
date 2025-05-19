package com.cat.msa.invoices.services;

import com.cat.msa.invoices.domain.InvoiceHeader;
import java.util.Optional;

import java.util.List;

public interface InvoiceHeaderService {

    InvoiceHeader createInvoiceHeader(InvoiceHeader invoiceHeader);

    List<InvoiceHeader> getAll();

    Optional<InvoiceHeader> findByNumber(String number);

    void deleteByNumber (String number);
}
