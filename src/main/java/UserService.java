import java.sql.*;
import java.util.ArrayList;

public class UserService {

    private static UserService objectRefrense = null;

    private UserService() {
    }

    public static UserService getInstance() {
        if (objectRefrense == null ) {
            objectRefrense = new UserService();
            return objectRefrense;
        } else {
            return objectRefrense;
        }
    }

    public void createUser(User user) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection( MariaDBConstant.DB_URL, MariaDBConstant.USER, MariaDBConstant.PASS);
            if (conn != null) {
                String query = "INSERT iNTO user (name, surename, password) VALUES (?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement( query );
                preparedStatement.setString( 1, user.getName() );
                preparedStatement.setString( 2, user.getSureName() );
                preparedStatement.setString( 3, user.getBCryptPassword() );
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public ArrayList<User> findAllUsers(){
        ArrayList<User> users = new ArrayList<>();
        User user = new User();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection( MariaDBConstant.DB_URL, MariaDBConstant.USER, MariaDBConstant.PASS);
            if (conn != null) {
                String query = "SELECT * FROM Customers";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    user.setName(resultSet.getString("name"));
                    user.setSureName(resultSet.getString("surname"));
                    user.setPassword(resultSet.getString("password"));
                    users.add(user);
                }
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
        return users;
    }

    public void updateUserByID(User user, int id){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(MariaDBConstant.DB_URL, MariaDBConstant.USER, MariaDBConstant.USER);
            if (conn != null) {
                String query = "UPDATE  user SET name = ?, surename = ?, password = ? WHERE id = ?" ;
                PreparedStatement preparedStatement = conn.prepareStatement( query );
                preparedStatement.setString( 1,user.getName() );
                preparedStatement.setString( 2,user.getSureName() );
                preparedStatement.setString( 3,user.getBCryptPassword() );
                preparedStatement.setInt( 4, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void deleteUserByID( int id){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(MariaDBConstant.DB_URL, MariaDBConstant.USER, MariaDBConstant.PASS);
            if (conn != null) {
                String query = "DELETE FROM user  WHERE id = ?" ;
                PreparedStatement preparedStatement = conn.prepareStatement( query );
                preparedStatement.setInt( 1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


}
