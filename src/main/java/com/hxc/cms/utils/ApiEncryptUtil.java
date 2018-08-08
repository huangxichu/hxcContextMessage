package com.hxc.cms.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * OpenApi通信协议加解密工具类
 * 
 * 
 */
public class ApiEncryptUtil {

    private final static Random random = new Random();

    public static void main(String[] args) throws Exception {
        
        Map<String, Object> map = generateRSAKeyPairs();
        System.out.println("publicKey:===>"+map.get("publicKey"));
        System.out.println("privateKey:===>"+map.get("privateKey"));
        
//        System.out.println(KeyCreate(24));
        
    }
    
    /**
     * 生成RSA公、私钥对
     * 
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static Map<String, Object> generateRSAKeyPairs() throws NoSuchAlgorithmException {
        Map<String, Object> keyPairMap = new HashMap<String, Object>();
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = generator.genKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        keyPairMap.put("publicKey", Base64.encodeBase64String(publicKey.getEncoded()));
        keyPairMap.put("privateKey", Base64.encodeBase64String(privateKey.getEncoded()));
        return keyPairMap;
    }

    public static byte[] signByPrivateKey(byte[] data, PrivateKey privateKey)
            throws Exception {
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initSign(privateKey);
        sig.update(data);
        byte[] ret = sig.sign();
        return ret;
    }
    
    public static PrivateKey getPrivateKeyFromString(String base64String)
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] bt = Base64.decodeBase64(base64String.getBytes());
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bt);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
        return privateKey;
    }
    


    /**
     * 生成16位AES随机密钥
     * @return
     */
    public static String getAESRandomKey(){
        long longValue = random.nextLong();
        return String.format("%016x", longValue);
    }
    

    public static String KeyCreate(int KeyLength) {
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%&*:_+<>?~#$@";
        Random random = new Random();
        StringBuffer Keysb = new StringBuffer();
        // 生成指定位数的随机秘钥字符串
        for (int i = 0; i < KeyLength; i++) {
            int number = random.nextInt(base.length());
            Keysb.append(base.charAt(number));
        }
        return Keysb.toString();
    }
    
    

}