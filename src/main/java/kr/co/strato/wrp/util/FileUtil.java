package kr.co.strato.wrp.util;

import kr.co.strato.wrp.WrpCoreApplication;

import java.net.URISyntaxException;

public class FileUtil {

    public static String getCurrentPath() throws URISyntaxException {

        String currentPath = null;

        try {
            currentPath = WrpCoreApplication.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return currentPath;
    }
}
