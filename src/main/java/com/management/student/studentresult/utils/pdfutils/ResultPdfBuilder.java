package com.management.student.studentresult.utils.pdfutils;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.management.student.studentresult.enums.ResultComponentEnum;
import com.management.student.studentresult.vo.MarksVO;
import com.management.student.studentresult.vo.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.IntStream;

@Component
public class ResultPdfBuilder extends PdfDocumentBuilder{

    @Autowired
    private ResultPdfComponentCreator componentCreator;
    @Autowired
    private ResultPdfUtils resultPdfUtils;

    public Paragraph getCustomHeader(String headerMessage) {
        return new Paragraph(headerMessage)
                .setFontColor(Color.GRAY)
                .setFontSize(5);
    }

    public Paragraph getCustomFooter(String footerMessage) {
        return new Paragraph(footerMessage)
                .setFontColor(Color.GRAY)
                .setFontSize(5);
    }


    public ResultPdfBuilder createNewDocument(PdfWriter writer){
        pdfDoc = new PdfDocument(writer);
        document = new Document(pdfDoc);
        return this;
    }

    public ResultPdfBuilder addBasicInfoBlock(UserDetails userDetails){
        Paragraph basicInfoSectionHeader = componentCreator.createSectionHeader(ResultComponentEnum.BASIC_INFO_SECTION.getComponentName());
        document.add(basicInfoSectionHeader);
        LinkedHashMap<ResultComponentEnum, String> basicDetailInfo = resultPdfUtils.createBasicDetails(userDetails);
        Table basicInfoDetails = componentCreator.createBasicDetailInfo(basicDetailInfo);
        document.add(basicInfoDetails);
        return this;
    }

    public ResultPdfBuilder addMarksDetails(List<MarksVO> marksList){
        Paragraph marksSectionHeader = componentCreator
                .createSectionHeader(ResultComponentEnum.MARKS_INFO_SECTION.getComponentName());
        document.add(marksSectionHeader);
        TreeMap<Integer, TreeMap<Integer, List<MarksVO>>> marksDetails = resultPdfUtils.createMarksDetails(marksList);
        Table marksDetailTable = componentCreator.createMarksDetail(marksDetails);
        document.add(marksDetailTable);
        return this;
    }

    public ResultPdfBuilder addHeader(final String headerMessage){
        return addHeader(headerMessage, FIRSTPAGE, LASTPAGE);
    }

    public ResultPdfBuilder addHeader(final String headerMessage, int startPage){
        return addHeader(headerMessage, startPage, LASTPAGE);
    }

    public ResultPdfBuilder addFooter(final String footerMessage){
        return addFooter(footerMessage, FIRSTPAGE, LASTPAGE);
    }
    public ResultPdfBuilder addFooter(final String footerMessage, int startPage){
        return addFooter(footerMessage, startPage, LASTPAGE);
    }

    public ResultPdfBuilder addHeader(final String headerMessage, final int startPage, final int endPage){
        Paragraph header = getCustomHeader(headerMessage);
        int endPageNumber = getEndPageNumber(endPage);
        includeHeaderFooter(header, startPage, endPageNumber, VerticalAlignment.TOP);
        return this;
    }

    private void includeHeaderFooter(Paragraph content, int startPage, int endPage, VerticalAlignment verticalAlignment){
        IntStream.range(startPage, endPage+1).forEach(pageNumber -> {
            Rectangle pageSize = pdfDoc.getPage(pageNumber).getPageSize();
            float xCordinate = pageSize.getWidth()/2;
            float yCordinate = -1;
            if(verticalAlignment == VerticalAlignment.BOTTOM)
                yCordinate = pageSize.getBottom()+20;
            else if(verticalAlignment == VerticalAlignment.TOP)
                yCordinate = pageSize.getTop()-20;
            document.showTextAligned(content, xCordinate, yCordinate, pageNumber, TextAlignment.CENTER, verticalAlignment, 0);
        });
    }

    public ResultPdfBuilder addFooter(final String footerMessage, final int startPage, final int endPage){
        Paragraph header = getCustomFooter(footerMessage);
        int endPageNumber = getEndPageNumber(endPage);
        includeHeaderFooter(header, startPage, endPageNumber, VerticalAlignment.BOTTOM);
        return this;
    }
}
