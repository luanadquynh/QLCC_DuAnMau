import helper.JdbcHelper;
import models.Apartment;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ApartmentDAO {
    
    public void Insert(Apartment model){
        String sql = "insert into Apartment (Apartment_code,Apartment_number,Floor_number,Owner_name,Owner_phone,Status,Id_building) values (?,?,?,?,?,?,?)";
            JdbcHelper.executeUpdate(sql, 
                model.getApartment_code(),
                model.getApartment_number(),
                model.getFloor_number(),
                model.getOwner_name(),
                model.getOwner_phone(),
                model.getStatus(),
                model.getId_building());
    }
    public void Update(Apartment model){
        String sql = "Update Apartment Set Apartment_number = ?, Floor_number = ?, Owner_name = ?,Owner_phone = ?, Status = ?, Id_building = ? Where Apartment_code = ?";
            JdbcHelper.executeUpdate(sql, model.getApartment_code(),
                model.getApartment_number(),
                model.getFloor_number(),
                model.getOwner_name(),
                model.getOwner_phone(),
                model.getStatus(),
                model.getId_building());
    }
    public void Delete(String Apartment_code){
        String sql="DELETE FROM Apartment WHERE Apartment_code=?";
        JdbcHelper.executeUpdate(sql, Apartment_code);
    }
    private List<Apartment> select(String sql, Object...args){
        List<Apartment> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while(rs.next()){
                    Apartment model=readFromResultSet(rs);
                    list.add(model);
                }
            }
            finally{
                rs.getStatement().getConnection().close();
            }
        } 
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
    private Apartment readFromResultSet(ResultSet rs) throws SQLException{
        Apartment model=new Apartment();
        model.setApartment_code(rs.getString("Apartment_code"));
        model.setApartment_number(rs.getString("Apartment_number"));
        model.setFloor_number(rs.getInt("Floor_number"));
        model.setId_building(rs.getString("Id_building"));
        model.setOwner_name(rs.getString("Owner_name"));
        model.setOwner_phone(rs.getInt("Owner_phone"));
        model.setStatus(rs.getInt("Status"));
        return model;
    }
    public List<Apartment> select(){
        String sql="SELECT * FROM APARTMENT";
        return select(sql);
    }
    
        public List<Apartment> selectByKeyword(String keyword){
        String sql="SELECT * FROM Apartment WHERE Apartment_code LIKE ?";
        return select(sql, "%"+keyword+"%");
    }
}
