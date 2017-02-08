package com.vpiaotong.elephant.core.utils.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * 获取Spring容器中bean的工具类
 * 
 * @author Acmen
 */
@Component(value = "springBeansUtil")
public final class SpringBeansUtil implements BeanFactoryAware {

    private static BeanFactory beanFactory = null;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        SpringBeansUtil.beanFactory = beanFactory;
    }

    /**
     * 根据提供的bean名称获得相应的类
     * 
     * @param beanName
     *            bean名称
     * @return 要从容器取得的类
     */
    public static Object getBean(String beanName) {
        return beanFactory.getBean(beanName);
    }

    /**
     * 从Spring容器内获取相应的bean
     * 
     * @param requiredType
     *            要获取的类的类型
     * @return 要从容器取得的类
     */
    public static <T> T getBean(Class<T> requiredType) {
        return beanFactory.getBean(requiredType);
    }

    /**
     * 从Spring容器内获取相应的bean
     * 
     * @param beanName
     *            bean名称
     * @param requiredType
     *            要获取的类的类型
     * @return 要从容器取得的类
     */
    public static <T> T getBean(String beanName, Class<T> requiredType) {
        return beanFactory.getBean(beanName, requiredType);
    }
}
