import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/stylist-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //post("/stylists/new"
    post("/stylists-new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String firstname = request.queryParams("firstname");
      String lastname = request.queryParams("lastname");
      String description = request.queryParams("description");
      Stylist stylist = new Stylist(firstname, lastname, description);
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params(":id"));
      model.put("stylist", Stylist.find(id));
      model.put("clients", Client.allByStylist(id));
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //get("stylists/:id/clients/new"
    get("/:id/clients/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params(":id"));
      model.put("stylist", Stylist.find(id));
      model.put("template", "templates/client-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //post("/stylists/:id/clients/new"
    post("/:idclients-new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String firstname = request.queryParams("firstname");
      String lastname = request.queryParams("lastname");
      String notes = request.queryParams("notes");
      int stylistid = Integer.parseInt(request.queryParams("stylistid"));
      Client client = new Client(firstname, lastname, notes, stylistid);
      response.redirect("/stylists/" + stylistid);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    
  }
}
