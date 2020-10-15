//<editor-fold desc="Description">
package com.xiaobu.base.util.codeGenerator;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xiaobu
 * @version 1.0
 * @date 2018/11/19 19:16
 * @descprition
 */
@SuppressWarnings({"rawtypes","unchecked","all"})
public class BeanConfig {
    //项目真实路径，即com.xx.xx之前的路径
    private static final String REAL_PATH = "G:\\Practise\\SpringBoot_JPA\\ssh5\\src\\main\\java\\";
    //生成的文件的共同子包路径，用于存放生成的文件
    private static final String PACKAGE_PATH = "com/xiaobu/";
    //共同子目录为
    private static final String COMMON_PATH = REAL_PATH + PACKAGE_PATH;

    /*各种文件存放路径
    默认路径根据实际情况修改*/
    private static final String CONTROLLER_PATH = "controller";
    private static final String DAO_PATH = "repository";
    private static final String DAO_IMPL_PATH = "repository/impl";
    private static final String SERVICE_PATH = "service";
    private static final String SERVICE_IMPL_PATH = "service/impl";

    //公共部分
    private static final String RT_1 = "\r\n";
    private static final String RT_2 = RT_1 + RT_1;
    private static final String BLANK_1 = " ";
    private static final String BLANK_4 = "    ";

    //注释部分
    private static final String ANNOTATION_AUTHOR_PARAMTER = "@author ";
    private static final String ANNOTATION_AUTHOR_NAME = "xiaobu";
    private static final String ANNOTATION_AUTHOR = ANNOTATION_AUTHOR_PARAMTER + ANNOTATION_AUTHOR_NAME;
    private static final String ANNOTATION_DATE = "@date ";
    private static final String ANNOTATION_DESCRIPTION = "@description 实现类必须加 @Repository 否则回报没有注入bean";
    private static final String ANNOTATION = "/**" + RT_1 + BLANK_1 + "*" + BLANK_1 + ANNOTATION_AUTHOR + RT_1 + BLANK_1 + "*" + BLANK_1 + ANNOTATION_DATE + getDate() + RT_1 + BLANK_1 +"*"+  BLANK_1 + ANNOTATION_DESCRIPTION + BLANK_1 + RT_1 + BLANK_1 +"*/" + RT_1;


    //包名

    private static final String CONTROLLER_URL = getPackageName(PACKAGE_PATH + CONTROLLER_PATH);
    private static final String DAO_URL = getPackageName(PACKAGE_PATH + DAO_PATH);
    private static final String DAO_IMPL_URL = getPackageName(PACKAGE_PATH + DAO_IMPL_PATH);
    private static final String SERVICE_URL = getPackageName(PACKAGE_PATH + SERVICE_PATH);
    private static final String SERVICE_IMPL_URL = getPackageName(PACKAGE_PATH + SERVICE_IMPL_PATH);

    //基本类名称
    private static final String BASE_PATH = "";
    private static final String BASE_CONTROLLER_NAME = BASE_PATH + "com.xiaobu.base.controller.BaseController";
    private static final String BASE_DAO_NAME = BASE_PATH + "com.xiaobu.base.repository.BaseRepository";
    private static final String BASE_DAO_IMPL_NAME = BASE_PATH + "com.xiaobu.base.repository.BaseRepositoryImpl";
    private static final String BASE_SERVICE_NAME = BASE_PATH + "com.xiaobu.base.service.BaseService";
    private static final String BASE_SERVICE_IMPL_NAME = BASE_PATH + "com.xiaobu.base.service.BaseServiceImpl";

    //bean的全名+类名
    private String beanName = "";
    //bean的类名
    private String beanSimpleName = "";
    //首字母小写的类名
    private String beanSmallName = "";

    private String serialName = "Integer";

    //初始化上面两个值
    private void initBeanName(Class c,String type) {
        if(StringUtils.isNotBlank(type)){
            serialName = type;
        }
        this.beanName = c.getName();
        this.beanSimpleName = c.getSimpleName();
        this.beanSmallName = beanSimpleName.substring(0, 1).toLowerCase() + beanSimpleName.substring(1);
    }

    void createBeanRepository(Class c,String type) throws Exception {
        initBeanName(c,type);
        String path = COMMON_PATH + DAO_PATH + "/" + beanSimpleName + "Repository.java";
        String content = "package " + DAO_URL + ";" + RT_2;
        content += "import " + beanName + ";" + RT_1;
        content += "import " + BASE_DAO_NAME + ";" + RT_2;
        content += ANNOTATION;
        content += "public interface " + beanSimpleName + "Repository extends BaseRepository <" + beanSimpleName + "," + serialName + "> {" + RT_2 + "}";
        createFile(path, content);
    }

    /**
     * @author xiaobu
     * @date 2018/11/20 11:53
     * @param c, type]
     * @descprition   创建自定义的repository
     * @version 1.0
     */
    void createBeanRepositoryCutom(Class c,String type) throws Exception {
        initBeanName(c,type);
        String path = COMMON_PATH + DAO_PATH + "/" + beanSimpleName + "RepositoryCustom.java";
        String content = "package " + DAO_URL + ";" + RT_2;
        content += "import org.springframework.stereotype.Repository;" + RT_1;
        content += ANNOTATION;
        content += "@Repository"+RT_1;
        content += "public interface " + beanSimpleName + "RepositoryCustom{" + RT_2 + "}";
        createFile(path, content);
    }

    /**
     * @author xiaobu
     * @date 2018/11/20 11:53
     * @param c, type]
     * @descprition   创建自定义的repository实现类
     * @version 1.0
     */
    void createBeanRepositoryCustomImpl(Class c,String type) throws Exception {
        initBeanName(c,type);
        String path = COMMON_PATH + DAO_IMPL_PATH + "/" + beanSimpleName + "RepositoryCustomImpl.java";
        String content = "package " + DAO_IMPL_URL + ";" + RT_2;
        content += "import org.springframework.beans.factory.annotation.Autowired;" + RT_1;
        content += "import org.springframework.stereotype.Repository;" + RT_1;
        content += "import javax.persistence.EntityManager;" + RT_1;
        content += "import javax.persistence.PersistenceContext;" + RT_1;
        content += "import " + BASE_DAO_IMPL_NAME + ";" + RT_1;
        content += "import " + DAO_URL + "." + beanSimpleName + "RepositoryCustom;" + RT_2;
        content += ANNOTATION;
        content += "@Repository"+RT_1;
        content += "@SuppressWarnings({\"rawtypes\",\"unchecked\",\"all\"})"+RT_1;
        content += "public class " + beanSimpleName + "RepositoryCustomImpl implements  "+beanSimpleName+"RepositoryCustom{"+RT_1 ;
        content += "    @Autowired\n" +
                "       @PersistenceContext\n" +
                "       private EntityManager entityManager;\n" ;

        content += RT_2 + "}";
        createFile(path, content);
    }


    /**
     * @author xiaobu
     * @date 2018/11/20 11:53
     * @param c, type]
     * @descprition   创建自定义的service接口类
     * @version 1.0
     */
    void createBeanService(Class c,String type) throws Exception {
        initBeanName(c,type);
        String path = COMMON_PATH + SERVICE_PATH + "/" + beanSimpleName + "Service.java";
        String content = "package " + SERVICE_URL + ";" + RT_2;
        content += "import " + beanName + ";" + RT_1;
        content += "import " + BASE_SERVICE_NAME + ";" + RT_2;
        content += ANNOTATION;
        content += "public interface " + beanSimpleName + "Service extends BaseService <" + beanSimpleName + "," + serialName + "> {" + RT_2 + "}";
        createFile(path, content);
    }

    /**
     * @author xiaobu
     * @date 2018/11/20 11:53
     * @param c, type]
     * @descprition   创建自定义的service接口实现类
     * @version 1.0
     */
    void createBeanServiceImpl(Class c,String type) throws Exception {
        initBeanName(c,type);
        String path = COMMON_PATH + SERVICE_IMPL_PATH + "/" + beanSimpleName + "ServiceImpl.java";
        String content = "package " + SERVICE_IMPL_URL + ";" + RT_2;
        content += "import org.springframework.stereotype.Service;" + RT_1;
        content += "import " + beanName + ";" + RT_1;
        content += "import " + BASE_SERVICE_IMPL_NAME + ";" + RT_1;
        content += "import " + SERVICE_URL + "." + beanSimpleName + "Service;" + RT_2;
        content += ANNOTATION;
        content += "@Service" + RT_1;
        content += "public class " + beanSimpleName + "ServiceImpl extends BaseServiceImpl<" +
                beanSimpleName + "," + serialName + "> implements " + beanSimpleName + "Service{" + RT_2 + "}";
        createFile(path, content);
    }



    /**
     * 获取路径的最后面字符串<br>
     * 如：<br>
     * <code>str = "com.minisay.test.bean.User"</code><br>
     * <code> return "User";<code>
     *
     * @param str
     * @return
     */
    public String getLastChar(String str) {
        if ((str != null) && (str.length() > 0)) {
            int dot = str.lastIndexOf('.');
            if ((dot > -1) && (dot < (str.length() - 1))) {
                return str.substring(dot + 1);
            }
        }
        return str;
    }

    /**
     * 把第一个字母变为小写<br>
     * 如：<br>
     * <code>str = "UserDao";</code><br>
     * <code>return "userDao";</code>
     *
     * @param str
     * @return
     */
    public String getLowercaseChar(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * 显示信息
     *
     * @param info
     */
    public void showInfo(String info) {
        System.out.println("创建文件：" + info + "成功！");
    }

    /**
     * 获取系统时间
     *
     * @return
     */
    public static String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 根据路径获取包名
     */
    private static String getPackageName(String path) {
        return path.replace("/", ".");
    }

    private static void createFile(String path, String content) throws Exception {
        File f = new File(path);
        FileWriter fw = new FileWriter(f);
        fw.write(content);
        fw.flush();
        fw.close();
        System.out.println("创建文件：" + path + "成功！");
    }

}