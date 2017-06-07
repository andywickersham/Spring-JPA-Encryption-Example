package com.wickersham.andy.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wickersham.andy.domain.postgresql.EncryptionExample;
import com.wickersham.andy.domain.postgresql.EncryptionExampleRepository;

/**
 * 
 * @author "Andy Wickersham"
 *
 */
@Service
public class EncryptionExampleServiceImpl implements EncryptionExampleService {
	@Autowired
	private EncryptionExampleRepository encryptionExampleRepo;
	
	@Override
	public Page<EncryptionExample> listAllByPage(Pageable pageable) {
		return encryptionExampleRepo.findAll(pageable);
	}

	@Override
	@Transactional
	public EncryptionExample updateEncryptionExample(EncryptionExample encryptionExample) throws Exception {
		return encryptionExampleRepo.save(encryptionExample);
	}
	
	@Override
	public EncryptionExample updateEncryptionExample(String clearText) throws Exception {
		EncryptionExample newEncryptionExample = new EncryptionExample(clearText, clearText);
		
		return updateEncryptionExample(newEncryptionExample);
	}

	

}
