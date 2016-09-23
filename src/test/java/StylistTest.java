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

  @Test
  public void Stylist_getId_true() {
    assertTrue(stylist.getId() > 0);
  }

  @Test
  public void Stylist_getFirstName_string() {
    assertEquals("John", stylist.getFirstName());
  }

  @Test
  public void Stylist_getLastName_string() {
    assertEquals("Doe", stylist.getLastName());
  }

  @Test
  public void Stylist_getDescription_string() {
    assertEquals("John specializes in hair color and balayage highlights", stylist.getDescription());
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists *;";
      con.createQuery(sql).executeUpdate();
    }
  }

}
