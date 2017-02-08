package com.vpiaotong.elephant.core.util.majorkey;

import org.junit.Before;
import org.junit.Test;

import com.vpiaotong.elephant.core.utils.majorkey.impl.DistributedKeyMaker;

/**
 * 主键生成测试类
 * 
 * @author Acmen
 */
public class DistributedKeyMakerTest {

    DistributedKeyMaker keyMaker;

    @Before
    public void before() {
        keyMaker = DistributedKeyMaker.getInstance();
    }

    @Test
    public void generateLongKeyTest() {
        System.out.println(keyMaker.generateLongKey());
    }

    @Test
    public void generateShotKeyTest() {
        System.out.println(keyMaker.generateShotKey());
    }
}
