package Zoo_Project;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    Connection conn;
    Statement st;
    PreparedStatement prst;

    private void getConnection() {

        try {
            this.conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_tekrar", "dev_user", "password");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    private void getStatement() {
        try {
            this.st = conn.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getPreparedStatement(String sql) {
        try {
            this.prst = conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createAnimalsTable() {

        getConnection();
        getStatement();
        String query = "create table if not exists t_animals" +
                "(id serial, name varchar(50), species varchar(50)," +
                " age int, gender char(1), health_status varchar(6)," +
                "cage_no int )";
        try {
            st.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void createVisitorsTable() {

        getConnection();
        getStatement();
        String query = "CREATE SEQUENCE if not exists ticket_seq START WITH 1001; create table if not exists t_visitors" +
                "(id serial, firstName varchar(50), lastName varchar(50)," +
                " entryTime   timestamp, ticketNumber INT DEFAULT nextval('ticket_seq'))";
        // burada serial degerini default olarak 1001 den baslatiyoruz
        try {
            st.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void getList(String table) {

        getConnection();
        getStatement();
        String query = "SELECT * FROM " + table + " order by id";
        //  String query = "SELECT * FROM ?";// tablo isimleri place holder (?)' yazilamaz
        // o yuzden preparedStatement kullnamaya gerek kalmaz.
        try {
            ResultSet rs = st.executeQuery(query);
            System.out.println("+" + "-".repeat(96) + "+");
            if (table.equals("t_animals"))
                System.out.printf("| %-3s | %-15s | %-15s | %-5s | %-15s | %-15s | %-8s |\n", "ID", "Name", "Species", "Age", "Gender", "Health Status", "Cage No");
            else
                System.out.printf("| %-3s | %-15s | %-10s | %-25s | %-15s |\n", "ID", "Name", "Lastname", "Entry Time", "Ticket Number");
            while (rs.next()) {

                if (table.equals("t_animals")) {
                    System.out.printf("| %-3s | %-15s | %-15s | %-5s | %-15s | %-15s | %-8s |\n",
                            rs.getObject(1), rs.getObject(2), rs.getObject(3), rs.getObject(4),
                            rs.getObject(5), rs.getObject(6), rs.getObject(7));
                } else {
                    System.out.printf("| %-3s | %-15s | %-10s | %-25s | %-15s | \n",
                            rs.getObject(1), rs.getObject(2), rs.getObject(3), rs.getObject(4),
                            rs.getObject(5));
                }
            }
            System.out.println("+" + "-".repeat(96) + "+");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void updateAnimal(Animal animal, int id) {

        getConnection();
        System.out.println();
        String query = "update  t_animals set name=?, species=?, age=?, gender=?, health_status=?, cage_no=? where id =" + id;
        getPreparedStatement(query);
        try {
            prst.setString(1, animal.getName());
            prst.setString(2, animal.getSpecies());
            prst.setInt(3, animal.getAge());
            prst.setString(4, animal.getGender());
            prst.setString(5, animal.getHealth_status());
            prst.setInt(6, animal.getCage_no());
            if (prst.executeUpdate() != 0) {
                System.out.println("Animal has been updated successfully...");
            } else System.out.println("Updating operation is failed...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                prst.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void addAnimal(Animal animal) {
        getConnection();
        System.out.println();
        String query = "insert into t_animals( name, species, age, gender, health_status, cage_no) " +
                " values(?,?,?,?,?,?)";
        getPreparedStatement(query);
        try {
            prst.setString(1, animal.getName());
            prst.setString(2, animal.getSpecies());
            prst.setInt(3, animal.getAge());
            prst.setString(4, animal.getGender());
            prst.setString(5, animal.getHealth_status());
            prst.setInt(6, animal.getCage_no());
            if (prst.executeUpdate() != 0) {
                System.out.println("Animal has been added successfully...");
            } else System.out.println("Operation is failed...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                prst.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void delete(int id, String table) {
        getConnection();
        getStatement();
        String query = " delete from " + table + " where id = " + id;
        try {
            if (st.executeUpdate(query) != 0) {
                System.out.println("deleted successfuly...");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean findById(int id, String table) {
        getConnection();
        getStatement();
        String query = "SELECT * FROM " + table + " WHERE id=" + id;
        try {
            return st.execute(query);// Eger ki bana bir yada birden fazla kayit dondururse true vericek.
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    public <T> List<T> filter(String table, String n) {
        getConnection();
        getStatement();
        List<T> list = new ArrayList<>();
        String query = "";
        if (table.equals("t_animals")) {
            query = "select * from " + table + " where name ilike '%" + n + "%' or species ilike '%" + n + "%' or health_status ilike '%" + n + "%'  ";
        } else
            query = "select * from " + table + " where firtName ilike '%" + n + "%' or lastName ilike '%" + n + "%' or id ilike '%" + n + "%'";
        try {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                if (table.equals("t_animals")) {
                    Animal animal = new Animal();
                    animal.setId(rs.getInt("id"));
                    animal.setName(rs.getString("name"));
                    animal.setSpecies(rs.getString("Species"));
                    animal.setAge(rs.getInt("age"));
                    animal.setGender(rs.getString("gender"));
                    animal.setHealth_status(rs.getString("health_status"));
                    animal.setCage_no(rs.getInt("cage_no"));
                    list.add((T) animal);
                } else {
                    Visitors visitor = new Visitors();
                    visitor.setId(rs.getInt("id"));
                    visitor.setFirstName(rs.getString("firstName"));
                    visitor.setLastName(rs.getString("lastName"));
                    visitor.setEntryTime(LocalDateTime.parse(rs.getString("entryTime")));
                    visitor.setTicketNumber(rs.getInt("ticketNumber"));
                    list.add((T) visitor);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return list;
    }

    public void addVisitor(Visitors visitor) {

        getConnection();
        String sql = "insert into t_visitors(firstName, lastName, entryTime) values(?,?,to_timestamp(?,'YYYY-MM-DD HH24:MI:SS'))";
        getPreparedStatement(sql);

        try {
            prst.setString(1, visitor.getFirstName());
            prst.setString(2, visitor.getLastName());
            prst.setTimestamp(3, Timestamp.valueOf((visitor.getEntryTime())));

            if (prst.executeUpdate() > 0) {
                System.out.println("You have inserted a visitor successfully");
            } else System.out.println("not inserted");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                prst.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void updateVisitor(Visitors visitor, int id) {

        getConnection();
        System.out.println();
        String query = "update  t_visitors set firstName=?, lastName=? where id =" + id;
        getPreparedStatement(query);
        try {
            prst.setString(1, visitor.getFirstName());
            prst.setString(2, visitor.getLastName());
            if (prst.executeUpdate() != 0) {
                System.out.println("The visitor has been updated successfully...");
            } else System.out.println("Updating operation is failed...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                prst.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}