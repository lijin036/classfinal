package com.sunrisechina;

public class SSJar {

    static {
        try {
            System.out.println(System.getProperty("java.library.path"));
            System.loadLibrary("sunrisechina");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static native String encrypt(String plainText);

    public static native String decrypt(String cipherText);
}
