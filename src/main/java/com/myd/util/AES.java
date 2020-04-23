package com.myd.util;

import org.apache.commons.codec.binary.Base64;
import org.intellij.lang.annotations.MagicConstant;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.SecureRandom;
import static com.google.common.base.Charsets.UTF_8;


/**
 * AES 算法实现。
 *
 * 1. `AES/CBC/PKCS5Padding`
 * 2. `keySize` 大小：256bit, 长度：32
 * 3. `IV` 大小：128bit, 长度：16；根据key生成固定的IV
 *
 * @author Fuchun
 * @since 1.0
 */
public class AES extends BaseCrypto {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    private static IvParameterSpec parameterSpec(Key key) {
        byte[] keyBytes = key.getEncoded();
        byte[] ivBytes = new byte[16];
        // IV 取密钥的前 16 位
        System.arraycopy(keyBytes, 0, ivBytes, 0, 16);
        return new IvParameterSpec(ivBytes);
    }

    public static Key generateKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM, BOUNCYCASTLE);

            keyGen.init(256, new SecureRandom());

            return keyGen.generateKey();
        } catch (Exception ex) {
            throw new CryptoException(ex);
        }
    }
    public static Key fromBase64(String b64Text) {
        byte[] keyBytes = Base64.decodeBase64(b64Text);
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }

    public static byte[] encrypt(byte[] data, Key key) {
        return doCrypt(Cipher.ENCRYPT_MODE, data, key);
    }

    public static byte[] decrypt(byte[] data, Key key) {
        return doCrypt(Cipher.DECRYPT_MODE, data, key);
    }

    public static String encryptToBase64(String text, Key key) {
        return encryptToBase64(text, key, UTF_8);
    }

    public static String encryptToBase64(String text, Key key, Charset charset) {
        if (charset == null) {
            charset = UTF_8;
        }
        byte[] plain = text.getBytes(charset);
        return encryptToBase64(plain, key);
    }

    public static String encryptToBase64(byte[] data, Key key) {
        byte[] bytes = encrypt(data, key);
        return Base64.encodeBase64String(bytes);
    }

    public static byte[] decryptFromBase64(String decryptedText, Key key) {
        byte[] decrypted = Base64.decodeBase64(decryptedText);
        return decrypt(decrypted, key);
    }

    public static String decryptToString(byte[] data, Key key, Charset charset) {
        byte[] plain = decrypt(data, key);
        if (charset == null) {
            charset = UTF_8;
        }
        return new String(plain, charset);
    }

    public static String decryptFromBase64ToString(String decryptedText, Key key) {
        return decryptFromBase64ToString(decryptedText, key, UTF_8);
    }

    public static String decryptFromBase64ToString(String decryptedText, Key key, Charset charset) {
        byte[] bytes = decryptFromBase64(decryptedText, key);
        if (charset == null) {
            charset = UTF_8;
        }
        return new String(bytes, charset);
    }

    private static byte[] doCrypt(
            @MagicConstant(intValues = {Cipher.ENCRYPT_MODE, Cipher.DECRYPT_MODE}) int mode, byte[] data, Key key) {
        // SunJCE provider Cipher (JAVA8_u152 之前的版本，需要下载JCE 无限制 unlimited 安全补丁)
        // JAVA8_u152 之后，可以修改 $JAVA_HOME/jre/lib/security/java.security 配置
        // crypto.policy=unlimited
        // JAVA9 之后，默认自动支持无限制密钥
        Cipher cipher = newBcCipher(TRANSFORMATION);
        initCipher(cipher, mode, key, parameterSpec(key));
        try {
            return cipher.doFinal(data);
        } catch (Exception ex) {
            throw new CryptoException("Unable to execute 'doFinal' with cipher instance [" + cipher + "].", ex);
        }
    }

}
