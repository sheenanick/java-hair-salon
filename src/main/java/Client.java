import org.sql2o.*;

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



}
