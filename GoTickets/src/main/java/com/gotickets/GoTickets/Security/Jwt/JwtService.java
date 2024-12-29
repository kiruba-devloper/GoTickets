package com.gotickets.GoTickets.Security.Jwt;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	private static final String SECRET = "1AA13180F4944E5E9852D4EF4D71FF1ECE6117F4EA3C0DEA070D276E5C065AC25FA4C8DF6A910BC7B1A937E7D65A724B8A4A93E466449149D3282BB8F76F344D";
	private static long VALIDITY = TimeUnit.MINUTES.toMillis(30);
	public String generateToken(UserDetails userDetails) {
		Map<String,String> claims = new HashMap<>();
		claims.put("iss", "GoTickets");
		return Jwts.builder()
		.claims(claims)
		.subject(userDetails.getUsername())
		.issuedAt(Date.from(Instant.now()))
		.expiration(Date.from(Instant.now().plusMillis(VALIDITY)))
		.signWith(generateKey())
		.compact();
	}
	
	private SecretKey generateKey() {
		byte[] decodedKey =Base64.getDecoder().decode(SECRET);
		return Keys.hmacShaKeyFor(decodedKey);
	}
	
	public  String extractUsername(String jwt) {
		Claims claims = getClaims(jwt);
		
		return claims.getSubject();
	}

	private Claims getClaims(String jwt) {
		Claims claims = Jwts.parser()
		.verifyWith(generateKey())
		.build()
		.parseSignedClaims(jwt)
		.getPayload();
		return claims;
	}
	
	public boolean isTokenValid(String jwt) {
		Claims claims = getClaims(jwt);
		return claims.getExpiration().after(Date.from(Instant.now()));
	}
}

