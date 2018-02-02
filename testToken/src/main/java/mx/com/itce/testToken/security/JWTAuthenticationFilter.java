package mx.com.itce.testToken.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/**
 * Lo que hace este filtro es interceptar todas las solicitudes para validar la presencia del JWT, es decir,
 *  las que no se limitan  a / ni /users. 
 *  Esta validación se realiza con la ayuda de la TokenAuthenticationServiceclase
 * 
 */
public class JWTAuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)throws IOException, ServletException {
		Authentication authentication = TokenAuthenticationService.getAuthentication((HttpServletRequest)request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request,response);
		
	}

}
