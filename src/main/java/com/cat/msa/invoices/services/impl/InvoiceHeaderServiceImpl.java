package com.cat.msa.invoices.services.impl;

import com.cat.msa.invoices.domain.InvoiceHeader;
import com.cat.msa.invoices.exception.DuplicateException;
import com.cat.msa.invoices.exception.NotContentException;
import com.cat.msa.invoices.repository.InvoiceHeaderRepository;
import com.cat.msa.invoices.services.InvoiceHeaderService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import java.util.List;

@Service
public class InvoiceHeaderServiceImpl implements InvoiceHeaderService {

    private final InvoiceHeaderRepository invoiceHeaderRepository;

    public InvoiceHeaderServiceImpl(InvoiceHeaderRepository invoiceHeaderRepository) {
        this.invoiceHeaderRepository = invoiceHeaderRepository;
    }

    @Override
    public InvoiceHeader createInvoiceHeader(InvoiceHeader invoiceHeader) {
        Optional<InvoiceHeader> invoiceH = this.findByNumber(invoiceHeader.getNumber());
        if (invoiceH.isEmpty()) {
            invoiceHeader.calculateInvoiceAmounts();
            return invoiceHeaderRepository.save(invoiceHeader);
        } else {
            throw new DuplicateException("Duplicate");
        }
    }

    @Override
    public List<InvoiceHeader> getAll() {
        List<InvoiceHeader> invoiceHeaders = invoiceHeaderRepository.findAll();
        if (invoiceHeaders.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return invoiceHeaders;
    }
    
    @Override
    public Optional<InvoiceHeader> findByNumber(String number) {
        return invoiceHeaderRepository.findByNumber(number);
    }

    @Override
    public void deleteByNumber(String number) {
        Optional<InvoiceHeader> invoiceHeader = this.findByNumber(number);
        invoiceHeader.ifPresentOrElse(invoice -> invoiceHeaderRepository.delete(invoice),()->{throw new NotContentException("Invoice Header Not Content");
        });
    }
}
