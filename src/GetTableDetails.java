import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class GetTableDetails {
    public static void main(String[] args) {
        try (Connection connection = DBConnection.getConnection()) {
                 // ! List of Stock Table 

                 try (PreparedStatement StockStatement = connection.prepareStatement("SELECT * FROM stock");
                 ResultSet resultSet = StockStatement.executeQuery()) {
                System.out.println("Stock");
                System.out.println("\n");
                System.out.println("prod_id" +" "+ "dept_id" + " "+  "quantity");
                System.out.println("\n");
                while (resultSet.next()) {
                    System.out.print(resultSet.getString("prod_id") + " " + resultSet.getString("dept_id") + " " + resultSet.getString("quantity"));
                    System.out.println("\n");
                }
                System.out.println("\n");
            }

            // ! List of Depot Table

            try (PreparedStatement DepotStatement = connection.prepareStatement("SELECT * FROM depot");
                 ResultSet resultSet = DepotStatement.executeQuery()) {
                     System.out.println("Depot");
                     System.out.println("\n");
                     System.out.print("dept_id" + " "+ "addr" + " "+  "volume");
                     System.out.println("\n");
                while (resultSet.next()) {

                    System.out.print(resultSet.getString("dept_id") + " " + resultSet.getString("addr") + " " + resultSet.getString("volume"));
                    System.out.println("\n");
                }
                System.out.println("\n");
            }

            // ! List of Product Table

            try (PreparedStatement ProductStatement = connection.prepareStatement("SELECT * FROM product");
                 ResultSet resultSet = ProductStatement.executeQuery()) {
                     System.out.println("Product");
                     System.out.println("\n");
                     System.out.print("prod_id"+ " "+ "pname" + " "+ "price");
                     System.out.println("\n");
                while (resultSet.next()) {

                    System.out.print(resultSet.getString("prod_id") + " " + resultSet.getString("pname") + " " + resultSet.getString("price"));
                    System.out.println("\n");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
