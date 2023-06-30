package com.ruoyi.carbon.utils;

import java.util.Random;

/**
 * 随机生成5位数验证码
 * @author zyh
 * @date 2023/4/8 2:55
 */
public class VerificationCodeGeneratorUtil {
    private static final int CODE_LENGTH = 5;

    public static String generate() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String generate = VerificationCodeGeneratorUtil.generate();
        System.out.println(generate);
    }
}
