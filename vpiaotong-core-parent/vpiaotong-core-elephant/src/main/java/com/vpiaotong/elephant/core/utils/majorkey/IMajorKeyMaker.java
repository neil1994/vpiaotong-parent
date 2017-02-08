package com.vpiaotong.elephant.core.utils.majorkey;

/**
 * 主键生成器
 * 
 * @author Acmen
 */
public interface IMajorKeyMaker {

    /**
     * 生成长度为32位的流水号
     * 
     * @return 长度为32位的流水号
     */
    public String generateLongKey();

    /**
     * 生成18位长度主键
     * 
     * @return 长度为18位的主键
     */
    public String generateShotKey();

}
