package app.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;

@Service
public class JWTService {

    private final RSAGen rsaGen = new RSAGen();

    public JWTService() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {

    }

    public String createToken(long textId, String sessionId, String ip){
        PrivateKey privateKey = rsaGen.getPair().getPrivate();
        PublicKey publicKey = rsaGen.getPair().getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
        RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
        Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
        String jwt = JWT.create()
                .withClaim("textId", textId)
                .withClaim("sessionId", sessionId)
                .withClaim("ip", ip)
                .sign(algorithm);
        return jwt;
    }

    public DecodeJWT decodeJWT(String token){
        PrivateKey privateKey = rsaGen.getPair().getPrivate();
        PublicKey publicKey = rsaGen.getPair().getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
        RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;

        DecodedJWT jwt = JWT.require(Algorithm.RSA256(rsaPublicKey, rsaPrivateKey))
                .build()
                .verify(token);

        long textId = jwt.getClaim("textId").asLong();
        String sessionId = jwt.getClaim("sessionId").asString();
        String ip = jwt.getClaim("ip").asString();

        return new DecodeJWT(textId, sessionId, ip);
    }
}
