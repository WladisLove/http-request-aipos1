package Model;

public class Request {
    private String method;
    private String uri;
    private String protocol;
    private String host;
    private String headers;
    private String body;

    public Request(String method, String uri, String host, String headers, String body){
        this.method = method;
        this.uri = uri;
        this.protocol = "HTTP/1.1";
        this.host = host;
        this.headers = headers;
        this.body = body;
    }

    public String getRequest(){
        String headersLine = "";
        if(!headers.equals("")){
            headersLine = headers.replace(';','\n');
            headersLine = headersLine +"\n";
        }
        return method +" "+ uri +" "+ protocol + "\n" +
                "Host: " + host + "\n" +
                headersLine +
                "\n" +
                body;
    }

    public void setHost(String host){
        this.host = host;
    }

    public void setUri(String uri){
        this.uri = uri;
    }

    public String getHost(){
        return host;
    }

}
