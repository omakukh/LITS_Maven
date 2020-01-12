package Homework_json_db;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static final String DATABASE_URL = "jdbc:mysql://localhost/TestDB?useSSL=false&characterEncoding=utf-8";
    static final String USER = "root";
    static final String PASSWORD = "";

    public static void main(String[] args) throws Exception {
        System.out.println("Registering JDBC driver...");
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Creating database connection...");
        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        List<Currency> currencies = getCurrencies();

        System.out.println("Inserting records to database...");

        PreparedStatement statement = connection.prepareStatement("INSERT INTO currency (r030, txt, rate, cc, exchangedate) VALUES (?, ?, ?, ?, ?);");

        for (Currency i : currencies) {
            try {
                statement.setInt(1, i.getR030_code());
                statement.setString(2, i.getTxt());
                statement.setDouble(3, i.getRate());
                statement.setString(4, i.getCc());
                statement.setString(5, i.getExchangedate());
                statement.execute();
            } catch (MySQLIntegrityConstraintViolationException e) {
                System.out.println(e);
            }
        }

        ResultSet result = statement.executeQuery("select * from currency;");
        System.out.println("Printing data from DB:");
        while (result.next()) {
            System.out.println("R030: " + result.getInt("r030"));
            System.out.println("TXT: " + result.getString("txt"));
            System.out.println("Rate: " + result.getString("rate"));
            System.out.println("CC: " + result.getString("cc"));
            System.out.println("ExchangeDate: " + result.getString("exchangedate"));
            System.out.println();
        }
        System.out.println("Closing connection and releasing resources...");
        result.close();
        statement.close();
        connection.close();

    }

    private static List<Currency> getCurrencies() throws java.io.IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        List<Currency> currencyList = mapper.readValue(new URL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode=&json"), new TypeReference<List<Currency>>() {
        });
        return currencyList.stream().filter(i -> i.getRate() > 15)
                .collect(Collectors.toList());
    }
}
