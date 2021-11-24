package com.management.student.studentresult.utils.pdfutils;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TransparentColor;
import com.management.student.studentresult.enums.ResultComponentEnum;
import com.management.student.studentresult.vo.MarksVO;
import com.management.student.studentresult.vo.UserDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import javax.print.attribute.standard.PagesPerMinute;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class ResultPdfBuilderTester {

    @Mock
    private ResultPdfComponentCreator pdfComponentCreator;

    @Mock
    private ResultPdfUtils resultPdfUtils;

    @InjectMocks
    private ResultPdfBuilder resultPdfBuilder;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    private MarksVO getMarksVO(){
        return new MarksVO("MT2020093", "sub01",
                "subject", 2010, 1, 100,
                90.0, "A+");
    }
    private List<MarksVO> getMarksVOList(){
        List<MarksVO> marksVOList = new ArrayList<>();
        marksVOList.add(getMarksVO());
        marksVOList.add(getMarksVO());
        marksVOList.add(getMarksVO());
        return marksVOList;
    }

    @Test
    public void createDocument(){
        PdfWriter writer = new PdfWriter(new ByteArrayOutputStream());
        ResultPdfBuilder builder = this.resultPdfBuilder.createNewDocument(writer);
        Assertions.assertNotNull(builder);
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

    public void buildDocumentTest(){
        PdfWriter writer = new PdfWriter(new ByteArrayOutputStream());
        Document document = this.resultPdfBuilder.createNewDocument(writer).build();
        Assertions.assertNotNull(document);
    }

    private void mockForResultPdfCreation(){
        Mockito.when(this.pdfComponentCreator
                .createSectionHeader(anyString()))
                .thenReturn(new Paragraph());
        Mockito.when(this.pdfComponentCreator.createMarksDetail(any(TreeMap.class)))
                .thenReturn(new Table(new float[]{10, 10}));
        Mockito.when(resultPdfUtils.createMarksDetails(any(List.class)))
                .thenReturn(new TreeMap<Integer, TreeMap<Integer, List<MarksVO>>>());
        Mockito.when(resultPdfUtils.createBasicDetails(any(UserDetails.class)))
                .thenReturn(new LinkedHashMap<ResultComponentEnum, String>());
        Mockito.when(pdfComponentCreator.createBasicDetailInfo(any(LinkedHashMap.class)))
                .thenReturn(new Table(new float[]{10, 10}));
    }


    @Test
    public void addMarksDetails(){
        mockForResultPdfCreation();
        PdfWriter writer = new PdfWriter(new ByteArrayOutputStream());
        ResultPdfBuilder resultPdfBuilderIntermediate = this.resultPdfBuilder.createNewDocument(writer)
                .addMarksDetails(getMarksVOList());
        Assertions.assertNotNull(resultPdfBuilderIntermediate);
        Document document = resultPdfBuilderIntermediate.build();
        Assertions.assertNotNull(document);

    }

    @Test
    public void addHeaderTest(){
        mockForResultPdfCreation();
        PdfWriter writer = new PdfWriter(new ByteArrayOutputStream());
        ResultPdfBuilder resultPdfBuilderIntermediate = this.resultPdfBuilder.createNewDocument(writer)
                .addMarksDetails(getMarksVOList())
                .addHeader("header");
        Assertions.assertNotNull(resultPdfBuilderIntermediate);
        Document document = resultPdfBuilderIntermediate.build();
        Assertions.assertNotNull(document);

        resultPdfBuilderIntermediate = this.resultPdfBuilder.createNewDocument(writer)
                .addMarksDetails(getMarksVOList())
                .addHeader("header", 1);
        Assertions.assertNotNull(resultPdfBuilderIntermediate);
        document = resultPdfBuilderIntermediate.build();
        Assertions.assertNotNull(document);
    }

    @Test
    public void addFooterTest(){
        mockForResultPdfCreation();
        PdfWriter writer = new PdfWriter(new ByteArrayOutputStream());
        ResultPdfBuilder resultPdfBuilderIntermediate = this.resultPdfBuilder.createNewDocument(writer)
                .addMarksDetails(getMarksVOList())
                .addFooter("Footer");
        Assertions.assertNotNull(resultPdfBuilderIntermediate);
        Document document = resultPdfBuilderIntermediate.build();
        Assertions.assertNotNull(document);

        resultPdfBuilderIntermediate = this.resultPdfBuilder.createNewDocument(writer)
                .addMarksDetails(getMarksVOList())
                .addFooter("Footer", 1);
        Assertions.assertNotNull(resultPdfBuilderIntermediate);
        document = resultPdfBuilderIntermediate.build();
        Assertions.assertNotNull(document);
    }

    @Test
    public void addBasicInfoBlockTest(){
        mockForResultPdfCreation();
        UserDetails userDetails = new UserDetails("Aditya Saha", "MT2020093", null, "address", "9009009009",
                "sample@gmail.com", "password", -1);
        PdfWriter writer = new PdfWriter(new ByteArrayOutputStream());
        ResultPdfBuilder resultPdfBuilderIntermediate = this.resultPdfBuilder.createNewDocument(writer)
                .addBasicInfoBlock(userDetails);
        Assertions.assertNotNull(resultPdfBuilderIntermediate);
        Document document = resultPdfBuilderIntermediate.build();
        Assertions.assertNotNull(document);

    }

}
