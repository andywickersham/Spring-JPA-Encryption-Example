package com.wickersham.andy.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wickersham.andy.domain.postgresql.EncryptionExample;
import com.wickersham.andy.service.EncryptionExampleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author "Andy Wickersham"
 *
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/encryptionExample")
public class EncryptionExampleController {
	
	@Autowired
	private EncryptionExampleService encryptionExampleService;
	
	//----------------------------RETRIEVE ALL Encryption Examples WITH PAGING -------------------------------
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<EncryptionExample>> listAllByPage(Pageable pageable) {
		Page<EncryptionExample> encryptedRecords = encryptionExampleService.listAllByPage(pageable);
		
		return new ResponseEntity<Page<EncryptionExample>>(encryptedRecords, HttpStatus.OK);
	}
	
	//----------------------------CREATE Encryption Example Record -------------------------------
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<EncryptionExample> createEncryptionExample(@RequestBody String clearText) {
		log.info("Creating encryption example record");
		
		if(StringUtils.isBlank(clearText)) {
			log.error("Clear text is null");
			return new ResponseEntity<EncryptionExample>(HttpStatus.BAD_REQUEST);
		}
		
		try {
			encryptionExampleService.updateEncryptionExample(clearText);
		} catch (Exception e) {
			log.error("Create encryption example error");
			return new ResponseEntity<EncryptionExample>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<EncryptionExample>(HttpStatus.OK);
	}
	
	//----------------------------UPDATE Encryption Example Record -------------------------------
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<EncryptionExample> updateEncryptionExample(@RequestBody String clearText) {
		log.info("Creating encryption example record");
		
		if(StringUtils.isBlank(clearText)) {
			log.error("Clear text is null");
			return new ResponseEntity<EncryptionExample>(HttpStatus.BAD_REQUEST);
		}
		
		try {
			encryptionExampleService.updateEncryptionExample(clearText);
		} catch (Exception e) {
			log.error("Create encryption example error");
			return new ResponseEntity<EncryptionExample>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<EncryptionExample>(HttpStatus.OK);
	}
}
