package com.example.demo;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptDecrypt {
	private static SecretKeySpec secretKey;
	private static byte[] key;
	
	public static void main(String[] args) {
		String aesKey = "J/PYjc1ftDFK5+77U1PB80v2TamokGap5yCIP2YI6tQ=";
		String iv = "gaOr3uvhZEwFeSbRHwlHcg==";
		String encryptedData = encrypt("How are you", aesKey, iv);
		System.out.println(encryptedData);
		String decryptedData = decrypt(encryptedData, aesKey, iv);
		System.out.println(decryptedData);
	}

	public static void setKey(String aesKey) {
		try {
			key = Base64.getDecoder().decode(aesKey);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String encrypt(String strToEncrypt, String secret, String iv) {
		try {
			setKey(secret);
			IvParameterSpec ivspec = new IvParameterSpec(Base64.getDecoder().decode(iv));
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}
  
	public static String decrypt(String strToDecrypt, String secret, String iv) {
		try {
			setKey(secret);
			IvParameterSpec ivspec = new IvParameterSpec(Base64.getDecoder().decode(iv));
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}
	
	public static String generateIV(int blockSize) {
		try {
			SecureRandom randomSecureRandom = SecureRandom.getInstance("SHA1PRNG");

			byte[] iv = new byte[blockSize];
			randomSecureRandom.nextBytes(iv);
			return Base64.getEncoder().encodeToString(iv);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}