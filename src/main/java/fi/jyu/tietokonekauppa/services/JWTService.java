package fi.jyu.tietokonekauppa.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JWTService {
    private static final String SECRET = "big-big-secret";
    private static final String ISSUER = "tietokonekauppa";

    public String issueToken(String login){
        String token = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            token = JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(login)
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000))
                    .sign(algorithm);
        } catch (UnsupportedEncodingException exception){
            //UTF-8 encoding not supported
            exception.printStackTrace();
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
            exception.printStackTrace();
        }
        return token;
    }

    public String verifyToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getSubject();

        } catch (UnsupportedEncodingException exception){
            //UTF-8 encoding not supported
            exception.printStackTrace();
        } catch (JWTVerificationException exception){
            //Invalid signature/claims
            exception.printStackTrace();
        }
        return null;
    }

    public void disableToken(String token){
        // TODO implement business logic
    }
}
