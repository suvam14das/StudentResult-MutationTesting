package com.management.student.studentresult.utils.pdfutils;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.management.student.studentresult.enums.ResultComponentEnum;
import com.management.student.studentresult.vo.MarksVO;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ResultPdfComponentCreator {


    private Table getBasicInfoContent(float[] keyContentWidth, LinkedHashMap<ResultComponentEnum, String> basicInfoDetails) {
        Table basicInfoTable = new Table(keyContentWidth);
        basicInfoDetails.keySet().stream().forEach(
                key -> {
                    basicInfoTable.addCell(getNoBorderWithFontCell(10).add(key.getComponentName()));
                    basicInfoTable.addCell(getNoBorderWithFontCell(10).add(basicInfoDetails.get(key)));
                }
        );
        return basicInfoTable;
    }

    public Cell getNoBorderCell(){
        return new Cell().setBorder(Border.NO_BORDER);
    }

    public Cell getNoBorderWithFontCell(float fontSize){
        return getNoBorderCell().setFontSize(fontSize);
    }

    public Paragraph createSectionHeader(String sectionHeaderName){
        Text sectionHeaderText = new Text(sectionHeaderName);
        sectionHeaderText.setBold();
        Paragraph sectionHeader = new Paragraph(sectionHeaderText);
        return sectionHeader;
    }

    public Cell createTableHeaderCells(String tableHeaderCellName){
        Cell cell = new Cell();
        cell.add(tableHeaderCellName);
        cell.setBackgroundColor(Color.BLUE);
        cell.setFontColor(Color.WHITE);
        cell.setFontSize(10);
        cell.setBold();
        return cell;
    }

    public Cell createTableRowCells(String cellName){
        Cell cell = new Cell();
        cell.add(cellName);
        cell.setFontSize(10);
        return cell;
    }

    public Cell createTableSectionHeader(String tableSectionHeaderName, int colNumber){
        return new Cell(1,colNumber)
                .add(tableSectionHeaderName)
                .setBold()
                .setFontSize(10);
    }
    public Table createBasicDetailInfo(LinkedHashMap<ResultComponentEnum, String> basicInfoDetails){
        float [] detailsColWidth = {450F, 100F};
        Table basicDetailsTable = new Table(detailsColWidth);
        Table basicInfoContent = getBasicInfoContent(new float[]{150F, 300F}, basicInfoDetails);
        basicDetailsTable.addCell(getNoBorderCell().add(basicInfoContent));
        return basicDetailsTable;
    }

    public void applyTableHeader(Table resultTable, List<String> headerCellNameList){
        headerCellNameList.stream()
                .map(headerCellName -> { return createTableHeaderCells(headerCellName); })
                .forEach(headerCell -> resultTable.addCell(headerCell));
    }

    public void addMarksRow(Table marksTable, MarksVO marksVO){
        StringBuffer subject = new StringBuffer();
        subject.append(marksVO.getSubjectCode()).append(":").append(marksVO.getSubjectName());
        marksTable.addCell(createTableRowCells(subject.toString()));
        marksTable.addCell(createTableRowCells(String.valueOf(marksVO.getMarksObtained())));
        marksTable.addCell(createTableRowCells(String.valueOf(marksVO.getTotalMarks())));
        marksTable.addCell(createTableRowCells(marksVO.getGrade()));
    }

    public void applyTableSection(int term, int year, List<MarksVO> marksList, Table marksTable){
        StringBuffer sectionHeaderValue = new StringBuffer();
        sectionHeaderValue.append(year).append(": Term-").append(term);
        Cell sectionHeader = createTableSectionHeader(sectionHeaderValue.toString(), marksTable.getNumberOfColumns());
        marksTable.addCell(sectionHeader);
        marksList.stream().forEach( marksVO -> addMarksRow(marksTable, marksVO) );
    }

    public Table createMarksDetail(TreeMap<Integer, TreeMap<Integer, List<MarksVO>>> marksDetails){
        float [] resultColWidth = {300F, 100F, 100F, 50F};
        Table  resultTable = new Table(resultColWidth);
        List<String> headerList = Arrays.asList(ResultComponentEnum.MARKS_INFO_SUBJECT.getComponentName(),
                ResultComponentEnum.MARKS_INFO_MARKSOBTAINED.getComponentName(),
                ResultComponentEnum.MARKS_INFO_TOTMARKS.getComponentName(),
                ResultComponentEnum.MARKS_INFO_GRADE.getComponentName());
        applyTableHeader(resultTable, headerList);
        marksDetails.keySet().stream()
                .forEach(year -> {
                            marksDetails.get(year).keySet().stream()
                                    .forEach(term -> {
                                            applyTableSection(term, year, marksDetails.get(year).get(term), resultTable);
                                    });
                });

        return resultTable;
    }


}
