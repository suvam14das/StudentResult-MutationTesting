/**
 * 
 */
package com.management.student.studentresult.service;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.management.student.studentresult.dao.Marks;
import com.management.student.studentresult.dao.Subject;
import com.management.student.studentresult.dao.User;
import com.management.student.studentresult.repository.MarksRepository;
import com.management.student.studentresult.repository.SubjectRepository;
import com.management.student.studentresult.repository.UserRepository;
import com.management.student.studentresult.vo.MarksVO;

/**
 * @author PRATAP
 *
 */

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ModeratorService {

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MarksRepository marksRepository;

	public String marksBulkUpload(MultipartFile fileMarksUpl, String modExitId) throws Exception {
		// TODO Auto-generated method stub
		String response = "";
		XSSFWorkbook workbook = null;
		InputStream stream = fileMarksUpl.getInputStream();
		workbook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowItr = sheet.iterator();
		User moderator = userRepository.findByExtId(modExitId);
		while (rowItr.hasNext()) {
			Row row = rowItr.next();
			if (row.getRowNum() == 0)
				continue;
			String rollNo = row.getCell(0).getStringCellValue();
			Double year = row.getCell(1).getNumericCellValue();
			Double term = row.getCell(2).getNumericCellValue();
			String subjectCode = row.getCell(3).getStringCellValue();
			Double totalMarks = row.getCell(4).getNumericCellValue();
			Double marksObtained = row.getCell(5).getNumericCellValue();
			String grade = row.getCell(6).getStringCellValue();
			User student = userRepository.findByExtId(rollNo);
			Subject subject = subjectRepository.findBySubCode(subjectCode);
			boolean checkExistence = findExistence(rollNo, subjectCode, false);
			if (checkExistence) {
				Marks mark = new Marks(student, subject, marksObtained, totalMarks.intValue(), year.intValue(),
						term.intValue(), grade, moderator);
				marksRepository.save(mark);
			}
		}
		response = "Marks successfully uploaded";

		if (workbook != null)
			workbook.close();

		return response;
	}

	public List<String> getTerms() {
		List<String> termList = subjectRepository.findDistinctByTerm();
		return termList;
	}

	public List<String> getSubjUseCodeName() {
		List<String> subjectList = subjectRepository.findSubjectByCodeName();
		return subjectList;
	}

	public String marksSingleUpload(MarksVO marksVO, String modExitId) throws Exception {
		// TODO Auto-generated method stub
		boolean checkExistence = findExistence(marksVO.getRollNo(), marksVO.getSubjectCode(), false);
		if (checkExistence) {
			User student = userRepository.findByExtId(marksVO.getRollNo());
			Subject subject = subjectRepository.findBySubCode(marksVO.getSubjectCode());
			User moderator = userRepository.findByExtId(modExitId);
			Marks marks = new Marks(student, subject, marksVO.getMarksObtained(), marksVO.getTotalMarks(),
					marksVO.getYear(), marksVO.getTerm(), marksVO.getGrade(), moderator);
			marksRepository.save(marks);
		}
		String response = "Marks Uploaded Successfullly";
		return response;
	}

	private boolean findExistence(String rollNo, String subjectCode, boolean updateFlag) throws Exception {
		// TODO Auto-generated method stub
		User student = userRepository.findByExtId(rollNo);
		Subject subject = subjectRepository.findBySubCode(subjectCode);
		if (student == null || subject == null) {
			throw new Exception("Student or Subject not found. Please check and upload.");
		}
		if (!student.getRole().getName().equalsIgnoreCase("STUDENT")) {
			throw new Exception("Student Id is incorrect. Please check.");
		}

		Marks checkMarkExist = marksRepository.findByUserAndSubject(student, subject);
		if (checkMarkExist != null && !updateFlag) {
			throw new Exception("Student found with same subject code. Please check and upload");
		}
		return true;
	}

	public String marksBulkUpdate(MultipartFile fileMarksUpdt, String modExitId) throws Exception {
		// TODO Auto-generated method stub
		String response = "";
		XSSFWorkbook workbook = null;
		InputStream stream = fileMarksUpdt.getInputStream();
		workbook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowItr = sheet.iterator();
		User moderator = userRepository.findByExtId(modExitId);
		while (rowItr.hasNext()) {
			Row row = rowItr.next();
			if (row.getRowNum() == 0)
				continue;
			String rollNo = row.getCell(0).getStringCellValue();
			Double year = row.getCell(1).getNumericCellValue();
			Double term = row.getCell(2).getNumericCellValue();
			String subjectCode = row.getCell(3).getStringCellValue();
			Double totalMarks = row.getCell(4).getNumericCellValue();
			Double marksObtained = row.getCell(5).getNumericCellValue();
			String grade = row.getCell(6).getStringCellValue();
			boolean checkExistence = findExistence(rollNo, subjectCode, true);
			if (checkExistence) {
				User student = userRepository.findByExtId(rollNo);
				Subject subject = subjectRepository.findBySubCode(subjectCode);
				Marks mark = marksRepository.findByUserAndSubjectAndTermAndYear(student, subject, term.intValue(),
						year.intValue());
				if (mark == null) {
					if (workbook != null)
						workbook.close();
					throw new Exception(
							"No mark exist for a stuent with the given Roll number, Subject Code, Term and Year. Please check and Udate the file.");
				}
				mark.setScore(marksObtained);
				mark.setTotScore(totalMarks.intValue());
				mark.setGrade(grade);
				mark.setModifiedBy(moderator);
				marksRepository.save(mark);
			}
		}
		if (workbook != null)
			workbook.close();
		response = "Bulk Update of Marks Successful";
		return response;
	}
}
