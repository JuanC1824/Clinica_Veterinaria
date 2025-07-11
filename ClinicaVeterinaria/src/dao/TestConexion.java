import java.sql.*;

public class TestConexion {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinica_veterinaria_abc", "root", "");
            System.out.println("Conexión exitosa");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
