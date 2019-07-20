package br.atm.location.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.atm.location.model.User;
import br.atm.location.repository.UserRepository;

/**
* 
* @author Wanessa Nascimento
*
*/
public class TokenAutenticationFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;
	private UserRepository repository;

	public TokenAutenticationFilter(TokenService tokenService, UserRepository repository) {
		this.tokenService = tokenService;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = getToken(request);
		boolean valid = tokenService.isTokenValid(token);
		if (valid) {
			clientAuthentication(token);
		}
		
		filterChain.doFilter(request, response);
	}

	private void clientAuthentication(String token) {
		Long isUser = tokenService.getIdUser(token);
		Optional<User> userReturned = repository.findById(isUser);
		if(userReturned.isPresent()) {
			User user = userReturned.get();
			UsernamePasswordAuthenticationToken authentication = 
					new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPasswordGenerated(), user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
	}

	private String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty()) {
			return null;
		}
		return token.substring(7, token.length());
	}
}
