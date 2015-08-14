package com.paul.mode.util;

/**
 * Created by me on 2015/8/14.
 */
public class Util {
    /**
     * 功能描述:byte数组合并
     *
     * @param byteOne
     * @param byteTwo
     * @return
     * @return byte[]
     * @throws
     */
    public static byte[] byteMerge(byte[] byteOne, byte[] byteTwo) {
        if (byteOne == null) {
            return byteTwo;

        } else if (byteTwo == null) {
            return byteOne;
        }

        byte[] result = new byte[byteOne.length + byteTwo.length];
        System.arraycopy(byteOne, 0, result, 0, byteOne.length);
        System.arraycopy(byteTwo, 0, result, byteOne.length, byteTwo.length);

        return result;
    }
}
