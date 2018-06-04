package proz;

import java.io.StringReader;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class AverageGetter {
	private URI uri;
	private Client client = ClientBuilder.newClient();
	
	public String getAverage(String table, String code, String topCount) {
		uri = UriBuilder.fromUri("http://api.nbp.pl/api/exchangerates/rates/"+table+"/"+code+"/last/"+topCount).build();
		WebTarget target = client.target(uri);
		String str = target.request().accept(MediaType.TEXT_XML).get(String.class);

		try {
			JAXBContext context = JAXBContext.newInstance(ExchangeRatesSeries.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			ExchangeRatesSeries exchange = (ExchangeRatesSeries) unmarshaller.unmarshal(new StringReader(str));
			return String.valueOf(exchange.getAverage());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return "0";
	}
}
