package miprimerRest.jakarta.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.credential.BasicAuthenticationCredential;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static jakarta.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;

@ApplicationScoped
public class InMemoryIdentityStore implements IdentityStore {

    @Override
    public int priority() {
        return 10;
    }

    @Override
    public CredentialValidationResult validate(Credential credential) {



        if (credential instanceof BasicAuthenticationCredential) {
            BasicAuthenticationCredential user = (BasicAuthenticationCredential)credential;


            HashSet<String> roles = new HashSet<>();
            roles.add("admin");
            roles.add("user");

            user.getPassword().getValue();
            return switch (user.getCaller()) {
                case "admin" -> new CredentialValidationResult("admin", Set.of("admin"));// Collections.singleton("ADMIN"));
                case "paco" -> new CredentialValidationResult("paco", Collections.singleton("user"));
                case "user" -> new CredentialValidationResult("user", Collections.singleton("user"));
                default -> INVALID_RESULT;
            };

        }
        return INVALID_RESULT;
    }

}
