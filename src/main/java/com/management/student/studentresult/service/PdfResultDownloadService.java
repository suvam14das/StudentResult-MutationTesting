package com.management.student.studentresult.service;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.management.student.studentresult.dao.User;
import com.management.student.studentresult.utils.pdfutils.PdfDocumentBuilder;
import com.management.student.studentresult.utils.pdfutils.ResultPdfBuilder;
import com.management.student.studentresult.vo.MarksVO;
import com.management.student.studentresult.vo.QueryVO;
import com.management.student.studentresult.vo.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Service
public class PdfResultDownloadService{

    @Autowired
    private UserService userService;

    @Autowired
    private MarksService marksService;

    @Autowired
    private ResultPdfBuilder pdfBuilder;

    public void exportFile(HttpServletResponse response, QueryVO queryVO) throws IOException, ParseException {
        UserDetails userDetails = userService.getUserDetailsByExtId(queryVO.getRollNumber());
        List<MarksVO> marksList = marksService.getMarksDetailsWithoutPagination(queryVO);
        PdfWriter writer = new PdfWriter(response.getOutputStream());
        pdfBuilder.createNewDocument(writer)
                .addHeader("This document is generated from Student Result Management System")
                .addBasicInfoBlock(userDetails)
                .addMarksDetails(marksList)
                .addFooter("This document is generated from Student Result Management System")
                .build();
    }
}
