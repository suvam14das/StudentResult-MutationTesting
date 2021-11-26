package com.management.student.studentresult.service;

import com.management.student.studentresult.dao.Marks;
import com.management.student.studentresult.repository.MarksRepository;
import com.management.student.studentresult.service.ModeratorService;
import com.management.student.studentresult.vo.MarksVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
@Transactional
public class ModeratorServiceTest {

    @Autowired
    private ModeratorService moderatorService;
    @Autowired
    private MarksRepository marksRepository;

    @Test
    public void getTermsTest(){
        assertNotEquals(null,moderatorService.getTerms());
    }

    @Test
    public void getSubjUseCodeNameTest(){
        assertNotEquals(null, moderatorService.getSubjUseCodeName());
    }

    @Test
    public void marksSingleUploadTest() throws Exception {

        MarksVO marksVO = new MarksVO("MT2020093", "A101", "", 2022, 4, 100, 85.6,"A-");
        String response = moderatorService.marksSingleUpload(marksVO,"MT2020155");
        assertEquals("Marks Uploaded Successfully", response);
        assertNotEquals(null, response);
    }

    @Test
    public void findExistenceTest() throws Exception {
        MarksVO marksVO = new MarksVO("MT2020093", "A101", "", 2022, 4, 100, 85.6,"A-");

        assertTrue(moderatorService.findExistence(marksVO.getRollNo(), marksVO.getSubjectCode(), false));

    }

    @Test
    public void bulkUploadTest() throws IOException {

        Path path = Paths.get("src/test/java/com/management/student/studentresult/sampleMarks.xls");
        String name = "sampleMarks.xls";
        String originalFileName = "sampleMarks.xls";
        String contentType = "text/csv";
        byte[] content = null;
        content = Files.readAllBytes(path);
        MultipartFile result = new MockMultipartFile(name,
                originalFileName, contentType, content);
    }
}
