package com.wickersham.andy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wickersham.andy.domain.postgresql.EncryptionExample;

/**
 * 
 * @author "Andy Wickersham"
 *
 */
public interface EncryptionExampleService {
	
	EncryptionExample updateEncryptionExample(String clearText) throws Exception;
	
	EncryptionExample updateEncryptionExample(EncryptionExample encryptionExample) throws Exception;
	
	Page<EncryptionExample> listAllByPage(Pageable pageable);
}
