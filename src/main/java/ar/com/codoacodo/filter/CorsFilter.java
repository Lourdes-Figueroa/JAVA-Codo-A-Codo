package ar.com.codoacodo.filter;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"/*"})// ---->indica que es lo que va a filtrar, en esta caso las peticiones 
public class CorsFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		var origenesPermitidos = List.of("http://localhost:50500", "http://127.0.0.1:50500","http://127.0.0.1:5500","http://127.0.0.1:5500/");// ----> una lista de url permitidas
		
		String origin = ((HttpServletRequest) request).getHeader("origin"); // ---> capturo del req el origen por el cual se envia
		
		if (origenesPermitidos.contains(origin)) { // ---> si mi lista de origenes contiene el origen recibido
			
			((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", origin);//---> mandamos por el header del res que el origen fue aceptado
			((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, DELETE, PUT, HEAD");
			((HttpServletResponse) response).addHeader("Access-Control-Allow-Headers", "*"); 
		}
		
		chain.doFilter(request, response);
	}

}
