/**
 * 
 */
package com.management.student.studentresult.controller;

import com.management.student.studentresult.service.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.management.student.studentresult.service.ModeratorService;
import com.management.student.studentresult.vo.MarksVO;
import com.management.student.studentresult.vo.ResponseMessage;

import java.util.List;

/**
 * @author PRATAP
 *
 */
@RestController
@RequestMapping("/moderator")
@CrossOrigin("*")
public class ModeratorController {

	@Autowired
	private ModeratorService moderatorService;

	@Autowired
	private MarksService marksService;

	@RequestMapping(value = "/bulkUpload", method = RequestMethod.POST)
	public ResponseEntity<?> marksBulkUpload(@RequestParam(name = "file", required = true) MultipartFile fileMarksUpl,
			@RequestParam(name = "extId") String modExitId) {
		ResponseMessage respMsg = null;
		try {
			String response = moderatorService.marksBulkUpload(fileMarksUpl, modExitId);
			respMsg = new ResponseMessage(response);
		} catch (Exception ex) {
			respMsg = new ResponseMessage(ex.getMessage());
			return new ResponseEntity<ResponseMessage>(respMsg, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<ResponseMessage>(respMsg, HttpStatus.OK);
	}

	@RequestMapping(value = "/getUniqueTerms", method = RequestMethod.GET)
	public ResponseEntity<?> uniqueTerms() {
		List<String> response;
		try {
			response = moderatorService.getTerms();
		} catch (Exception ex) {
			String res = ex.getMessage();
			return new ResponseEntity<String>(res, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<String>>(response, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/getListSubjCodeName", method = RequestMethod.GET)
	public ResponseEntity<?> subjCodeName() {
		List<String> response;
		try {
			response = moderatorService.getSubjUseCodeName();
		} catch (Exception ex) {
			String res = ex.getMessage();
			return new ResponseEntity<String>(res, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<String>>(response, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/singleUpload", method = RequestMethod.POST)
	public ResponseEntity<?> marksSingleUpload(@RequestBody MarksVO marksVO,
			@RequestParam(name = "extId") String modExitId) {
		ResponseMessage respMsg = null;
		try {
			String response = moderatorService.marksSingleUpload(marksVO, modExitId);
			respMsg = new ResponseMessage(response);
		} catch (Exception ex) {
			respMsg = new ResponseMessage(ex.getMessage());
			return new ResponseEntity<ResponseMessage>(respMsg, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<ResponseMessage>(respMsg, HttpStatus.OK);
	}

	@RequestMapping(value = "/bulkUpdate", method = RequestMethod.POST)
	public ResponseEntity<?> marksBulkUpdate(@RequestParam(name = "file", required = true) MultipartFile fileMarksUpdt,
			@RequestParam(name = "extId") String modExitId) {
		ResponseMessage respMsg = null;
		try {
			String response = moderatorService.marksBulkUpdate(fileMarksUpdt, modExitId);
			respMsg = new ResponseMessage(response);
		} catch (Exception ex) {
			respMsg = new ResponseMessage(ex.getMessage());
			return new ResponseEntity<ResponseMessage>(respMsg, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<ResponseMessage>(respMsg, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateMarks", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> updateMarks(@RequestBody List<MarksVO> marksVO) {
		List<MarksVO> response;
		try {
			response = marksService.updateMarksQueryResult(marksVO);
		} catch (Exception ex) {
			String res = ex.getMessage();
			return new ResponseEntity<String>(res, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<MarksVO>>(response, HttpStatus.ACCEPTED);
	}
}
