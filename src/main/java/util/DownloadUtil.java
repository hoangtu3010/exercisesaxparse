package util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.FileOutputStream;

public class DownloadUtil {
    public static void downloadFile(String fileName, String fileLink) {
        try {
            Connection.Response response = Jsoup.connect(fileLink).ignoreContentType(true).execute();
            FileOutputStream out = new FileOutputStream(fileName);
            out.write(response.bodyAsBytes());
            out.close();
            System.out.printf("Download of file %s success!", fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
