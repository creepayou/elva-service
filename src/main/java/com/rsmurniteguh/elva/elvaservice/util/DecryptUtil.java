package com.rsmurniteguh.elva.elvaservice.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DecryptUtil {
	public static final String ALGORITHM = "AES/CBC/PKCS5PADDING";

	private DecryptUtil() {}

	public static String decrypt(String cipherText, String keyString)
			throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
			InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hashKey = digest.digest(keyString.getBytes(StandardCharsets.UTF_8));
		byte[] hashIv = Arrays.copyOf(hashKey, 16);

		SecretKeySpec key = new SecretKeySpec(hashKey, "AES");
		IvParameterSpec iv = new IvParameterSpec(hashIv);

		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
		return LZStringUtil.decompressFromEncodedURIComponent(new String(plainText));
	}
}
