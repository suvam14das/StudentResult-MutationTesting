package com.management.student.studentresult.utils.pdfutils;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TransparentColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;

@SpringBootTest
public class ResultPdfBuilderTester {

    private ResultPdfBuilder resultPdfBuilder;
    @BeforeEach
    public void init(){
        resultPdfBuilder = new ResultPdfBuilder();
    }

    public void createDocument(){
        PdfWriter writer = new PdfWriter(new ByteArrayOutputStream());
        this.resultPdfBuilder.createNewDocument(writer);
    }

    @Test
    public void getCustomHeaderTest(){
        String message = "This is a custom header";
        Paragraph header = this.resultPdfBuilder.getCustomHeader(message);
        Assertions.assertNotNull(header);
        Assertions.assertEquals(java.util.Optional.ofNullable(5.0f), java.util.Optional.ofNullable(header.getProperty(24)));
        Assertions.assertEquals(Color.GRAY, ((TransparentColor)header.getProperty(21)).getColor());
        Assertions.assertEquals(message, ((Text)(header.getChildren().get(0))).getText());
    }

    @Test
    public void getCustomFooterTest(){
        String message = "This is a custom footer";
        Paragraph footer = this.resultPdfBuilder.getCustomFooter(message);
        Assertions.assertNotNull(footer);
        Assertions.assertEquals(java.util.Optional.ofNullable(5.0f), java.util.Optional.ofNullable(footer.getProperty(24)));
        Assertions.assertEquals(Color.GRAY, ((TransparentColor)footer.getProperty(21)).getColor());
        Assertions.assertEquals(message, ((Text)(footer.getChildren().get(0))).getText());
    }

    public void addHeaderTest(){
        String message = "This is a custom header";
        this.resultPdfBuilder.addHeader(message, 2, 3);
        Document document = this.resultPdfBuilder.getDocument();
        //how to test needs to discuss
    }

    public void addFooterTest(){
        String message = "This is a custom header";
        this.resultPdfBuilder.addHeader(message, 2, 3);
        Document document = this.resultPdfBuilder.getDocument();
        //how to test needs to discuss
    }

    //for other 2 methods not testable

}
