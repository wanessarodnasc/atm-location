package br.atm.location.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.atm.location.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
* 
* @author Wanessa Nascimento
*
*/
@Service
public class TokenService {
	
	@Value("${forum.jwt.expiration}")
	private String expiration;
	
	@Value("${forum.jwt.secret}")
	private String secret;

	public String generateToken(Authentication authentication) {
		User logged = (User) authentication.getPrincipal();
		Date now = new Date();
		
		return Jwts.builder()
				.setIssuer("Lets Movie API")
				.setSubject(logged.getId().toString())
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + Long.parseLong(expiration)))
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUser(String token) {
		return Long.parseLong(Jwts.parser()
				.setSigningKey(this.secret)
				.parseClaimsJws(token)
				.getBody()
				.getSubject());
	}
}
