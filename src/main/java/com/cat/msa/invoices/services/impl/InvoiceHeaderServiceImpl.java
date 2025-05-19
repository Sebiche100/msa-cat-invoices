package com.cat.msa.invoices.services.impl;

import com.cat.msa.invoices.domain.InvoiceHeader;
import com.cat.msa.invoices.exception.DuplicateException;
import com.cat.msa.invoices.exception.NoContentException;
import com.cat.msa.invoices.exception.NotFoundException;
import com.cat.msa.invoices.repository.InvoiceHeaderRepository;
import com.cat.msa.invoices.services.InvoiceHeaderService;
import org.springframework.stereotype.Service;

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
        boolean exists = invoiceHeaderRepository.findByNumber(invoiceHeader.getNumber()).isPresent();
        if (exists) {
            throw new DuplicateException("Invoice with number " + invoiceHeader.getNumber() + " already exists.");
        }
        invoiceHeader.calculateInvoiceAmounts();
        return invoiceHeaderRepository.save(invoiceHeader);
    }

    @Override
    public List<InvoiceHeader> getAll() {
        List<InvoiceHeader> invoiceHeaders = invoiceHeaderRepository.findAll();
        if (invoiceHeaders.isEmpty()) {
            throw new NoContentException();
        }
        return invoiceHeaders;
    }
    
    @Override
    public Optional<InvoiceHeader> findByNumber(String number) {
        Optional<InvoiceHeader> existingInvoiceHeader = invoiceHeaderRepository.findByNumber(number);
        if (existingInvoiceHeader.isEmpty()) {
            throw new NoContentException();
        }
        return existingInvoiceHeader;
    }

    @Override
    public void deleteByNumber(String number) {
        invoiceHeaderRepository.findByNumber(number).ifPresentOrElse(
                invoiceHeaderRepository::delete,
                () -> {throw new NotFoundException("Cannot delete: invoice with number " + number + " not found.");}
        );
    }
}
