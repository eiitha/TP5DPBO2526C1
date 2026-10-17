
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private Connection connection;

    public Database() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db_people",
                    "root",
                    ""
            );

            System.out.println("Koneksi database berhasil!");

        } catch (SQLException e) {
            System.out.println("Koneksi database gagal!");
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static void main(String[] args) {
        Database db = new Database();

        if (db.getConnection() != null) {
            System.out.println("Database siap digunakan.");
        }
    }
}
