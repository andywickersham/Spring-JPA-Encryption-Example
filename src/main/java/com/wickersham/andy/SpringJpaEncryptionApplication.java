package com.wickersham.andy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author 
 *
 */
@SpringBootApplication
@Slf4j
public class SpringJpaEncryptionApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringJpaEncryptionApplication.class, args);
		
		log.info("***** Application Started...");
		
		//Uncomment to get a symmetric encryption key
		//log.info("ENCRYPTION KEY: " + CryptoHelper.generateSymmetricKey());
	}
}
