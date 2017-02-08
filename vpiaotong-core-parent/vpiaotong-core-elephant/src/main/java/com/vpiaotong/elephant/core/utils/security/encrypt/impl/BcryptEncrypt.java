package com.vpiaotong.elephant.core.utils.security.encrypt.impl;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.vpiaotong.elephant.core.utils.file.PropertiesConfigurationUtil;
import com.vpiaotong.elephant.core.utils.security.encrypt.IPasswordEncrypt;

/**
 * Bcrypt加密工具类
 * 
 * @author Acmen
 */
public class BcryptEncrypt implements IPasswordEncrypt {

    @Override
    public String encryp(String password) {
        PropertiesConfiguration config = PropertiesConfigurationUtil.initConfig("systemConfig.properties");
        int parallelization = config.getInt("encrypt.parallelization");
        return BCrypt.hashpw(password, BCrypt.gensalt(parallelization));
    }

    @Override
    public boolean check(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
}
