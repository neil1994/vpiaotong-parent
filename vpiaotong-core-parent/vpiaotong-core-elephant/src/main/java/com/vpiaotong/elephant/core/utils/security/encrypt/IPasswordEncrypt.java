package com.vpiaotong.elephant.core.utils.security.encrypt;

/**
 * 密码加密接口
 * 
 * @author Acmen
 */
public interface IPasswordEncrypt {

    /**
     * 加密密码
     * 
     * @param password
     *            用户当前输入的密码
     * @return 加密后的密码字符串
     */
    String encryp(String password);

    /**
     * 校验密码
     * 
     * @param password
     *            用户当前输入的密码
     * @param hashed
     *            数据库保存的加密后的密码
     * @return true||false
     */
    boolean check(String password, String hashed);
}
