package by.overone.it.encoder;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordEncoder {

    /**
     * Метод шифрования пароля
     * @param password пароль
     * @return зашифрованный пароль
     */
    public static String encodePassword(String password) {
        return DigestUtils.md5Hex(password);
    }
}
