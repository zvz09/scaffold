package com.zvz.servicefileview.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class DownloadUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DownloadUtils.class);

    private static void downloadFile(String downloadUrl, File file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            URL url = new URL(downloadUrl);
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            int length = 0;
            byte[] bytes = new byte[1024];
            while ((length = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, length);
            }
            fileOutputStream.close();
            inputStream.close();
        } catch (IOException e) {
            LOGGER.error("download error ! url :{}, exception:{}", downloadUrl, e);
        }
    }
}
