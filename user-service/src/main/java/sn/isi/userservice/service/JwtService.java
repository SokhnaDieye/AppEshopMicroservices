package sn.isi.userservice.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.springframework.stereotype.Service;
import sn.isi.userservice.model.Users;
import java.util.Date;

@Service
public class JwtService {

    private Key getSignInKey() {
        String SECRET_KEY = "2336ba6521fc5d2beb152d31c3aae96b1312367cb11a3ce34380c81a04ff762da056a8d3ec4f91554c2138407e27a27b916efebec2b4f84126fb0275b0e48d3ebc565fb4ed88e889c05a8bba0f40b43d91d2ce3685b621e604f9a2198b5f66480d30b7c1da757c1dd838a58caa1cc60358417f9ea267085f4342b6042556d8977cb553463a79bcdf41ef82372c10c22e22cd660df8b1cfc9e10fe993905c3eaf4edecf1f7a0836a5d56d4c7dcae52fb7a0eaaa3cca7aa098d32a300611dece1635dd5473c50ae0bf908bcca08ac02f08ee76140d6dfeae1fb0b039267770ce9aab737d506239e8220e62259cb3267d0e76038f6098736570647255259a09d381";
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(Users user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24h
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String generateRefreshToken(Users user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token, Users user) {
        return extractUsername(token).equals(user.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

}

