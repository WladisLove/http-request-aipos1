package Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequests {

    public void makeRequest(String requestName, String url){
        switch (requestName){
            case "GET":
                sendGetRequest(url);
                break;
            case "POST":
                sendPostRequest(url);
                break;
            case "HEAD":
                sendHeadRequest(url);
                break;
            default:
                System.out.println("Incorrect request name");
        }
    }

    public String sendGetRequest(String url){
        HttpURLConnection connection = null;
        String response = "";
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            //connection.setConnectTimeout(500);
            //connection.setReadTimeout(500);

            connection.connect();
            StringBuilder stringBuilder = new StringBuilder();

            if(HttpURLConnection.HTTP_OK == connection.getResponseCode()){
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "cp1251"));

                String line;
                while ((line = in.readLine()) != null){
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                }

                response = stringBuilder.toString();
            }
            else{
                response = "fail: "+ connection.getResponseCode() + ", " + connection.getResponseMessage();
            }
        }
        catch (Throwable cause){
            cause.printStackTrace();
        }
        finally {
            if(connection != null){
                connection.disconnect();
            }
        }
        return response;
    }

    public String sendPostRequest(String url){
        HttpURLConnection connection = null;
        String response = "";

        return response;
    }

    public String sendHeadRequest(String url){
        HttpURLConnection connection = null;
        String response = "";

        return response;
    }
}