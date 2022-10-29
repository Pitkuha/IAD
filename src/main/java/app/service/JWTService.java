package app.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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
}
