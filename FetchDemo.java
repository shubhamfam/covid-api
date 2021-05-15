import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		URL url;
		try {
			url = new URL("http://localhost:8081/covid_api/getdistrict?district_id=19");
			HttpURLConnection huc = (HttpURLConnection) url.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(huc.getInputStream()));

			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			huc.disconnect();

			System.out.println(response.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
