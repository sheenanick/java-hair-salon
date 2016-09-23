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

    post("/stylists/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String firstname = request.queryParams("firstname");
      String lastname = request.queryParams("lastname");
      String description = request.queryParams("description");
      Stylist stylist = new Stylist(firstname, lastname, description);
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:stylistid", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params(":stylistid"));
      model.put("stylist", Stylist.find(id));
      model.put("clients", Client.allByStylist(id));
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //get("stylists/:id/clients/new"
    get("/:stylistid/clients/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params(":stylistid"));
      model.put("stylist", Stylist.find(id));
      model.put("template", "templates/client-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //post("/stylists/:id/clients/new"
    post("/:stylistid/clients/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String firstname = request.queryParams("firstname");
      String lastname = request.queryParams("lastname");
      String notes = request.queryParams("notes");
      int stylistid = Integer.parseInt(request.params(":stylistid"));
      Client client = new Client(firstname, lastname, notes, stylistid);
      response.redirect("/stylists/" + stylistid);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:id/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params(":id"));
      model.put("stylist", Stylist.find(id));
      model.put("template", "templates/stylist-edit.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists/:id/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String firstname = request.queryParams("firstname");
      String lastname = request.queryParams("lastname");
      String description = request.queryParams("description");
      int stylistid = Integer.parseInt(request.params(":id"));
      Stylist.update(stylistid, firstname, lastname, description);
      response.redirect("/stylists/" + stylistid);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params(":id"));
      Stylist.delete(id);
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:stylistid/clients/:clientid/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int clientid = Integer.parseInt(request.params(":clientid"));
      int stylistid = Integer.parseInt(request.params(":stylistid"));
      model.put("client", Client.find(clientid));
      model.put("stylist", Stylist.find(stylistid));
      model.put("template", "templates/client-edit.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //post("/stylists/:stylistid/clients/:clientid/edit"
    post("/:stylistid/clients/:clientid/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String firstname = request.queryParams("firstname");
      String lastname = request.queryParams("lastname");
      String notes = request.queryParams("notes");
      int stylistid = Integer.parseInt(request.params(":stylistid"));
      int clientid = Integer.parseInt(request.params(":clientid"));
      Client.update(clientid, firstname, lastname, notes, stylistid);
      response.redirect("/stylists/" + stylistid);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //get("/stylists/:stylistid/clients/:clientid/delete"
    get("/:clientid/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int clientid = Integer.parseInt(request.params(":clientid"));
      Client client = Client.find(clientid);
      int stylistid = client.getStylistId();
      // int stylistid = Integer.parseInt(request.params(":stylistid"));
      Client.delete(clientid);
      response.redirect("/stylists/" + stylistid);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
