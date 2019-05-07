package de.mygroup.powercore.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

@Service
public class EncryptionService {

	public String hash(String original) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(original.getBytes());
		byte[] digest = md.digest();
		String hash = DatatypeConverter.printHexBinary(digest);
		return hash;
	}
}