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

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author "Andy Wickersham"
 *
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "encryption_example")
public class EncryptionExample implements Serializable {
	
	private static final long serialVersionUID = -7371781304063255782L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "encrypted_text")
	@Convert(converter = JPACryptoConverter.class)
	private @NonNull String encryptedText;
	
	@Column(name = "clear_text")
	private @NonNull String clearText;
	
}
