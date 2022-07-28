package io.rookware.reefTMSCore.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Component
public class JwtServices {

    private String secret;

    public JwtServices() {
        this.secret = System.getenv("JWT_SECRET_KEY");
    }

    public String createToken(String subject, Map<String, Object> claims) {
        SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        return Jwts.builder().setSubject(subject).addClaims(claims).signWith(keySpec).compact();
    }

    public Boolean validateToken(String token) {
        SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");

        String jwt = token.split(" ")[1].trim();

        try {
            Date exp = Jwts.parserBuilder().setSigningKey(keySpec).build()
                    .parseClaimsJws(jwt).getBody().getExpiration();
            if (exp != null && exp.after(Date.from(Instant.now()))) {
                System.out.println("Authentication expired");
                return false;
            }
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String getUserFromJwt(String token) {
        SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        String jwt = token.split(" ")[1].trim();
        try {
             return Jwts.parserBuilder().setSigningKey(keySpec).build()
                     .parseClaimsJws(jwt).getBody().getSubject();
        } catch (JwtException e) {
            return null;
        }
    }

}
