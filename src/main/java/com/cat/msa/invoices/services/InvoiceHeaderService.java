package com.cat.msa.invoices.services;

import com.cat.msa.invoices.domain.InvoiceHeader;
import java.util.Optional;

public interface InvoiceHeaderService {

    InvoiceHeader createInvoiceHeader(InvoiceHeader invoiceHeader);

    Optional<InvoiceHeader> findByNumber(String number);
}
