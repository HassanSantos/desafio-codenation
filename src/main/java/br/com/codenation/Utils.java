package br.com.codenation;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

	public static String decrypt(int numberOfPlaces, String encrypted) {

		String decrypted = "";

		for (int i = 0; i < encrypted.length(); i++) {

			int asciiTablePosition = encrypted.charAt(i);

			if (asciiTablePosition > 64 && asciiTablePosition < 91)
				asciiTablePosition += 32;

			int newAsciiTablePosition = asciiTablePosition;

			if (asciiTablePosition > 96 && asciiTablePosition < 123) {
				newAsciiTablePosition = asciiTablePosition - 4;

				if (newAsciiTablePosition < 97)
					newAsciiTablePosition += 26;
			}

			decrypted += (char) newAsciiTablePosition;

		}
		return decrypted;
	}

	

	public static String generateSHA1Signature(String text)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.reset();
		digest.update(text.getBytes("utf8"));
		return String.format("%040x", new BigInteger(1, digest.digest()));

	}

}
