package proz;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/exchangerates/rates")
public class RateGetter {
	AverageGetter getter = new AverageGetter();

	@GET
	@Path("{table}/{code}/{topCount}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getTEXT_PLAIN(@PathParam("table") String table, @PathParam("code") String code, @PathParam("topCount") String topCount) {
		return getter.getAverage(table, code, topCount);
	}

	@GET
	@Path("{table}/{code}/{topCount}")
	@Produces(MediaType.TEXT_HTML)
	public String getTEXT_HTML(@PathParam("table") String table, @PathParam("code") String code, @PathParam("topCount") String topCount) {
		return "<html><body><h1>"+getter.getAverage(table, code, topCount)+"</h1></body></html>";
	}
	
	@GET
	@Path("{table}/{code}/{topCount}")
	@Produces(MediaType.TEXT_XML)
	public String getTEXT_XML(@PathParam("table") String table, @PathParam("code") String code, @PathParam("topCount") String topCount) {
		return "<?xml version=\"1.0\"?>"+"<average>"+getter.getAverage(table, code, topCount)+"</average>";
	}

	@GET
	@Path("{table}/{code}/{topCount}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAPPLICATION_JSON(@PathParam("table") String table, @PathParam("code") String code, @PathParam("topCount") String topCount) {
		JsonObjectBuilder JSBuilder = Json.createObjectBuilder();
		JSBuilder.add("average", getter.getAverage(table, code, topCount));
		return (JSBuilder.build()).toString();
	}

}