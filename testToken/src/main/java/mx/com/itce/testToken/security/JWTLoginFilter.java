package mx.com.itce.testToken.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import mx.com.itce.testToken.entity.User;


/***
 * Ahora tenemos todo configurado para usar JWT en nuestro proceso de autenticación. 
 * Primero echaremos un vistazo a la JWTLoginFilterclase. 
 * Esta clase interceptará las POST solicitudes en la /loginruta e intentará autenticar al usuario. 
 * Cuando el usuario se autentica con éxito, devolverá un JWT en el Authorizationencabezado de la respuesta. 
 *  
 * 
 * @author reneorozco
 *
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	public JWTLoginFilter(String url, AuthenticationManager authManager) {
	    super(new AntPathRequestMatcher(url));
	    setAuthenticationManager(authManager);
	  }

	/**Durante el intento de autenticación, que trata el attemptAuthenticationmétodo, recuperamos el nombre de usuario y la contraseña de la solicitud.
	 * Después de que se recuperan, usamos AuthenticationManagerpara verificar que estos detalles coincidan con un usuario existente.
	 * Si lo hace, ingresamos el método successfulAuthentication . En este método, buscamos el nombre del usuario autenticado y 
	 * lo pasamos a TokenAuthenticationService, que luego agregará un JWT a la respuesta.
	 * @see org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter#successfulAuthentication(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain, org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication attemptAuthentication( HttpServletRequest req, HttpServletResponse res) throws AuthenticationException, IOException, ServletException {
	    AccountCredentials creds = new ObjectMapper().readValue(req.getInputStream(), AccountCredentials.class);// mapeamos la credenciales
	    return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(),creds.getPassword(),Collections.emptyList() ) );// le pasamos los parametros a la clase para que nos regrese la autorizacion
	}
	
	@Override
	  protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,Authentication auth) throws IOException, ServletException {
	    TokenAuthenticationService.addAuthentication(res, auth.getName(),req);
	   // TokenAuthenticationService.addAuthentication(res, auth.getName(),req);
	  }
	
	

}
