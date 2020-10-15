package com.xiaobu.base.util.codeGenerator;


import com.xiaobu.entity.Resource;

/**
 * Created by lic113506 on 2015/11/25.
 * 这是一个代码生成器，配合自己封装的框架使用，建好实体类后，在BeanConfig中简单配置一下路径，就可以生成实体类的controller,service,serviceimpl,dao,daoimpl；
 * 本代码生成器为了简化配置，目前已把本框架内的基类路径和名称写死，如果其他框架需要使用本代码生成器，请修改对应的基类路径
 */
@SuppressWarnings({"all"})
public class BeanGenerator {
    public static void main(String[] args) throws Exception{
        BeanGenerator beangenerator = new BeanGenerator();
        BeanConfig beanConfig = new BeanConfig();
        beangenerator.beanTool(beanConfig, Resource.class,"Integer");
    }

    private void beanTool(BeanConfig beanConfig, Class c,String type)throws Exception{
        beanConfig.createBeanRepository(c,type);
        beanConfig.createBeanRepositoryCutom(c, type);
        beanConfig.createBeanRepositoryCustomImpl(c,type);
        beanConfig.createBeanService(c,type);
        beanConfig.createBeanServiceImpl(c,type);
    }
}
