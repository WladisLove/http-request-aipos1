package Controller;

import Model.Request;
import Model.Response;

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
    public Response response;

    private boolean remakeRequest;

    public HttpRequest(String method, String url, String host, String headers, String body){
        this.request = new Request(method, url, host, headers, body);
        this.remakeRequest = false;
    }

    public String getRequest(){
        return request.getRequest();
    }

    public String getResponse() {
        //String response = "";
        try {
            InetAddress address = InetAddress.getByName(request.getHost());

            Socket socket = new Socket(address, 80);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), charset));
            out.println(request.getRequest());

            response = new Response(generateResponse());

            socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return response.getResponse();
    }

    public String getStatusCodeInfo() {
        int code = response.getStatusCode();
        String info = "Code: " + code + "\n";

        switch (code) {
            case Response.HTTP_OK:
                return info + "«хорошо»\n";
           case Response.HTTP_CREATED:
               return info + "«создано»\n";
            case Response.HTTP_ACCEPTED:
                return info + "«принято»\n";
            case Response.HTTP_MOVED_PERM:
                makeRedirect();
                return info + "«перемещено навсегда»"
                        + "\nПеренаправлено на " + response.getLocation() + "\n";
            case Response.HTTP_MOVED_TEMP:
                makeRedirect();
                return info + "«перемещено временно»"
                        + "\nПеренаправлено на " + response.getLocation() + "\n";
            case Response.HTTP_BAD_REQUEST:
                return info + "«плохой, неверный запрос»\n";
            case Response.HTTP_UNAUTHORIZED:
                return info + "«не авторизован»\n";
            case Response.HTTP_PAYMENT_REQUIRED:
                return info + "«необходима оплата»\n";
            case Response.HTTP_FORBIDDEN:
                return info + "«запрещено»\n";
            case Response.HTTP_NOT_FOUND:
                return info + "«не найдено»\n";
            case Response.HTTP_INTERNAL_ERROR:
                return info + "«внутренняя ошибка сервера»\n";
            case Response.HTTP_NOT_IMPLEMENTED:
                return info + "«не реализовано»\n";
            case Response.HTTP_BAD_GATEWAY:
                return info + "«плохой, ошибочный шлюз»\n";
            case Response.HTTP_UNAVAILABLE:
                return info + "«сервис недоступен»\n";
            case Response.HTTP_GATEWAY_TIMEOUT:
                return info + "«шлюз не отвечает»\n";
        }

        return info;
    }

    private void makeRedirect(){
        this.remakeRequest = true;
        String location = response.getLocation();
        int separ = location.indexOf("/");

        this.request.setHost(location.substring(0,separ));
        this.request.setUri(location.substring(separ));
    }

    public boolean remakeRequest(){
        return this.remakeRequest;
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