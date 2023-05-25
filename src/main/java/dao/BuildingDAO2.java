import  helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Building;

/**
 *
 * @author ADMIN
 */
public class BuildingDAO {
    
    public void insert(Building model){
        String sql = "Insert into Building (id, nameBuilding, address, Image, phone, numberFloor, totalAparments,Email, description,Trash, Status) VALUS (?,?,?,?,?,?,?,?,?,?)";
        JdbcHelper.executeUpdate(sql,
                model.getid(),
                model.getnameBuilding(),
                model.getaddress(),
                model.getimage(),
                model.getphone(),
                model.getnumberFloor(),
                model.gettotalAparments(),
                model.getEmail(),
                model.getdescription(),
                model.gettrash(),
                model.getstatus());
    }
    public void update(Building model){
        String sql = "Update Building Set (nameBuilding=?,Address = ?, Image = ?, phone = ?, numberFloor = ?, totalApartment = ?,Email = ?, Description= ?,Trash = ?, Status = ? Where Id_buiding = ?)";
        JdbcHelper.executeUpdate(sql,
                model.getid(),
                model.getnameBuilding(),
                model.getaddress(),
                model.getimage(),
                model.getphone(),
                model.getnumberFloor(),
                model.gettotalAparments(),
                model.getemail(),
                model.getdescription(),
                model.gettrash(),
                model.getstatus());
    }
    public void Delete(String Id_building){
        String sql="DELETE FROM Building WHERE Id=?";
        JdbcHelper.executeUpdate(sql, Id_building);
    }
    private List<Building> select(String sql, Object...args){
        List<Building> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while(rs.next()){
                    Building model=readFromResultSet(rs);
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
    private Building readFromResultSet(ResultSet rs) throws SQLException{
        Building model=new Building();
        model.setId_building(rs.getString("id"));
        model.setName(rs.getString("nameBuilding"));
        model.setAddress(rs.getString("address"));
        model.setImage(rs.getString("image"));
        model.setPhone_number(rs.getInt("phone"));
        model.setNumber_of_floor(rs.getInt("numberFloor"));
        model.setTotal_apartment(rs.getInt("totalApartment"));
        model.setDescription(rs.getString("description"));
        model.setTrash(rs.getInt("trash"));      
        model.setStatus(rs.getInt("status"));
        return model;
    }
    public List<Building> select(){
        String sql="SELECT * FROM Building";
        return select(sql);
    }
      public List<Building> selectByKeyword(String keyword){
        String sql="SELECT * FROM Building WHERE Id LIKE ?";
        return select(sql, "%"+keyword+"%");
    }
