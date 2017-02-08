package com.vpiaotong.elephant.core.utils.security.encrypt.impl;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.stereotype.Component;

import com.lambdaworks.crypto.SCryptUtil;
import com.vpiaotong.elephant.core.utils.file.PropertiesConfigurationUtil;
import com.vpiaotong.elephant.core.utils.security.encrypt.IPasswordEncrypt;

/**
 * Scrypt加密工具类
 * 
 * @author Acmen
 */
@Component("passwordEncrypt")
public class ScryptEncrypt implements IPasswordEncrypt {

    @Override
    public String encryp(String password) {
        PropertiesConfiguration config = PropertiesConfigurationUtil.initConfig("systemConfig.properties");
        int cpuCost = config.getInt("encrypt.cpuCost");
        int memoryCost = config.getInt("encrypt.memoryCost");
        int parallelization = config.getInt("encrypt.parallelization");
        return SCryptUtil.scrypt(password, cpuCost, memoryCost, parallelization);
    }

    @Override
    public boolean check(String password, String hashed) {
        return SCryptUtil.check(password, hashed);
    }
}
