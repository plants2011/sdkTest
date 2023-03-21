package com.hm.pluginsdk.utils;

/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 支持HMAC-SHA1消息签名 及 DES/AES对称加密的工具类.
 * <p/>
 * 支持Hex与Base64两种编码方式.
 *
 * @author calvin
 */
public class CryptoUtils {

    private static final String AES = "AES";
    private static final String AES_CBC = "AES/CBC/PKCS5Padding";
    private static final String HMACSHA1 = "HmacSHA1";

    private static final int DEFAULT_HMACSHA1_KEYSIZE = 160; // RFC2401
    private static final int DEFAULT_AES_KEYSIZE = 128;
    private static final int DEFAULT_IVSIZE = 16;

    private static SecureRandom random = new SecureRandom();

    // -- HMAC-SHA1 funciton --//

    public static String generateCToken(String pkgName, String userId, String userToken, String accessKeyID, String channelID, String accessKey) {
        try {
            String raw = userId + userToken + pkgName + accessKeyID + channelID;
            byte[] aes = CryptoUtils.aesEncrypt(raw.getBytes(), CryptoUtils.hexStringToByte(accessKey));
            byte[] sha1 = CryptoUtils.sha1(aes);
            return CryptoUtils.byteToHexString(sha1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String generateCToken(String raw, String accessKey) {
        try {
            byte[] aes = CryptoUtils.aesEncrypt(raw.getBytes(), CryptoUtils.hexStringToByte(accessKey));
            byte[] sha1 = CryptoUtils.sha1(aes);
            return CryptoUtils.byteToHexString(sha1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // -- AES funciton --//

    /**
     * 使用AES加密原始字符串.
     *
     * @param input 原始输入字符数组
     * @param key   符合AES要求的密钥
     */
    public static byte[] aesEncrypt(byte[] input, byte[] key) {
        return aes(input, key, Cipher.ENCRYPT_MODE);
    }

    /**
     * 使用AES加密或解密无编码的原始字节数组, 返回无编码的字节数组结果.
     *
     * @param input 原始字节数组
     * @param key   符合AES要求的密钥
     * @param mode  Cipher.ENCRYPT_MODE 或 Cipher.DECRYPT_MODE
     */
    private static byte[] aes(byte[] input, byte[] key, int mode) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, AES);
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(mode, secretKey);
            return cipher.doFinal(input);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
//            try {
//                CountlyUtil.recordCheckErrorCodeEvent(Constants.COUNTLY_CONNECT_SAAS_AES_ERROR, "CryptoUtils::aes1::" + Log.getStackTraceString(e) + " input : [" + Base64.encodeToString(input,Base64.DEFAULT) + "] key : [" + new String(key, "UTF-8") + "] mode : [" + mode + "]");
//            } catch (UnsupportedEncodingException e1) {
//                e1.printStackTrace();
//            }
            return null;
        }
    }

    public static String byteToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String strHex = Integer.toHexString(bytes[i]);
            if (strHex.length() > 3) {
                sb.append(strHex.substring(6));
            } else {
                if (strHex.length() < 2) {
                    sb.append("0" + strHex);
                } else {
                    sb.append(strHex);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 十六进制string转二进制byte[]
     */
    public static byte[] hexStringToByte(String s) {
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return baKeyword;
    }

    public static byte[] sha1(byte[] input) {
        return digest(input, "SHA-1", null, 1);
    }

    private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            if (salt != null) {
                digest.update(salt);
            }

            byte[] result = digest.digest(input);

            for (int i = 1; i < iterations; i++) {
                digest.reset();
                result = digest.digest(result);
            }
            return result;
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return null;
        }
    }
}