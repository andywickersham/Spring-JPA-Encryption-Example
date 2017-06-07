package com.wickersham.andy.domain.postgresql;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wickersham.andy.domain.jpa.converter.JPACryptoConverter;

import lombok.Data;

/**
 * 
 * @author "Andy Wickersham"
 *
 */
@Entity
@Data
@Table(name = "encryption_example")
public class EncryptionExample implements Serializable {
	
	private static final long serialVersionUID = -7371781304063255782L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "encrypted_text")
	@Convert(converter = JPACryptoConverter.class)
	private String encryptedText;
	
	@Column(name = "clear_text")
	private String clearText;
	
}
