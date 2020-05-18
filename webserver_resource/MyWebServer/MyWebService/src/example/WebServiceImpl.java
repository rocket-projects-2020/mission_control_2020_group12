//Reference for generating a random number range:
//https://www.mkyong.com/java/java-generate-random-integers-in-a-range/

//Reference for getting a http response (XML) and temperature to celsius via URL:
//https://www.chillyfacts.com/java-send-url-http-request-read-xml-response/
//https://stackoverflow.com/questions/19477324/how-do-i-calculate-the-temperature-in-celsius-returned-in-openweathermap-org-jso
package example;

import javax.jws.WebService;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

@WebService(endpointInterface = "example.WebServiceInterface",portName = "webservicePort",serviceName = "WebService")

public class WebServiceImpl implements WebServiceInterface {

	@Override
	public String greet(String clientName) {
		return "Hi," + clientName + ", this is WebService! (Your local weather finder)";
	}

	String result;
	String url;
	public void getHttpXmlWeather(String cityName, String countryName){
		try {
			url = "https://api.openweathermap.org/data/2.5/weather?&q="+cityName+","+countryName+"&APPID=9dbce15b12f75333f8f274f51793d0ff&mode=xml&units=metric";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			//int responseCode = con.getResponseCode(); //To verify a 200 connection "OK"
			//System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new InputSource(new StringReader(response.toString())));
			NodeList errNodes = doc.getElementsByTagName("current");//Look at XML main element
			if (errNodes.getLength() > 0) {
				Element err = (Element)errNodes.item(0);//Default Listing inside current
				result = "Temperature: "+((Element) err.getElementsByTagName("temperature").item(0)).getAttributeNode("value").getTextContent();
				//Search for element temperature and choose the value attribute (option)
			} else {
				// success
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}


	@Override
	public String realTime(String cityName, String countryName) {
		getHttpXmlWeather(cityName, countryName);
		return "The real time temperature of "+ cityName +" in "+ countryName +" above is " + result + " degrees celsius";
	}
}