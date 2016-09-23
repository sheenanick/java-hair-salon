import org.sql2o.*;
import java.util.List;

public class Client {
  private int id;
  private String firstname;
  private String lastname;
  private String notes;
  private int stylistid;

  public Client(String firstname, String lastname, String notes, int stylistid) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.notes = notes;
    this.stylistid = stylistid;

    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (firstname, lastname, notes, stylistid) VALUES (:firstname, :lastname, :notes, :stylistid)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("firstname", this.firstname)
        .addParameter("lastname", this.lastname)
        .addParameter("notes", this.notes)
        .addParameter("stylistid", this.stylistid)
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

  public String getNotes() {
    return notes;
  }

  public int getStylistId() {
    return stylistid;
  }

  public static List<Client> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients";
      return con.createQuery(sql)
        .executeAndFetch(Client.class);
    }
  }

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE id = :id";
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Client.class);
    }
  }

  public static List<Client> allByStylist(int stylistid) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE stylistid = :id";
      return con.createQuery(sql)
        .addParameter("id", stylistid)
        .executeAndFetch(Client.class);
    }
  }

  public static void update(int id, String firstname, String lastname, String notes, int stylistid) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET firstname = :firstname, lastname = :lastname, notes = :notes, stylistid = :stylistid WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("firstname", firstname)
        .addParameter("lastname", lastname)
        .addParameter("notes", notes)
        .addParameter("stylistid", stylistid)
        .executeUpdate();
    }
  }

  public static void delete(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

}
