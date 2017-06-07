package com.wickersham.andy.domain.jpa.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.apache.commons.lang3.StringUtils;

import com.wickersham.andy.properties.EnvironmentProperties;
import com.wickersham.andy.util.CryptoHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author "Andy Wickersham"
 *
 */
@Converter
@Slf4j
public class JPACryptoConverter implements AttributeConverter<String, String> {
	private CryptoHelper cryptoHelper;
	
	private static final String ENCRYPT = "ENCRYPT";
	
	private static final String DECRYPT = "DECRYPT";
	
	@Override
	public String convertToDatabaseColumn(String sensitive) {
		if (sensitive == null) { // Don't do anything if the value is null
			return null;
		}
		
		return encryptDecryptString(sensitive, ENCRYPT);
	}
	
	@Override
	public String convertToEntityAttribute(String sensitive) {
		if (sensitive == null) {
			return null;
		}
		
		return encryptDecryptString(sensitive, DECRYPT);
	}
	
	/**
	 * 
	 * @param sensitive
	 * @param encryptDecrypt
	 * @return
	 */
	private String encryptDecryptString(String sensitive, String encryptDecrypt) {
		try {
			if (cryptoHelper == null) {
				//log.info("ENCRYPTION_KEY: " + EnvironmentProperties.ENCRYPTION_KEY);
				cryptoHelper = new CryptoHelper(EnvironmentProperties.ENCRYPTION_KEY);
			}
			
			if(StringUtils.equalsIgnoreCase(encryptDecrypt, ENCRYPT)) {
				return cryptoHelper.encrypt(sensitive);
			} else if(StringUtils.equalsIgnoreCase(encryptDecrypt, DECRYPT)) {
				return cryptoHelper.decrypt(sensitive);
			}
		} catch(Exception e) {
			log.error("***** Exception: " + e.getMessage());
			throw new RuntimeException();
		}
		
		return null;
	}
}
