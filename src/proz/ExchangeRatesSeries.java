package proz;

import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ExchangeRatesSeries")
public class ExchangeRatesSeries {
	@XmlElement(name = "Table")
	private String Table;
	@XmlElementWrapper(name = "Rates")
	@XmlElement(name = "Rate")
	private List<Rate> RateList = new ArrayList<Rate>();

	public double getAverage() {
		double sum = 0.0;
		for (Rate i : RateList) sum += Double.parseDouble(i.Ask);
		double finalSum = Math.round((sum/RateList.size())*1000.0)/1000.0;
		
		return finalSum;
	}
	
	public static class Rate {
		@XmlElement(name = "Ask")
		private String Ask;
		@XmlElement(name = "Mid")
		private String Mid;
	}
}


