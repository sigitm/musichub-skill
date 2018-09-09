package it.musichub.skill.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.amazon.ask.model.services.ApiClient;
import com.amazon.ask.model.services.ApiClientRequest;
import com.amazon.ask.model.services.ApiClientResponse;
import com.amazon.ask.services.ApacheHttpApiClient;

import it.musichub.skill.rest.ex.ConnectionException;
import it.musichub.skill.rest.ex.RestClientException;

public class RestClient {
	
	private static final int TIMEOUT = 6; //seconds
    private static final String TEST_URI = "http://sigitm.synology.me:8080/";
    
    
    
    public static ApiClientResponse invokeEndpoint(String method, String route, Map<String, Object> params) throws RestClientException {
    	String baseUrl = TEST_URI+route;
    	URI uri;
		try {
			uri = new URI(baseUrl);
		} catch (URISyntaxException e) {
			throw new RestClientException("Invalid endpoint uri: "+baseUrl, e);
		}
    	URIBuilder ub = new URIBuilder(uri);
    	if (params != null){
	    	for (String key : params.keySet()) {
	    		String value = params.get(key) != null ? params.get(key).toString() : null;
	        	ub.addParameter(key, value);
	    	}
    	}
    	String url = ub.toString();
    	
    	
        ApiClientRequest request = generateRequest(method, url);

        RequestConfig config = RequestConfig.custom()
          .setConnectTimeout(TIMEOUT * 1000)
          .setConnectionRequestTimeout(TIMEOUT * 1000)
          .setSocketTimeout(TIMEOUT * 1000).build();
        CloseableHttpClient apacheClient = 
          HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        
        ApiClient client = ApacheHttpApiClient.custom().withHttpClient(apacheClient).build();

        try{
        	ApiClientResponse response = client.invoke(request);
        	return response;
        } catch (Exception e){
        	throw new ConnectionException("Error connecting to url "+url, e);
        }
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
