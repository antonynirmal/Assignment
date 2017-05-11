package my.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import my.sample.service.GcdService;
import sample.my.web_service_app.GetGcd;
import sample.my.web_service_app.GetGcdList;
import sample.my.web_service_app.GetGcdListResponse;
import sample.my.web_service_app.GetGcdResponse;
import sample.my.web_service_app.GetGcdSum;
import sample.my.web_service_app.GetGcdSumResponse;

@Endpoint
public class GcdEndpoint {
	private static final String NAMESPACE_URI = "http://my.sample/web-service-app";

	@Autowired
	private GcdService gcdService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getGcd")
	@ResponsePayload
	public GetGcdResponse gcd(@RequestPayload GetGcd request) {
		GetGcdResponse response = new GetGcdResponse();
		response.setHeadgcd(gcdService.getGcd());
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getGcdList")
	@ResponsePayload
	public GetGcdListResponse gcd(@RequestPayload GetGcdList request) {
		GetGcdListResponse response = new GetGcdListResponse();
		response.setGcdList(gcdService.getList().toString());
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getGcdSum")
	@ResponsePayload
	public GetGcdSumResponse gcd(@RequestPayload GetGcdSum request) {
		GetGcdSumResponse response = new GetGcdSumResponse();
		response.setGcdSum(gcdService.gcdSum());
		return response;
	}
}
