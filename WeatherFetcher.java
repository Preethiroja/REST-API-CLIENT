
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherFetcher {

    public static void main(String[] args) {
        try {
            // Step 1: Set the API endpoint with latitude & longitude (Chennai as an example)
            String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=13.08&longitude=80.27&current_weather=true";
            // Step 2: Create URL and open connection
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // Step 3: Check response code
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Step 4: Read the response
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                // Step 5: Parse JSON response
                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONObject weather = jsonResponse.getJSONObject("current_weather");
                // Step 6: Display structured data
                System.out.println("Weather Information (Chennai):");
                System.out.println("Temperature: " + weather.getDouble("temperature") + " °C");
                System.out.println("Wind Speed: " + weather.getDouble("windspeed") + " km/h");
                System.out.println("Time: " + weather.getString("time"));

            } else {
                System.out.println("API request failed. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
