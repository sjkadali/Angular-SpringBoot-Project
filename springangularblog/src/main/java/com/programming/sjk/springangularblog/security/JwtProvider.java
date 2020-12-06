package com.programming.sjk.springangularblog.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.annotation.PostConstruct;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.programming.sjk.springangularblog.exception.SpringBlogException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Service
public class JwtProvider {
	
	private KeyStore keyStore;
	
	@PostConstruct
	public void init() throws SpringBlogException {
		try {
		keyStore = KeyStore.getInstance("JKS");
		InputStream resourceStream = getClass().getResourceAsStream("/springblog.jks");
		keyStore.load(resourceStream, "spring".toCharArray());
		} catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            throw new SpringBlogException("Exception occured while loading keystore");
        }
	}
	
	public String generateToken(Authentication authentication) throws SpringBlogException {
		User principal = (User) authentication.getPrincipal();
		return Jwts.builder()
				.setSubject(principal.getUsername())
				.signWith(getPrivateKey())
				.compact();
	}
	
	private PrivateKey getPrivateKey() throws SpringBlogException {
		try {
			return (PrivateKey) keyStore.getKey("springblog","spring".toCharArray());
		} catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new SpringBlogException("Exception occured while loading keystore");
        }
	}
	
	public boolean validateToken(String jwt) throws SpringBlogException {
		Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
		return true;
	}
	
	private PublicKey getPublicKey() throws SpringBlogException {
		try {
            return keyStore.getCertificate("springblog").getPublicKey();
        } catch (KeyStoreException e) {
            throw new SpringBlogException("Exception occured while retrieving public key from keystore");
        }		
	}

	public String getUsernameFromJWT(String token) throws SpringBlogException {
		Claims claims = Jwts.parser()
				.setSigningKey(getPublicKey())
				.parseClaimsJws(token)
				.getBody();
		
		return claims.getSubject();
	}
}
