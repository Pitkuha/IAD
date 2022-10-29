package app.service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class RSAGen {
    private KeyPair pair;

    public RSAGen() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        this.pair = generator.generateKeyPair();
    }

    public KeyPair getPair() {
        return pair;
    }
}
