package com.earthlyfish.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {

	public static final String ALGORITHM_NAME = "SHA-512";// 算法
	public static final String ENCRYPT_NAME = "UTF-8";// 编码类型
	public static final int HASH_ITERATIONS=1024;//加密多少次

	public static String generatePwd(String source, String salt, int hashIterations)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {

		if (source == null || source.trim().equals("")) {
			throw new RuntimeException("用户名和密码不能为空!");
		}

		if (salt == null || salt.trim().equals("")) {
			throw new RuntimeException("salt不能为空!");
		}

		byte[] saltBytes = salt.getBytes(ENCRYPT_NAME);
		byte[] sourceBytes = source.getBytes(ENCRYPT_NAME);

		// 以下做算法加密处理
		MessageDigest digest = MessageDigest.getInstance(ALGORITHM_NAME);
		if (salt != null) {
			digest.reset();
			digest.update(saltBytes);
		}

		byte[] hashed = digest.digest(sourceBytes);
		int iterations = hashIterations - 1;

		for (int i = 0; i < iterations; ++i) {
			digest.reset();
			hashed = digest.digest(hashed);
		}

		return new String(CodecUtils.encodeBase64(hashed, false), ENCRYPT_NAME);
	}

	/**
	 * 调用此方法，作为ICS密码加密处理.
	 * 
	 * @param source
	 * @param salt
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
	private static String generatePwd4Ics(String source, String salt)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {
		return generatePwd(source, salt, HASH_ITERATIONS);
	}

	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		String pwd = "ssr_admin@inspur";
		String userName = "ssr_admin";
		String source = pwd + userName.toUpperCase();
		String salt = userName;// 使用用户名作为此次加密的盐
		String s = generatePwd4Ics(source, salt);
		System.out.println(s);
	}

}
