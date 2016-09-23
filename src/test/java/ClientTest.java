import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {
  private Client client;
  private Stylist stylist;

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
    stylist = new Stylist("John", "Doe", "John specializes in hair color and balayage highlights");
    client = new Client("Jane", "Smith", "Has visited two times. Used color Como Light Brown 7NGM.", 1);
  }

  @Test
  public void Client_instantiates_true() {
    assertTrue(client instanceof Client);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients *;";
      con.createQuery(sql).executeUpdate();
    }
  }

}
