import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {
  private Stylist stylist;

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
    stylist = new Stylist("John", "Doe", "John specializes in hair color and balayage highlights");
  }

  @Test
  public void Stylist_instantiates_true() {
    assertTrue(stylist instanceof Stylist);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists *;";
      con.createQuery(sql).executeUpdate();
    }
  }

}
