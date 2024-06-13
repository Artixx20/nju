import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Person person = new Person("Jane Doe", 22);
        // Person person = new Person("James Bond", 54, 1);
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "root")) {

            // System.out.println(conn.isClosed());

            // insertPerson(conn, person);
            // readPerson(conn);
            // updatePerson(conn, person);
            // deletePerson(conn, 1);

        } catch (SQLException ex) {
            System.out.println("Error in database connection: \n" + ex.getMessage());
        }
    }

    public static void insertPerson(Connection conn, Person person) throws SQLException {
        /*
        Statement st = conn.createStatement();
        String query = "Insert into persons values (null, '" + person.getName() + "', " + person.getAge() + ")";
        st.execute(query);
        */
        String query = "Insert into persons (name, age) values (?, ?)";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, person.getName());
        st.setString(2, String.valueOf(person.getAge()));
        st.execute();
    }

    public static void readPerson(Connection conn) throws SQLException {
        List<Person> personList = new ArrayList<>();
        Statement st = conn.createStatement();
        String query = "select * from persons";
        st.executeQuery(query);
        ResultSet rs = st.getResultSet();
        while (rs.next()) {
            Person person = new Person(rs.getString("name"), rs.getInt("age"), rs.getInt("id"));
            personList.add(person);
        }

        for (Person p : personList) {
            System.out.println(p);
        }
    }

    public static void updatePerson(Connection conn, Person person) throws SQLException {
        /*
        Statement st = conn.createStatement();
        String query = "update persons set name = 'Jeniffer Douglass' where id=3";
        st.execute(query);
        */

        String query = "update persons set name=?, age=? where id=?";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, person.getName());
        st.setString(2, String.valueOf(person.getAge()));
        st.setString(3, String.valueOf(person.getId()));
        st.execute();
    }

    public static void deletePerson(Connection conn, int id) throws SQLException {
        String query = "delete from persons where id=?";
        PreparedStatement st = conn.prepareStatement(query);
        st.setInt(1, id);
        // st.execute();
        System.out.println(st.executeUpdate());
    }
}
