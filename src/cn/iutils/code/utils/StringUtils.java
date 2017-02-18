package cn.iutils.code.utils;


import cn.iutils.code.config.Config;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具
 *
 * @version 2013-05-22
 */
public class StringUtils {

    private static final char SEPARATOR = '_';
    private static final String CHARSET_NAME = "UTF-8";


    /**
     * 表名转成类名
     *
     * @param table
     * @return
     */
    public static String toClassName(String table) {
        if (table != null) {
            String[] strs = table.split("_");
            for (int i = 0; i < strs.length; i++) {
                if (i == 0) {
                    if (!isFilter(strs[i])) {
                        table = table.substring(table.indexOf("_") + 1);
                    }
                    break;
                }
            }
            return toCapitalizeCamelCase(table);
        } else {
            return "";
        }
    }

    /**
     * 过滤字符
     *
     * @param str
     * @return
     */
    public static boolean isFilter(String str) {
        String[] filterStrs = Config.charfilters;
        boolean flag = true;
        for (int i = 0; i < filterStrs.length; i++) {
            if (filterStrs[i].equals(str.toLowerCase())) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase("hello_world") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase("hello_world") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase("hello_world") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

}
