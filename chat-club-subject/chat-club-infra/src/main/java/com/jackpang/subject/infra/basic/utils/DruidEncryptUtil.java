package com.jackpang.subject.infra.basic.utils;

import com.alibaba.druid.filter.config.ConfigTools;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * description: DruidEncryptUtil
 * date: 3/9/24 10:55 PM
 * author: jinhao_pang
 * version: 1.0
 */
public class DruidEncryptUtil {
    private static String publicKey;
    private static String privateKey;
    static {
        try {
            String[] keyPair = ConfigTools.genKeyPair(512);
            privateKey = keyPair[0];
            publicKey = keyPair[1];
            System.out.printf("privateKey: %s\n", privateKey);
            System.out.printf("publicKey: %s\n", publicKey);
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String plainText) throws Exception {
        String encrypt = ConfigTools.encrypt(privateKey, plainText);
        System.out.printf("encrypt: %s\n", encrypt);
        return encrypt;
    }

    public static String decrypt(String cipherText) throws Exception {
        String decrypt = ConfigTools.decrypt(publicKey, cipherText);
        System.out.printf("decrypt: %s\n", decrypt);
        return decrypt;
    }

    public static void main(String[] args) throws Exception {
        encrypt("123456");
        System.out.println(ConfigTools.decrypt("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAOksceSqs62qpEUCD17wMDsIvr+BL2lrQ37AHQ8L03VPlwxnlO84JwXWRR59ODQvUmxWYFVG39QURsd3HuZa0JMCAwEAAQ==","TRsMw8bnGU+vfcadWcIvafKdEQ342uoco+ywgpQ3LXkLwo+ZIQZ20wvK9PBp/vnjtzh8Gz7VayqcJq+kG+JTiA=="));
    }
}
