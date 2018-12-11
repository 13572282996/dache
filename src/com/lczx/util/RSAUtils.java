/*



 */
package com.lczx.util;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.HashMap;

import javax.crypto.Cipher;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.util.Assert;

/**
 * Utils - RSA加密解密
 * 
 */
public final class RSAUtils
{
    
    /** 安全服务提供者 */
    private static final Provider PROVIDER = new BouncyCastleProvider();
    
    /** 密钥大小 */
    private static final int KEY_SIZE = 1024;
    
    /** */
    /**
    * RSA最大加密明文大小
    */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    
    /** */
    /**
    * RSA最大解密密文大小
    */
    private static final int MAX_DECRYPT_BLOCK = 128;
    
    public static final String KEY_ALGORITHM = "RSA";
    
    /**
     * 不可实例化
     */
    private RSAUtils()
    {
    }
    
    private static String priKeyString = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALLSPHV6cNkJTIpvmn1Bi0WvBIDtgUOmW8qaujGp8r+d1IzQwhJnTI0X9AAh3zqi39ykqxZhcXJYsEtZTWY+k0wTJLvpNUj9FOt9KKrfEj9VTJDp/nrg9365XnlRJZRsLXztCF38Y0syQyWKR2+Xj/JMV/6ONdbnTNrgYbLwueF/AgMBAAECgYEAqBOBLi6SqPzbi3cQr1CO9kVlp0UVbZ+MwWcjQRDrEH3pzR1efGvJgQiVmBY+Ol/iqAHCqXuXZRHFSl06qbKBQLk+gbAjxnz0SRVw8imB1mF36yRpjkgBMT0u3fJjV1kHpoEHFquUIPz0O79IXsVJr5Be8gRX5hjlm9elK6YE6+ECQQDpLgEH9gIeOg7eGlFPwPIxWjrmkGyvp7F2ZvdcRofwpyGggxNunR3r+fbiolJpprn4LxHH60nc4gFJ02L3J+BxAkEAxFJbDNhMjpN87r3LFcHV5/JM2jZ73nQOlSho4xMQtBDmaTxNXVVqkuuCXYDGQ5crpQkzgtqEqLiv5iuv7yDY7wJAdoYLwCArs3GPXRXDfuZ0NOHITqnalO6IJcbwtNalAo3xacU2e2MhcnD8LPaVLV6x6JUEVLokMUIvpXbtNvPuAQJBAK/BBtAuCAOQGEVnVhtpR4V5zsGAC06wWanA3n2DQO3jP1Mw8BXBdUKIYlIxCc3S1PjPjvTzidW+WYLW049hubMCQQCtDclRc2GEs0iVvr8S5wkpO/FEa53S4v9HeuAOe6CKgKKbYJI3Zb6E87WodXgiLSADPGab8TLFqA8yP1MeLU4T";
    
    /**
     * 生成密钥对
     * 
     * @return 密钥对
     */
    public static KeyPair generateKeyPair()
    {
        try
        {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA",
                    PROVIDER);
            keyPairGenerator.initialize(KEY_SIZE, new SecureRandom());
            return keyPairGenerator.generateKeyPair();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 加密
     * 
     * @param publicKey
     *            公钥
     * @param data
     *            数据
     * @return 加密后的数据
     */
    public static byte[] encrypt(PublicKey publicKey, byte[] data)
    {
        Assert.notNull(publicKey);
        Assert.notNull(data);
        try
        {
            Cipher cipher = Cipher.getInstance("RSA", PROVIDER);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(data);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 加密
     * 
     * @param publicKey
     *            公钥
     * @param text
     *            字符串
     * 
     * @return Base64编码字符串
     */
    public static String encrypt(PublicKey publicKey, String text)
    {
        Assert.notNull(publicKey);
        Assert.notNull(text);
        byte[] data = encrypt(publicKey, text.getBytes());
        return data != null ? com.lczxtech.codec.binary.Base64.encodeBase64String(data)
                : null;
    }
    
    /**
     * 解密
     * 
     * @param privateKey
     *            私钥
     * @param data
     *            数据
     * @return 解密后的数据
     */
    public static byte[] decrypt(PrivateKey privateKey, byte[] data)
    {
        Assert.notNull(privateKey);
        Assert.notNull(data);
        try
        {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", PROVIDER);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(data);
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
    /**
     * 解密
     * 
     * @param privateKey
     *            私钥
     * @param text
     *            Base64编码字符串
     * @return 解密后的数据
     * @throws Exception 
     */
    public static String decrypt(PrivateKey privateKey, String text)
            throws Exception
    {
        Assert.notNull(privateKey);
        Assert.notNull(text);
        //        byte[] data = decrypt(privateKey,
        //                com.lczxtech.codec.binary.Base64.decodeBase64(text));
        byte[] data = decryptByPrivateKey(com.lczxtech.codec.binary.Base64.decodeBase64(text),
                priKeyString);
        return data != null ? new String(data, "UTF-8") : null;
    }
    
    /**
     * 解密 
     * @throws Exception 
     **/
    public static String decrypt(String str) throws Exception
    {
        byte[] buffer = Base64.decodeBase64(priKeyString);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        
        return RSAUtils.decrypt(privateKey, str);
    }
    
    /** */
    /**
    * <P>
    * 私钥解密
    * </p>
    * 
    * @param encryptedData 已加密数据
    * @param privateKey 私钥(BASE64编码)
    * @return
    * @throws Exception
    */
    public static byte[] decryptByPrivateKey(byte[] encryptedData,
            String privateKey) throws Exception
    {
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0)
        {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK)
            {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            }
            else
            {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }
    
}