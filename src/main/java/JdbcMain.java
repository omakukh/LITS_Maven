import java.sql.*;

public class JdbcMain {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/TestDB?useSSL=false";
    static final String USER = "root";
    static final String PASSWORD = "";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement readData = null;
        System.out.println("Registering JDBC driver...");
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Creating database connection...");
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        System.out.println("Executing statement...");


//        PreparedStatement writeQuery = connection.prepareStatement("INSERT `developers` (`name`, `specialty`,`salary`) VALUES ('name1', 'qa',1);");
//        writeQuery.execute("INSERT `developers` (`name`, `specialty`,`salary`) VALUES ('name1', 'qa',1);");

        PreparedStatement writeQuery = connection.prepareStatement("INSERT `developers` (`name`, `specialty`,`salary`) VALUES (?, 'qa',1);");
        writeQuery.setString(1,"qwerty");
        writeQuery.execute();


        readData = connection.createStatement();
        ResultSet resultSet = readData.executeQuery("SELECT * FROM developers");


        System.out.println("Retrieving data from database...");
        System.out.println("\nDevelopers:");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String specialty = resultSet.getString("specialty");
            int salary = resultSet.getInt("salary");
            System.out.println("\n================\n");
            System.out.println("id: " + id);
            System.out.println("Name: " + name);
            System.out.println("Specialty: " + specialty);
            System.out.println("Salary: $" + salary);
        }
        System.out.println("Closing connection and releasing resources...");
        resultSet.close();
        readData.close();
        connection.close();
        writeQuery.close();
    }
}
