package login.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtils {

    private static  final String Secrect_Key ="mymymymymy3434343434googoggogggogkkakakaka";

    private static SecretKey secrectKey(){
        return Keys.hmacShaKeyFor(Secrect_Key.getBytes());

    }
    public String generateToken(String username, String roles){
        return Jwts.builder()
                .setSubject(username)
                .claim("roles",roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(secrectKey(),
                        SignatureAlgorithm.HS256).compact();


    }
    public Claims extractAllclaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secrectKey())
                .build()
                .parseClaimsJws(token)
                .getBody() ;   //->get payload

    }

    //we want username so call getsubject subject means is a username in payload
    public String extractUsername(String token){
        return extractclaims(token,Claims::getSubject);


    }
    public<T> T extractclaims(String token, Function<Claims,T> resolver){
        final Claims claims= extractAllclaims(token);
        return resolver.apply(claims);
    }

    public Date extractexpiration(String token){
        return extractclaims(token,Claims::getExpiration);
    }
    public boolean istokenExpired(String token){

        return extractexpiration(token).before(new Date());
    }
    public boolean validateToken(String token, String username){

        final  String extractedUsername = extractUsername(token);

        return (extractedUsername.equals(username) && !istokenExpired(token));
    }
}