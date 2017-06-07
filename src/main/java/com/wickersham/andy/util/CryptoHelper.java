package com.wickersham.andy.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * <p>
 * CryptoHelper encrypts a String using an AES key and the CBC mode.  CBC 
 * requires an Initialization Vector (IV) to randomize encrypted values.  The IV
 * is stored with the encrypted text separated by the split character so it can be used during
 * the decryption proccess.
 * </p>
 * 
 * @author "Andy Wickersham"
 *
 * Modified code provided in the following link
 * https://gist.github.com/twuni/5668121
 */
public class CryptoHelper {
	private Key key;
	
	private static final String ALGORITHM = "AES";
	
	private static final String CIPHER_TRANSFORMATION = "/CBC/PKCS5Padding";
	
	private static final Integer KEY_SIZE = 256;
	
	private static final Integer IV_SIZE = 16;
	
	private static final String SPLIT_CHARACTER = "|";
	
	
	/**
	 * 
	 * @param key
	 */
	public CryptoHelper(String key) {
		byte[] decodedKey = Base64.decodeBase64(key);
		this.key = new SecretKeySpec(decodedKey, 0, decodedKey.length, ALGORITHM);
	}
	
	/**
	 * 
	 * @param key
	 */
	public CryptoHelper(Key key) {
		this.key = key;
	}
	
	public CryptoHelper() throws NoSuchAlgorithmException {
		this(generateSymmetricKey());
	}
	
	public String encrypt(String plainText) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		return encrypt(generateIV(), plainText);
	}
	
	/**
	 * 
	 * @param iv
	 * @param plainText
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	private String encrypt(byte[] iv, String plainText) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] decrypted = plainText.getBytes();
		byte[] encrypted = encrypt(iv, decrypted);
		
		StringBuilder cipherText = new StringBuilder();
		
		cipherText.append(Base64.encodeBase64String(iv));
		cipherText.append(SPLIT_CHARACTER);
		cipherText.append(Base64.encodeBase64String(encrypted));
		
		return cipherText.toString();
	}
	
	/**
	 * 
	 * @param iv
	 * @param plainText
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	private byte[] encrypt(byte[] iv, byte[] plainText) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance(key.getAlgorithm() + CIPHER_TRANSFORMATION);
		//log.info("CIPHER MAX LENGTH: " + Cipher.getMaxAllowedKeyLength(key.getAlgorithm() + CIPHER_TRANSFORMATION));
		cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
		
		return cipher.doFinal(plainText);
	}
	
	/**
	 * 
	 * @param cipherText
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public String decrypt(String cipherText) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String[] parts = cipherText.split("\\" + SPLIT_CHARACTER);
		
		byte[] iv = Base64.decodeBase64(parts[0]);
		byte[] encrypted = Base64.decodeBase64(parts[1]);
		byte[] decrypted = decrypt(iv, encrypted);
		
		return new String(decrypted);
	}
	
	/**
	 * 
	 * @param iv
	 * @param cipherText
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	private byte[] decrypt(byte[] iv, byte[] cipherText) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance(key.getAlgorithm() + CIPHER_TRANSFORMATION);
		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
		
		return cipher.doFinal(cipherText);
	}
	
	/**
	 * 
	 * @return
	 */
	private byte[] generateIV() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] iv = new byte[IV_SIZE];
		secureRandom.nextBytes(iv);
		
		return iv;
	}
	
	/**
	 * 
	 * @return
	 */
	public Key getKey() {
		return key;
	}
	
	public void setKey(Key key) {
		this.key = key;
	}
	
	/**
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String generateSymmetricKey() throws NoSuchAlgorithmException {
		KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
		generator.init(KEY_SIZE);
		
		SecretKey key = generator.generateKey();
		String encodedKey = Base64.encodeBase64String(key.getEncoded());
		
		return encodedKey;
	}
}
