package Controller;

import Model.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class HttpRequest {
    private PrintWriter out;
    private BufferedReader in;
    private static final String charset = "utf-8";
    private Request request;

    public HttpRequest(String method, String url, String host, String headers, String body){
        this.request = new Request(method, url, host, headers, body);
    }

    public String getRequest(){
        return request.getRequest();
    }

    public String getResponse() {
        String response = "";
        try {
            InetAddress address = InetAddress.getByName(request.getHost());

            Socket socket = new Socket(address, 80);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), charset));
            out.println(request.getRequest());

            response = generateResponse();

            socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }

    private String generateResponse() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        while (i != -1) {
            try {
                i = in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stringBuilder.append((char) (i));
        }
        return stringBuilder.toString();
    }

}