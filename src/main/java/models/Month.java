package models;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


public class Month extends Model {
    protected int id;
    protected long salaryId;
    protected String name, phone,position;
    protected boolean gender;
    protected Salary salary;
    protected Timestamp date,created;
    
    public Month() {
    }

    public int getId() {
        return id;
    }

    public long getSalaryId() {
        return salaryId;
    }

  
    public Timestamp getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPosition() {
        return position;
    }

   
    public boolean isGender() {
        return gender;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Salary getSalary() {
        return salary;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPosition(String position) {
        this.position = position;
    }

 

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
        if (salary != null) {
            this.salaryId = salary.getId();
        }
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

  


    @Override
    public String toString() {
        return name;
    }


    public static Month getFromResultSet(ResultSet rs) throws SQLException {
        Month e = new Month();
        e.setId(rs.getInt("id"));
         e.setName(rs.getNString("name"));
        e.setPhone(rs.getNString("phone"));
        e.setDate(rs.getTimestamp("date"));
        e.setGender(rs.getBoolean("gender"));
          e.setPosition(rs.getNString("position"));
         e.setSalaryId((int) rs.getLong("salary_id"));
          e.setCreated(rs.getTimestamp("created"));
        return e;
    }

    @Override
    public Object[] toRowTable() {
        return new Object[]{
            id, name,phone,date,gender,position,salary.getSalary()
          
     
        };
    }


  

}
