package mx.com.itce.testToken.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import mx.com.itce.testToken.entity.User;

import static java.util.Collections.emptyList;

import java.io.IOException;

/**Clase para verificar el Token 
 * 
 * @author reneorozco
 *
 */
public class TokenAuthenticationService {

	  static final long EXPIRATIONTIME = 864_000_000; // 10 days
	  static final String SECRET = "ThisIsASecret"; // esta es la palabra secreta
	  static final String TOKEN_PREFIX = "Bearer"; // con esta palabra empezara
	  static final String HEADER_STRING = "Authorization"; // tipo de header Autorization
	  //Creamos el token
	  static void addAuthentication(HttpServletResponse res, String name,HttpServletRequest request)
	  {
		  String ip = null; // IP del cliente
		  String host = null; // Host del cliente
		  ip =  request.getHeader("x-forwarded-for");
		  host = request.getRemoteHost();
		  //ARMAMOS EL PAYLOAD PARA CREAR EL JWT
		  String JWT = Jwts.builder()
			        .setSubject(name)// aqui puede irI O MAS  argumento.setSubject("Otro argumento")
			        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME)) // aqui ponemos la expiracion del token
			        .signWith(SignatureAlgorithm.HS512, SECRET)// algoritmo de Incriptacion de la palabra secreta
			        .compact();
			  
			   res.addHeader("IP", ip);
			   res.addHeader("HOST", host);
			   res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);// ENVIAMOS EN EL HEADER LA RESPUESTA  "Authorization" Bearer "AEklñererlerlel"
			   res.setStatus(res.SC_OK);//ENVIAMOS EL STATUS 200 DE OK
			   String json="{\"Token\":\"JWT\"}";//TODO PODEMOS USAR LA LIBRERIA GSON PONEMOS EN UN JSON EL TOKEN PARA ENVIARLO AL BODY
			   json=json.replace("JWT", JWT);
			   res.setContentType("application/json");
			   res.setCharacterEncoding("UTF-8");
			   try {res.getWriter().write(json);} catch (IOException e) {e.printStackTrace();} //Enviamos en 
			  System.out.println( request.getServerName() + " " + request.getLocalAddr() );
			  System.out.println( "ip : "+ip);
			  System.out.println( "host : " +host);
	  }
	  //verificamos el token
	  static Authentication getAuthentication(HttpServletRequest request) {
		    String token = request.getHeader(HEADER_STRING);// recibimos el header
		    if (token != null) {// si no es nullo
		      // parse the token.
		      String user = Jwts.parser() // verificamos el token
		          .setSigningKey(SECRET) // con la palabra secreta
		          .parseClaimsJws(token.replace(TOKEN_PREFIX, "")) // y los claims replazamos el prefijo Bearer y el espacio 
		          .getBody()//obtenemos el cuerpo del token
		          .getSubject();//lo verificamos

		      return user != null ? new UsernamePasswordAuthenticationToken(user, null, emptyList()) : null;// si el usuarion es difernte de null regresamos la autenticacion correcta en caso contrario null
		    }
		    return null;
		  }
}
