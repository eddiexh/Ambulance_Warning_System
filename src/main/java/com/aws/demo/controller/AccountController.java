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
public class AccountController {

    ArrayList<String> account = new ArrayList<String>();
    ArrayList<String> password = new ArrayList<String>();

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Account acc) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ResultSet rs = DatabaseManager.view_table("user_information");
        while (rs.next() == true){
            account.add(rs.getString("account"));
            password.add(rs.getString("password"));
        }
        ResponseEntity status;

        for (int counter = 0; counter < account.size(); counter++) {
            if (acc.getAccount().equals(account.get(counter)) && acc.getPassword().equals(password.get(counter))){
                status =  new ResponseEntity<>(HttpStatus.OK);
                return status;
            }
        }
        status =  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return status;
    }
}