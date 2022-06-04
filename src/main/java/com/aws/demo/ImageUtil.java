package com.aws.demo;

import com.aws.demo.controller.DatabaseManager;
import org.apache.commons.net.util.Base64;
import java.io.*;
import java.sql.SQLException;


public class ImageUtil {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        File file = new File("C:\\Users\\user\\Desktop\\YunTech\\110-2\\系統分析與設計\\TW2\\photo\\B109230xx.jpg");
        InputStream in = new FileInputStream(file);
        byte[] data = new byte[in.available()];
        in.read(data);
        String base64 = Base64.encodeBase64String(data);
        //System.out.println(base64);
        byte[] base64Arr = base64.getBytes();
        byte[] decodeData = Base64.decodeBase64(base64Arr);
        DatabaseManager.update_id("user_information","picture","id",1,Base64.encodeBase64String(decodeData));
    }
}