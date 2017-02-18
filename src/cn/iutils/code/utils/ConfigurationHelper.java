package cn.iutils.code.utils;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import freemarker.template.Configuration;

public class ConfigurationHelper {

    private static Configuration cfg = null;

    public static Configuration getConfiguration(String templateDir) throws IOException {
        if (null == cfg) {
            cfg = new Configuration();
            File templateDirFile = new File(templateDir);
            cfg.setDirectoryForTemplateLoading(templateDirFile);
            cfg.setLocale(Locale.CHINA);
            cfg.setDefaultEncoding("UTF-8");
        }
        return cfg;
    }
}
