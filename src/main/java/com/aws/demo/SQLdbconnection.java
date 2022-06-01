package com.aws.demo;
import com.aws.demo.model.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class SQLdbconnection {

    public void main(String[] Args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

    }

    @RequestMapping("con")
    public static String connect() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String ur = "jdbc:mysql://localhost:3316/aws";
        String username = "test";
        String password = "12345";
        Connection connection = null;
        try {
            System.out.println("Connecting database...");
            connection = DriverManager.getConnection(ur, username, password);
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect the database!", e);
        }
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM `userinformation`");//搜尋哪個資料表
        String all = "";
        while (rs.next() == true){
            all +=rs.getString("account") + " " + rs.getString("password") + " " + rs.getString("name") + "\n";//取得資料表內的資料
        }
        connection.close();
        return all;
    }
}