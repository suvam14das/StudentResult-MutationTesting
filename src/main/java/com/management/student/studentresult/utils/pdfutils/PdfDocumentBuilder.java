package com.management.student.studentresult.utils.pdfutils;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

public abstract class PdfDocumentBuilder {

    public static int FIRSTPAGE = 1;
    public static int LASTPAGE = -1;

    protected Document document;
    protected PdfDocument pdfDoc;

    public abstract PdfDocumentBuilder createNewDocument(PdfWriter writer);
    public abstract PdfDocumentBuilder addHeader(final String headerMessage, final int startPage, final int endPage);
    public abstract PdfDocumentBuilder addFooter(final String footerMessage, final int startPage, final int endPage);

    protected int getEndPageNumber(int endPageNumber){
        return endPageNumber == LASTPAGE ? pdfDoc.getNumberOfPages() : endPageNumber;
    }

    public Document build(){
        document.close();
        return document;
    }
}
