import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {
  private Client client;
  private Client client2;
  private Stylist stylist;

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
    stylist = new Stylist("John", "Doe", "John specializes in hair color and balayage highlights");
    client = new Client("Jane", "Smith", "Has visited two times. Used color Como Light Brown 7NGM.", 1);
    client2 = new Client("Jessica", "Jones", "Visits once a month. Always same bob cut with bangs.", 2);
  }

  @Test
  public void Client_instantiates_true() {
    assertTrue(client instanceof Client);
  }

  @Test
  public void Client_getId_true() {
    assertTrue(client.getId() > 0);
  }

  @Test
  public void Client_getFirstName_string() {
    assertEquals("Jane", client.getFirstName());
  }

  @Test
  public void Client_getLastName_string() {
    assertEquals("Smith", client.getLastName());
  }

  @Test
  public void Client_getNotes_string() {
    assertEquals("Has visited two times. Used color Como Light Brown 7NGM.", client.getNotes());
  }

  @Test
  public void Client_getFirstName_int() {
    assertEquals(1, client.getStylistId());
  }

  @Test
  public void Client_all_ArrayList() {
    assertTrue(Client.all().size() > 0);
  }

  @Test
  public void Client_allByStylist_ArrayList() {
    assertTrue(Client.allByStylist(1).size() > 0);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients *;";
      con.createQuery(sql).executeUpdate();
    }
  }

}
