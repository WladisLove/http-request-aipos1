package Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequests {

    public String makeRequest(String requestName, String url){
        switch (requestName){
            case "GET":
                return sendGetRequest(url);
            case "POST":
                return sendPostRequest(url);
            case "HEAD":
                return sendHeadRequest(url);
            default:
                return "Incorrect request name";
        }
    }

    private String sendGetRequest(String url){
        HttpURLConnection connection = null;
        String response = "";
        try {
            connection = (HttpURLConnection) new URL(url+"/get").openConnection();
            connection.setRequestMethod("GET");
            //connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setUseCaches(false);
            //connection.setConnectTimeout(500);
            //connection.setReadTimeout(500);

            connection.connect();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Response Code : " + connection.getResponseCode());

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

    private String sendPostRequest(String url){
        HttpURLConnection connection = null;
        String response = "";
        try {
            connection = (HttpURLConnection) new URL(url+"/post").openConnection();
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);

            connection.connect();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Response Code : " + connection.getResponseCode());

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

    private String sendHeadRequest(String url){
        HttpURLConnection connection = null;
        String response = "";
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            connection.setUseCaches(false);

            connection.connect();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Response Code : " + connection.getResponseCode());

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
}