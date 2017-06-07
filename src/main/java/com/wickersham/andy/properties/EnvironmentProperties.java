package com.wickersham.andy.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



/**
 * 
 * @author "Andy Wickersham"
 *
 */
@Component
public class EnvironmentProperties {

	public static String ENCRYPTION_KEY;
	
	public EnvironmentProperties() {
		super();
	}
	
	@Value("${environment.variable.encryption-symmetric-key}")
	private void setEncryptionKey(String key) {
		ENCRYPTION_KEY = key;
	}
}
