package com.example.courierLogisticsSystem.db;

import com.example.courierLogisticsSystem.config.DatabaseConfig;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.ibatis.jdbc.ScriptRunner;

public class DB {
    public static Connection connect(Boolean loadTables) throws SQLException {

        try {
            var jdbcUrl = DatabaseConfig.getDbUrl();
            var user = DatabaseConfig.getDbUsername();
            var password = DatabaseConfig.getDbPassword();

            Connection con = DriverManager.getConnection(jdbcUrl, user, password);

            if (loadTables) {
                ScriptRunner sr = new ScriptRunner(con);
//                System.out.println(Paths.get("schema.sql").toAbsolutePath());
//                System.out.println(Paths.get("data.sql").toAbsolutePath());
                Reader schema = new BufferedReader(new FileReader("/Users/jodyfortuin/Documents/VSCode Projects/courier-logistics-system/src/main/resources/sql/schema.sql"));
                Reader data = new BufferedReader(new FileReader("/Users/jodyfortuin/Documents/VSCode Projects/courier-logistics-system/src/main/resources/sql/data.sql"));
                sr.runScript(schema);
                sr.runScript(data);
            }
            return con;
        } catch (SQLException  e) {
            e.printStackTrace();
//            System.err.println(e.getMessage());
            return null;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
