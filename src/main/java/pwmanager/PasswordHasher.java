package pwmanager;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class PasswordHasher {
    public String hashPassword(String str) {
        return Hashing.sha256().hashString(str, StandardCharsets.UTF_8).toString();
    }

    public boolean checkHash(String str, String hash) {
        if (hashPassword(str).equals(hash))
            return true;

        return false;
    }
}
