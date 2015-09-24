package com.paul.mode.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;

/**
 * Created by me on 2015/8/14.
 */
public class Util {
    /**
     * ��������:byte����ϲ�
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

    public static void writeLineToFile(File file, String text)throws  Exception{
        if(!file.exists()){
            file.createNewFile();
        }

        FileWriter fileWritter = new FileWriter(file.getName(),true);
        BufferedWriter writer = new BufferedWriter(fileWritter);

        writer.newLine();
        writer.write(text);
        writer.close();
    }
}
