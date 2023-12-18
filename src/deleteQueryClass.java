import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class deleteQueryClass {
        public static String DeleteQueryMethod(String table_name,String condition){
            String deleteQuery = "DELETE FROM %s WHERE %s = ?";
            return String.format(deleteQuery, table_name,condition);
        }
        public static void main(String[] args) {
             try (Connection connection = DBConnection.getConnection()) {
                // ! 1. The product p1 is deleted from Product and Stock.

                // Alter the table
                String alterStatementString1= "ALTER TABLE stock DROP CONSTRAINT fk_stock_product; ALTER TABLE stock ADD CONSTRAINT fk_stock_product FOREIGN KEY (prod_id) REFERENCES product(prod_id) ON DELETE CASCADE;";

                try(PreparedStatement alterStatement = connection.prepareStatement(alterStatementString1);
                ){
                    int resultSet =alterStatement.executeUpdate();
                    System.out.println("Table Altering is done. Rows affected: " + Integer.toString(resultSet));
                }

                // ? Product

                String deleteProduct = deleteQueryClass.DeleteQueryMethod("product","prod_id");
                
                try(
                PreparedStatement deleteProductStatement = connection.prepareStatement(deleteProduct);
                ){
                                deleteProductStatement.clearParameters();
                                deleteProductStatement.setObject(1, "p1"); 

                                int ProductRowsDeleted = deleteProductStatement.executeUpdate();
                                System.out.println("ProductRowsDeleted" + Integer.toString(ProductRowsDeleted));
                }

                // ! 2. The depot d1 is deleted from Depot and Stock.

                // Alter the table
                

                String alterStatementString2= "ALTER TABLE stock DROP CONSTRAINT fk_stock_depot; ALTER TABLE stock ADD CONSTRAINT fk_stock_depot FOREIGN KEY (dept_id) REFERENCES depot(dept_id) ON DELETE CASCADE;";

                try(PreparedStatement alterStatement = connection.prepareStatement(alterStatementString2);
                ){
                    int resultSet =alterStatement.executeUpdate();
                    System.out.println("Table Altering is done. Rows affected: " + Integer.toString(resultSet));
                }

                // ? Depot

                String deleteDepot = deleteQueryClass.DeleteQueryMethod("depot","dept_id");
                
                try(
                PreparedStatement deleteProductStatement = connection.prepareStatement(deleteDepot);
                ){
                    deleteProductStatement.clearParameters();
                    deleteProductStatement.setObject(1, "d1"); 

                    int resultSet = deleteProductStatement.executeUpdate();
                    System.out.println("Table Altering is done. Rows affected: " + Integer.toString(resultSet));
            }
        }
            catch (SQLException e) {
            e.printStackTrace();
        } 
    }
}