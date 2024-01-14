package dev.digitalcodex.web.application.security;

import dev.digitalcodex.web.application.ApplicationConstants;
import dev.digitalcodex.web.application.exception.ProcessingException;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

@Service(ApplicationConstants.JWT_PROVIDER_BEAN_NAME)
public class JWTProvider {
    private KeyStore keyStore;

    @PostConstruct
    public void init() {
        try {
            this.keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = this.getClass().getResourceAsStream("/ks.jks");
            this.keyStore.load(resourceAsStream, "secret".toCharArray());
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            throw new ProcessingException(ProcessingException.KEYSTORE_EXCEPTION_MSG_FORMAT.formatted("loading"));
        }
    }

    public String generateJWT(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .signWith(this.getPrivateKey())
                .compact();
    }

    public PublicKey getPublicKey() {
        try {
            return this.keyStore.getCertificate("ks").getPublicKey();
        } catch (KeyStoreException e) {
            throw new ProcessingException(ProcessingException.KEYSTORE_EXCEPTION_MSG_FORMAT.formatted("retrieving public key from"));
        }
    }

    public PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) this.keyStore.getKey("ks", "secret".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new ProcessingException(ProcessingException.KEYSTORE_EXCEPTION_MSG_FORMAT.formatted("retrieving public key from"));
        }
    }

    public String validate(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(this.getPublicKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }
}
