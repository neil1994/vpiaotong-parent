package com.vpiaotong.elephant.core.utils.sms;

import java.util.Random;

/**
 * 随机数、 随机字符串工具类
 * 
 * @author Acmen
 */
public class RandomUtils {

    public static final String LETTER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String NUMBERIC = "0123456789";

    public static final String NUMBERIC_EXCEPT_ZERO = "123456789";

    public static final String ALPHABETIC = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String ALPHABETIC2 = "0123456789abcdefghijklmnopqrstuvwxyz";

    /**
     * 返回一个定长字符串(包含字母和数字)
     * 
     * @param length
     *            随机字符串长
     * @return 随机字符串
     */
    public static String generateMixString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALPHABETIC2.charAt(random.nextInt(ALPHABETIC2.length() - 1)));
        }
        return sb.toString();
    }

    /**
     * 返回纯字母字符串(只包含字母)
     * 
     * @param length
     *            随机字符串长
     * @return 随机字符串
     */
    public static String generatePureString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(LETTER.length()));
        }
        return sb.toString();
    }

    /**
     * 返回纯大写字母字符串
     * 
     * @param length
     *            随机字符串长
     * @return 随机字符串
     */
    public static String generateUpperString(int length) {
        return generatePureString(length).toUpperCase();
    }

    /**
     * 返回纯小写字母字符串
     * 
     * @param length
     *            随机字符串长
     * @return 随机字符串
     */
    public static String generateLowerString(int length) {
        return generatePureString(length).toLowerCase();
    }

    /**
     * 返回数字字符串
     * 
     * @param length
     *            随机字符串长
     * @return 随机字符串
     */
    public static String generateNumString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(NUMBERIC.length()));
        }
        return sb.toString();
    }

    /**
     * 返回单个数字
     * 
     * @param length
     *            随机字符串长
     * @return 随机字符串
     */
    public static int generateNumber() {
        Random random = new Random();
        return random.nextInt(NUMBERIC.length());
    }

    public static int generatePosition() {
        Random random = new Random();
        return random.nextInt(NUMBERIC_EXCEPT_ZERO.length());
    }
}
