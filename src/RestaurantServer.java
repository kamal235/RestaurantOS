import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class RestaurantServer {


    public static void main(String[] args) throws IOException {

        HttpServer server=HttpServer.create(new InetSocketAddress("0.0.0.0",8080),0);
        server.createContext("/ingredients",exchange -> {
            String BodyMessage;
            int ResponseCode;
            String RequestType=exchange.getRequestMethod();
            if(RequestType.equals("GET")){
            BodyMessage=" Get Request Received ";
            ResponseCode=200;
            }
            else if(RequestType.equals("POST")){
            InputStream streamBody=exchange.getRequestBody();
            byte[] bodyBytes= streamBody.readAllBytes();
            String bodyMessage=new String(bodyBytes, StandardCharsets.UTF_8);
            System.out.println(bodyMessage);
            BreakString requestObj= new BreakString(bodyMessage, RequestType);
            BodyMessage=requestObj.getMessage();
            ResponseCode=requestObj.getStatus();
            System.out.println(requestObj.getMessage());
            }
            else{
            exchange.getResponseHeaders().set("Allow", "GET, POST");
            BodyMessage=" Unsupported Request Received ";
            ResponseCode=405;
            }

            exchange.getResponseHeaders().set("Content-Type","text/plain; charset=UTF-8");
            byte[] response= BodyMessage.getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(ResponseCode,response.length);
            exchange.getResponseBody().write(response);
            System.out.println("Ingredients endpoint reached");
            exchange.close();
        });
        server.start();
        System.out.println("RestaurantOS Server Started");

    }
}
