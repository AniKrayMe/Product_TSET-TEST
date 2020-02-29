import java.sql.*;
import java.util.ArrayList;

public class ProductService {
    private static ProductService objectRefrense = null;

    private ProductService() {
    }

    public static ProductService getInstance() {
        if (objectRefrense == null ) {
            objectRefrense = new ProductService();
            return objectRefrense;
        } else {
            return objectRefrense;
        }
    }

    public void createProduct(Product product) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection( MariaDBConstant.DB_URL, MariaDBConstant.USER, MariaDBConstant.PASS);
            if (conn != null) {
                String query = "INSERT iNTO product (name, type) VALUES (?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement( query );
                preparedStatement.setString( 1, product.getName() );
                preparedStatement.setString( 2, product.getType() );
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Product> findAllProducts(){
        ArrayList<Product> products = new ArrayList<>();
        Product product = new Product();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection( MariaDBConstant.DB_URL, MariaDBConstant.USER, MariaDBConstant.PASS);
            if (conn != null) {
                String query = "SELECT * FROM producte";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    product.setName(resultSet.getString("name"));
                    product.setType(resultSet.getString("type"));
                    products.add(product);
                }
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
        return products;
    }


    public void updateProductByID(Product product, int id){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(MariaDBConstant.DB_URL, MariaDBConstant.USER, MariaDBConstant.USER);
            if (conn != null) {
                String query = "UPDATE  product SET name = ?, type = ? WHERE id = ?" ;
                PreparedStatement preparedStatement = conn.prepareStatement( query );
                preparedStatement.setString( 1,product.getName() );
                preparedStatement.setString( 2,product.getType() );
                preparedStatement.setInt( 3, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
