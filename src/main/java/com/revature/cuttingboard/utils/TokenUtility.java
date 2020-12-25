package com.revature.cuttingboard.utils;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtility implements Claims {
	
	private String SECRET_KEY = "secret";

    public String createJWT(String id, String issuer, String subject, long ttlMillis) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        } 
        
        return builder.compact();
        
    }

    public Claims decodeJWT(String jwt) {

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object get(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object put(String key, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<String> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Object> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Entry<String, Object>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIssuer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Claims setIssuer(String iss) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSubject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Claims setSubject(String sub) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAudience() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Claims setAudience(String aud) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getExpiration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Claims setExpiration(Date exp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getNotBefore() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Claims setNotBefore(Date nbf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getIssuedAt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Claims setIssuedAt(Date iat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Claims setId(String jti) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T get(String claimName, Class<T> requiredType) {
		// TODO Auto-generated method stub
		return null;
	}
}
