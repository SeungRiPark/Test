package htmltopdf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class gethtml {

	public String getOpenStreamHTML(String urlToRead) {
        String result = "";
        try {
            URL url = new URL(urlToRead);
 
            System.out.println("url=[" + url + "]");
            System.out.println("protocol=[" + url.getProtocol() + "]");
            System.out.println("host=[" + url.getHost() + "]");
            System.out.println("content=[" + url.getContent() + "]");
 
            InputStream is = url.openStream();
            int ch;
            while ((ch = is.read()) != -1) {
                System.out.print((char) ch);
                result += (char) ch;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
 
    public String getHttpHTML(String urlToRead) {
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        String result = "";
        try {
            url = new URL(urlToRead);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                result += line + "\n";
            }
            rd.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
   

}
