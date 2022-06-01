package com.aws.demo.controller;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.*;
import com.aws.demo.model.*;

@RequestMapping("/api")
@RestController
public class LoginController {

    ArrayList<String> account = new ArrayList<String>();
    ArrayList<String> password = new ArrayList<String>();

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Account acc) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        connect_db();
        ResponseEntity status;
        try {
            for (int counter = 0; counter < account.size(); counter++) {
                if (acc.getAccount().equals(account.get(counter)) && acc.getPassword().equals(password.get(counter))){
                    return new ResponseEntity<>(HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public void connect_db() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://localhost:3316/aws";
        String db_username = "test";
        String db_password = "12345";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, db_username, db_password);
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect the database!", e);
        }
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM `user_information`");//搜尋哪個資料表


        while (rs.next() == true){
            account.add(rs.getString("account"));
            password.add(rs.getString("password"));
        }
        connection.close();
    }
}
