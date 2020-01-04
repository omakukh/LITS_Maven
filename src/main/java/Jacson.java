import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Jacson {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        //Car car = new Car("white", "KIA");
        //objectMapper.writeValue(new File("target/car.json"),car);

//        String json = "{\"color\":\"white\",\"type\":\"KIA\"}";
//        Car car = objectMapper.readValue(json, Car.class);
//        System.out.println(car);


        String json2 = "{ \"name\" : \"product\", \"count\" : \"12\", \"address\" : \"grove street\" }";
        Testjson test1 = objectMapper.readValue(json2, Testjson.class);
        System.out.println(test1);

        String jsonCarArray = "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";
        List<Car> listCar = objectMapper.readValue(jsonCarArray, new TypeReference<List<Car>>(){});
        System.out.println(listCar);
    }
}
