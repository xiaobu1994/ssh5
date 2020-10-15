JDBC连接Mysql5 com.mysql.jdbc.Driver:
# mysql
spring.datasource.url=jdbc:mysql://localhost:3306/springboot_jpa?characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=113506
spring.datasource.driver-class-name=com.mysql.jdbc.Driver

JDBC连接Mysql6 com.mysql.cj.jdbc.Driver， 需要指定时区serverTimezone:
# mysql
spring.datasource.url=jdbc:mysql://localhost:3306/springboot_jpa?characterEncoding=UTF-8&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=113506
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver


#swagger常见的注解说明
@Api： 描述Controller
@ApiIgnore： 忽略该Controller，指不对当前类做扫描
@ApiOperation： 描述Controller类中的method接口
@ApiParam： 单个参数描述，与@ApiImplicitParam不同的是，他是写在参数左侧的。如（@ApiParam(name = "username",value = "用户名") String username）
@ApiModel： 描述POJO对象
@ApiProperty： 描述POJO对象中的属性值
@ApiImplicitParam： 描述单个入参信息
@ApiImplicitParams： 描述多个入参信息
@ApiResponse： 描述单个出参信息
@ApiResponses： 描述多个出参信息
@ApiError： 接口错误所返回的信息



ssh5是springboot2.1+entityManage 搭建的JPA框架 entityManage

