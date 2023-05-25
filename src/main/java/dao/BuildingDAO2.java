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
        String sql = "Insert into Building (Id_buidling, Name,Address, Image, Phone_number, Number_of_floor, Total_apartment,Email, Description,Trash, Status) VALUS (?,?,?,?,?,?,?,?,?,?)";
        JdbcHelper.executeUpdate(sql,
                model.getId_building(),
                model.getName(),
                model.getAddress(),
                model.getImage(),
                model.getPhone_number(),
                model.getNumber_of_floor(),
                model.getTotal_apartment(),
                model.getEmail(),
                model.getDescription(),
                model.getTrash(),
                model.getStatus());
    }
    public void update(Building model){
        String sql = "Update Building Set (Name=?,Address = ?, Image = ?, Phone_number = ?, Number_of_floor = ?, Total_apartment = ?,Email = ?, Description= ?,Trash = ?, Status = ? Where Id_buiding = ?)";
        JdbcHelper.executeUpdate(sql,
                model.getId_building(),
                model.getName(),
                model.getAddress(),
                model.getImage(),
                model.getPhone_number(),
                model.getNumber_of_floor(),
                model.getTotal_apartment(),
                model.getEmail(),
                model.getDescription(),
                model.getTrash(),
                model.getStatus());
    }
    public void Delete(String Id_building){
        String sql="DELETE FROM Building WHERE Id_building=?";
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
        model.setId_building(rs.getString("Id_Building"));
        model.setName(rs.getString("Name"));
        model.setAddress(rs.getString("Address"));
        model.setImage(rs.getString("Image"));
        model.setPhone_number(rs.getInt("Phone_number"));
        model.setNumber_of_floor(rs.getInt("Number_of_floor"));
        model.setTotal_apartment(rs.getInt("Total_apartment"));
        model.setDescription(rs.getString("Description"));
        model.setTrash(rs.getInt("Trash"));      
        model.setStatus(rs.getInt("Status"));
        return model;
    }
    public List<Building> select(){
        String sql="SELECT * FROM Building";
        return select(sql);
    }
      public List<Building> selectByKeyword(String keyword){
        String sql="SELECT * FROM Building WHERE Id_Building LIKE ?";
        return select(sql, "%"+keyword+"%");
    }
