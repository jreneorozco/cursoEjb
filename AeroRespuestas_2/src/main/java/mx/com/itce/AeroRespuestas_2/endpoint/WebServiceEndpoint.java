package mx.com.itce.AeroRespuestas_2.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import mx.com.itce.vucem.PeticionRespuesta;
import mx.com.itce.vucem.PeticionRespuestaResponse;
import myproject.wsdl.currency.ObjectFactory;

@Endpoint
public class WebServiceEndpoint {

	private static final String NAMESPACE_URI = "http://service.ws.respuesta.aereos.privados.www.ventanillaunica.gob.mx/";
	private static final Logger logger = LoggerFactory.getLogger(WebServiceEndpoint.class);
	
//	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "inputSOATest")
//	@ResponsePayload
//	public OutputSOATest hello(@RequestPayload InputSOATest request) {
//		byte[] bytes = request.getTest();
//		String s = new String(bytes);
//		logger.info("Esta es una prueba " + s);
//		ObjectFactory factory = new ObjectFactory();
//		OutputSOATest response = factory.createOutputSOATest();
//		response.setResult(true);
//		return response;
//	}
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "peticionRespuesta")
	@ResponsePayload
	public PeticionRespuestaResponse hello(@RequestPayload PeticionRespuesta request) {
		byte[] bytes = request.getRespuesta();
		String s = new String(bytes);
		logger.info("Esta es una prueba " + s);
		ObjectFactory factory = new ObjectFactory();
		PeticionRespuestaResponse response = new PeticionRespuestaResponse();
		response.setResult(true);
	
		return response;
	}
}
