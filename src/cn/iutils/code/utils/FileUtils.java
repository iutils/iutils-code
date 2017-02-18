package cn.iutils.code.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;

/**
 * 读取文本文件
 *
 * @author hc
 */
public class FileUtils {

    /**
     * 读取文件
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static String readFile(String path) throws Exception {
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuffer stringBuffer = new StringBuffer();
        String str;
        str = bufferedReader.readLine();
        while (str != null) {
            stringBuffer.append(str);
            stringBuffer.append("\r\n");
            str = bufferedReader.readLine();
        }
        bufferedReader.close();
        fileReader.close();
        return stringBuffer.toString();
    }

    /**
     * 保存文件
     *
     * @param path
     * @throws Exception
     */
    public static void saveFile(String path, String msg) throws Exception {
        FileOutputStream fos = new FileOutputStream(new File(path));
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        osw.write(msg);
        osw.flush();
        osw.close();
    }
}
