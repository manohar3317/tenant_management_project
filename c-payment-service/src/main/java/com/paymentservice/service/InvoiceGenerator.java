package com.paymentservice.service;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.paymentservice.model.Payment;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class InvoiceGenerator {

    public byte[] generateInvoice(Payment payment) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Tenant Invoice"));
        document.add(new Paragraph("Tenant ID: " + payment.getTenantId()));
        document.add(new Paragraph("Amount Paid: $" + payment.getAmount()));
        document.add(new Paragraph("Method: " + payment.getMethod()));
        document.add(new Paragraph("Status: " + payment.getStatus()));
        document.add(new Paragraph("Date: " + payment.getTimestamp()));

        document.close();
        return baos.toByteArray();
    }
}
