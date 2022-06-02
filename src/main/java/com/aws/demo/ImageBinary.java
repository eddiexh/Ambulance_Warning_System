package com.aws.demo;

import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;

import com.aws.demo.controller.DatabaseController;
import com.zaxxer.hikari.pool.HikariProxyResultSet;
import org.apache.tomcat.util.codec.binary.Base64;

import static com.aws.demo.controller.DatabaseController.connect_db;

public class ImageBinary {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {

        String fileName = "C:\\Users\\zxc14\\Desktop\\test2\\test.JPG";
        String s = "";
        //Blob b = getImageBinary(fileName);
        //InputStream inStream = b.getBinaryStream();
        //InputStreamReader inStreamReader = new InputStreamReader(inStream);
        //BufferedReader reader = new BufferedReader(inStreamReader );
        //StringBuffer buf = new StringBuffer();
        //while(s = reader.readLine()) {
            //buf.append(s);
        //}
        //DatabaseController.update_id("user_information","picture",1,blob);
        //System.out.printf(blob);
    }

    public static String getImageBinary(String fileName) {
        File f = new File(fileName);
        BufferedImage bi;
        try {
            bi = ImageIO.read(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", baos);
            byte[] bytes = baos.toByteArray();
            return Base64.encodeBase64String(bytes);
            //return encoder.encodeBuffer(bytes).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}