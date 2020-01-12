package Homework_json_db;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


public class Currency implements Serializable {
    @JsonAlias({"r030"})
    private int r030_code;
    private String txt;
    private double rate;
    private String cc;
 //   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private String exchangedate;

    public Currency(){}

    public double getRate(){
        return rate;
    }

    public int getR030_code() {
        return r030_code;
    }

    public String getTxt() {
        return txt;
    }

    public String getCc() {
        return cc;
    }

    public String getExchangedate() {
        return exchangedate;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "r030_code=" + r030_code +
                ", txt='" + txt + '\'' +
                ", rate=" + rate +
                ", cc='" + cc + '\'' +
                ", exchangedate=" + exchangedate +
                '}';
    }

}