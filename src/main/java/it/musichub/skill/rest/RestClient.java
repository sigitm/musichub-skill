package it.musichub.skill.rest;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.amazon.ask.model.services.ApiClient;
import com.amazon.ask.model.services.ApiClientRequest;
import com.amazon.ask.model.services.ApiClientResponse;
import com.amazon.ask.services.ApacheHttpApiClient;

public class RestClient {


    private static final String TEST_URI = "http://sigitm.synology.me:8080/";
    
    public static ApiClientResponse invokeEndpoint(String method, String route) throws Exception {
        ApiClientRequest request = generateRequest(method, TEST_URI+route);

        int timeout = 6; //seconds
        RequestConfig config = RequestConfig.custom()
          .setConnectTimeout(timeout * 1000)
          .setConnectionRequestTimeout(timeout * 1000)
          .setSocketTimeout(timeout * 1000).build();
        CloseableHttpClient apacheClient = 
          HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        
        ApiClient client = ApacheHttpApiClient.custom().withHttpClient(apacheClient).build();

        ApiClientResponse response = client.invoke(request);
        
        return response;
    }
    
    
    
    private static ApiClientRequest generateRequest(String method, String uri) {
        return generateRequest(method, uri, null);
    }
    
    private static ApiClientRequest generateRequest(String method, String uri, String body) {
        ApiClientRequest request = new ApiClientRequest();
        request.setMethod(method);
        request.setUrl(uri);
        request.setBody(body);
        return request;
    }
//    
//    public static void main(String[] args) {
//		try {
//			ApiClientResponse response = invokeEndpoint("GET", "health");
//			System.out.println("response="+response.toString());
//			System.out.println("status="+response.getStatusCode());
//			System.out.println("body="+response.getBody());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
