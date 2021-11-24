package com.management.student.studentresult.utils.pdfutils;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.Background;
import com.itextpdf.layout.property.TransparentColor;
import com.management.student.studentresult.enums.ResultComponentEnum;
import com.management.student.studentresult.vo.MarksVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
public class ResultPdfComponentCreatorTest {

    private ResultPdfComponentCreator resultPdfComponentCreator;
    private String getCellTextData(Cell cell){
        return ((Text)((Paragraph)cell.getChildren().get(0)).getChildren().get(0)).getText();
    }
    @BeforeEach
    public void init(){
        this.resultPdfComponentCreator = new ResultPdfComponentCreator();
    }

    @Test
    public void createBasicDetailInfoTest(){
        //this needs to be done
        String name = "XYZ";
        String extId = "3090M2";
        LinkedHashMap<ResultComponentEnum, String> basicInfoDetails = new LinkedHashMap<>();
        basicInfoDetails.put(ResultComponentEnum.BASIC_INFO_NAME, name);
        basicInfoDetails.put(ResultComponentEnum.BASIC_INFO_ROLLNUMBER, extId);
        Table basicInfoTable = this.resultPdfComponentCreator.createBasicDetailInfo(basicInfoDetails);
        Assertions.assertEquals(1, basicInfoTable.getNumberOfRows());
        Assertions.assertEquals(2, basicInfoTable.getNumberOfColumns());
        Cell cell = basicInfoTable.getCell(0,0);
        Table basicInfoContent = (Table) cell.getChildren().get(0);
        Assertions.assertEquals(basicInfoDetails.keySet().size(), basicInfoContent.getNumberOfRows());
        Assertions.assertEquals(2, basicInfoContent.getNumberOfColumns());
        int idx = 0;
        for(ResultComponentEnum key : basicInfoDetails.keySet()){
            Assertions.assertEquals(key.getComponentName(), getCellTextData(basicInfoContent.getCell(idx, 0)));
            Assertions.assertEquals(basicInfoDetails.get(key), getCellTextData(basicInfoContent.getCell(idx, 1)));
            idx++;
        }
    }

    @Test
    public void noBorderCell(){
        Cell cell = this.resultPdfComponentCreator.getNoBorderCell();
        Assertions.assertEquals(Border.NO_BORDER, cell.getProperty(9));
    }

    @Test
    public void noBorderNoFontCell(){
        Cell cell = this.resultPdfComponentCreator.getNoBorderWithFontCell(10);
        Assertions.assertEquals(Border.NO_BORDER, cell.getProperty(9));
        Assertions.assertEquals(java.util.Optional.ofNullable(10.0f).get(), java.util.Optional.ofNullable(cell.getProperty(24)).get());
    }

    @Test
    public void createTableHeaderCellsTest(){
        String cellName = "Header Cell";
        Cell cell = this.resultPdfComponentCreator.createTableHeaderCells(cellName);
        Assertions.assertEquals(cellName, getCellTextData(cell));
        Assertions.assertEquals(Color.WHITE, ((TransparentColor)cell.getProperty(21)).getColor());
        Assertions.assertEquals(java.util.Optional.ofNullable(10.0f), java.util.Optional.ofNullable(cell.getProperty(24)));
        Assertions.assertTrue((Boolean) cell.getProperty(8));
        Assertions.assertEquals(Color.BLUE, ((Background)cell.getProperty(6)).getColor());
    }

    @Test
    public void createSectionHeaderTest(){
        String sectionHeaderValue = "SectionHeader";
        Paragraph sectionHeader = this.resultPdfComponentCreator.createSectionHeader(sectionHeaderValue);
        Text sectionHeaderText = ((Text)sectionHeader.getChildren().get(0));
        Assertions.assertEquals(sectionHeaderValue, sectionHeaderText.getText());
        Assertions.assertTrue((Boolean)sectionHeaderText.getProperty(8));
    }

    @Test
    public void createTableRowCellsTest(){
        String cellName = "cell name";
        Cell cell = this.resultPdfComponentCreator.createTableRowCells(cellName);
        Assertions.assertEquals(cellName,  getCellTextData(cell));
        Assertions.assertEquals(java.util.Optional.ofNullable(10.0f), java.util.Optional.ofNullable(cell.getProperty(24)));
    }

    @Test
    public void createTableSectionHeader(){
        String cellName = "SectionHeader";
        Cell cell = this.resultPdfComponentCreator.createTableSectionHeader(cellName, 2);
        Assertions.assertEquals(cellName,  ((Text)((Paragraph)cell.getChildren().get(0)).getChildren().get(0)).getText());
        Assertions.assertEquals(java.util.Optional.ofNullable(10.0f), java.util.Optional.ofNullable(cell.getProperty(24)));
        Assertions.assertTrue((Boolean)cell.getProperty(8));
        Assertions.assertEquals(1, cell.getRowspan());
    }


    public TreeMap<Integer, TreeMap<Integer, List<MarksVO>>> createMarksDetails(){
        List<MarksVO> marksVOList = IntStream.range(0, 20).mapToObj(x -> {
            MarksVO marksVO = new MarksVO();
            marksVO.setRollNo("MT2020"+x);
            marksVO.setSubjectName("Sub"+x);
            marksVO.setSubjectCode("Code"+x);
            marksVO.setTotalMarks(100);
            marksVO.setMarksObtained(100.00-2*x);
            marksVO.setTerm(x%2+1);
            marksVO.setYear(2000+x%4);
            marksVO.setGrade("A");
            return marksVO;
        } ).collect(Collectors.toList());
        TreeMap<Integer, TreeMap<Integer, List<MarksVO>>> resultDetails = marksVOList.stream()
                .collect(Collectors.groupingBy(MarksVO::getYear, TreeMap::new,
                        Collectors.groupingBy(MarksVO::getTerm, TreeMap::new, Collectors.toList())));
        return resultDetails;
    }
    @Test
    public void createMarksDetailTest(){
        TreeMap<Integer, TreeMap<Integer, List<MarksVO>>> resultDetails = createMarksDetails();
        List<String> headerList = Arrays.asList(ResultComponentEnum.MARKS_INFO_SUBJECT.getComponentName(),
                ResultComponentEnum.MARKS_INFO_MARKSOBTAINED.getComponentName(),
                ResultComponentEnum.MARKS_INFO_TOTMARKS.getComponentName(),
                ResultComponentEnum.MARKS_INFO_GRADE.getComponentName());
        Table marksDetailTable = this.resultPdfComponentCreator.createMarksDetail(resultDetails);
        for(int i=0;i<headerList.size();i++){
            Assertions.assertEquals(headerList.get(i), getCellTextData(marksDetailTable.getCell(0, i)));
        }
        int rowNo = 1;
        for(int year : resultDetails.keySet()){
            for(int term : resultDetails.get(year).keySet()){
                Assertions.assertEquals(year+": Term-"+term, getCellTextData(marksDetailTable.getCell(rowNo, 0)));
                rowNo++;
                List<MarksVO> marksVOList = resultDetails.get(year).get(term);
                for(MarksVO marksVO : marksVOList){
                    Assertions.assertEquals(marksVO.getSubjectCode()+":"+marksVO.getSubjectName(), getCellTextData(marksDetailTable.getCell(rowNo, 0)));
                    Assertions.assertEquals(marksVO.getMarksObtained().toString(), getCellTextData(marksDetailTable.getCell(rowNo, 1)));
                    Assertions.assertEquals(String.valueOf(marksVO.getTotalMarks()), getCellTextData(marksDetailTable.getCell(rowNo, 2)));
                    Assertions.assertEquals(marksVO.getGrade(), getCellTextData(marksDetailTable.getCell(rowNo, 3)));
                    rowNo++;
                }
            }
        }
    }


}
