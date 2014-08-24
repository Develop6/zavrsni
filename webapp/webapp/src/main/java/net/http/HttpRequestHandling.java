package net.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestHandling {
	
	private static final String USER_AGENT = "Mozilla/5.0";
	
	public static String sendPost(String targetURL, String urlParameters) throws IOException {
		URL url;
		HttpURLConnection connection = null;
		DataOutputStream wr = null;
		InputStream is = null;
		try {
			// Create connection
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded; charset=UTF-8");

			connection.setRequestProperty("Content-Length",
					"" + Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "hr");

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			// Send request
			wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			// Get Response
			is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {

            if (wr != null) {
            	wr.close();
            }
            if (is != null) {
            	is.close();
            }
			if (connection != null) {
				connection.disconnect();
			}
		}
	}	
	
	// HTTP GET request
	public static String sendGet(String targetURL, String urlParameters) throws IOException {
 
		HttpURLConnection connection = null;
		BufferedReader in = null;
		
		try {
			URL obj = new URL(targetURL + urlParameters);
			connection = (HttpURLConnection) obj.openConnection();
	 
			// optional default is GET
			connection.setRequestMethod("GET");
	 
			//add request header
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded; charset=UTF-8");			
			connection.setRequestProperty("User-Agent", USER_AGENT);
			connection.setRequestProperty("Content-Language", "hr");
	 
			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + targetURL);
			System.out.println("Response Code : " + responseCode);
	 
			in = new BufferedReader(
			        new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	 
			//print result
			return response.toString();
		}
		catch (Exception e) {
			
			e.printStackTrace();
			return null;			
		} finally {

            if (in != null) {
            	in.close();
            }
			if (connection != null) {
				connection.disconnect();
			}			
		}
 
	}	
	
}
