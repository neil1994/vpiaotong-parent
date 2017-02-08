package com.vpiaotong.elephant.core.util.security.encrypt;

import org.junit.Before;
import org.junit.Test;

import com.vpiaotong.elephant.core.utils.security.encrypt.impl.BcryptEncrypt;
import com.vpiaotong.elephant.core.utils.security.encrypt.impl.ScryptEncrypt;

/**
 * 加密测试类
 * 
 * @author Acmen
 */
public class PasswordEncryptTest {

    String password = "admin";

    BcryptEncrypt bcryptEncrypt;

    ScryptEncrypt scryptEncrypt;

    @Before
    public void before() {
        bcryptEncrypt = new BcryptEncrypt();
        scryptEncrypt = new ScryptEncrypt();
    }

    @Test
    public void bcryptTest() {
        String hashed = bcryptEncrypt.encryp(password);
        System.out.println("bcrypt加密:" + hashed);
        System.out.println(bcryptEncrypt.check(password, hashed));
    }

    @Test
    public void scryptTest() {
        String hashed = scryptEncrypt.encryp(password);
        System.out.println("scrypt加密:" + hashed);
        System.out.println(scryptEncrypt.check(password, hashed));
    }
}
