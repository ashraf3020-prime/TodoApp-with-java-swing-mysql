import java.sql.*;
import java.util.ArrayList;

public class TaskDAO {

    public void addTask(String task) {

        try {

            Connection con = DatabaseConnection.getConnection();

            String sql = "INSERT INTO tasks(task) VALUES(?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, task);

            ps.executeUpdate();

            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public void deleteTask(int id) {

        try {

            Connection con = DatabaseConnection.getConnection();

            String sql = "DELETE FROM tasks WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public ArrayList<Task> getAllTasks() {

        ArrayList<Task> list = new ArrayList<>();

        try {

            Connection con = DatabaseConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM tasks");

            while (rs.next()) {

                list.add(

                        new Task(

                                rs.getInt("id"),

                                rs.getString("task")

                        )

                );

            }

            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return list;

    }

}