package com.aws.demo;

import com.aws.demo.controller.DatabaseManager;
import org.apache.commons.net.util.Base64;

import java.io.*;
import java.sql.SQLException;


public class ImageUtil {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        File file = new File("C:\\Users\\zxc14\\Desktop\\YunTech\\110-2\\系統分析與設計\\TW2\\photo\\B10923065.jpg");
        InputStream in = new FileInputStream(file);
        byte[] data = new byte[in.available()];
        in.read(data);

        String base64 = Base64.encodeBase64String(data);
        // 流轉為base64編碼
        System.out.println(base64);

        // BLOB欄位存資料庫需要轉為byte陣列
        byte[] base64Arr = base64.getBytes();

        // BLOB資料轉為base64編碼
        byte[] decodeData = Base64.decodeBase64(base64Arr); // base64編碼後的位元組資料解碼成原來檔案流的位元組資料
        DatabaseManager.update_id("user_information","picture","id",1,Base64.encodeBase64String(decodeData));
    }

}