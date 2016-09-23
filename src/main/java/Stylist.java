import org.sql2o.*;
import java.util.List;

public class Stylist {
  private int id;
  private String firstname;
  private String lastname;
  private String description;

  public Stylist(String firstname, String lastname, String description) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.description = description;

    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists (firstname, lastname, description) VALUES (:firstname, :lastname, :description)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("firstname", this.firstname)
        .addParameter("lastname", this.lastname)
        .addParameter("description", this.description)
        .executeUpdate()
        .getKey();
    }
  }

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstname;
  }

  public String getLastName() {
    return lastname;
  }

  public String getDescription() {
    return description;
  }

  public static List<Stylist> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists";
      return con.createQuery(sql)
      .executeAndFetch(Stylist.class);
    }
  }

  public static Stylist find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists WHERE id = :id";
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Stylist.class);
    }
  }

  public static void update(int id, String firstname, String lastname, String description) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stylists SET firstname = :firstname, lastname = :lastname, description = :description WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("firstname", firstname)
        .addParameter("lastname", lastname)
        .addParameter("description", description)
        .executeUpdate();
    }
  }

  public static void delete(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

}
