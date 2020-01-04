import java.io.Serializable;

public class Testjson implements Serializable {

    private String name;
    private int count;
    private String address;

    public Testjson(String name, int count, String address) {
        this.name = name;
        this.count = count;
        this.address = address;
    }
public Testjson(){}

    @Override
    public String toString() {
        return "Testjson{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", address='" + address + '\'' +
                '}';
    }
}
