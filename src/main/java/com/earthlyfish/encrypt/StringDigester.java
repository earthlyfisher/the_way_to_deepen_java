package com.earthlyfish.encrypt;

import com.earthlyfish.utils.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by earthlyfisher on 2017/3/17.
 */
public class StringDigester {

    private String algorithm;

    private String charset;

    private int interation;

    public byte[] digest(String salt, String message) {
        if (StringUtils.isNullOrEmpty(algorithm)) {
            throw new RuntimeException("algorithm is null or empty");
        }

        if (StringUtils.isNullOrEmpty(charset)) {
            charset = "UTF-8";
        }

        try {
            // 以下做算法加密处理
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            //salt处理
            if (salt != null) {
                digest.reset();
                digest.update(salt.getBytes(charset));
            }

            byte[] hashed = digest.digest(message.getBytes(charset));
            int iterations = interation - 1;
            for (int i = 0; i < iterations; ++i) {
                digest.reset();
                hashed = digest.digest(hashed);
            }

            return hashed;
        } catch (NoSuchAlgorithmException e) {

        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

    private String encodeHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        int key;
        for (int i = 0; i < bytes.length; i++) {
            key = bytes[i];
            if (key < 0) {
                key += 256;
            }

            if (key < 16) {
                sb.append("0");
            }

            sb.append(Integer.toHexString(key));
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        byte[] bytes = new byte[]{-128};
        StringDigester digester = new StringDigester();
        digester.encodeHex(bytes);
    }
}
