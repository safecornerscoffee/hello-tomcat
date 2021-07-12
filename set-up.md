# Hello Tomcat@9

## Tomcat@9

```shell
brew install tomcat@9
```

1. IntelliJ > Run > Edit Configurations... > Add New Configuration > Tomcat Server
2. Configure Tomcat Server.
3. Deployment > Application Context to `/`

## Spring MVC

### Project Structure using intellij

1. Click `Add Framework Support...` within Project Root context menu.
2. Click Spring > Spring MVC
3. Configure `Artifacts`, `Facets`, and `Libraries`.
4. Delete `lib` directory.

### Servlet Config

web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/applicationContext.xml</param-value>
    </context-param>
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
</web-app>
```

dispatcher-servlet.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="
           http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context
           http://www.springframework.org/schema/context/spring-context.xsd
           http://www.springframework.org/schema/mvc
           http://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <context:component-scan base-package="com.safecornerscoffee.medium.web">
        <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>

    <mvc:annotation-driven/>

</beans>
```

- [context:component-scan](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-controller)
- [mvc:annotation-driven](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-config>)

## JSP and JSTL

[MVC View - JSP and JSTL](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-view-jsp)

### Maven Dependencies

[Apache Tomcat 9.x is the current focus of development. It builds on Tomcat 8.0.x and 8.5.x and implements the Servlet 4.0, JSP 2.3, EL 3.0, WebSocket 1.1 and JASPIC 1.1](http://tomcat.apache.org/whichversion.html)

```xml
<!-- https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api -->
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
    <scope>provided</scope>
</dependency>
        <!-- https://mvnrepository.com/artifact/javax.servlet.jsp/javax.servlet.jsp-api -->
<dependency>
<groupId>javax.servlet.jsp</groupId>
<artifactId>javax.servlet.jsp-api</artifactId>
<version>2.3.3</version>
<scope>provided</scope>
</dependency>
        <!-- https://mvnrepository.com/artifact/javax.servlet.jsp.jstl/javax.servlet.jsp.jstl-api -->
<dependency>
<groupId>javax.servlet.jsp.jstl</groupId>
<artifactId>javax.servlet.jsp.jstl-api</artifactId>
<version>1.2.2</version>
</dependency>
```

add libs to project artifacts

### Servlet View Config

dispatcher-servlet.xml

```xml

<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
    <property name="prefix" value="/WEB-INF/jsp/"/>
    <property name="suffix" value=".jsp"/>
</bean>
```

## JSP and JSTL

[MVC View - JSP and JSTL](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-view-jsp)

### Maven Dependencies

[Apache Tomcat 9.x is the current focus of development. It builds on Tomcat 8.0.x and 8.5.x and implements the Servlet 4.0, JSP 2.3, EL 3.0, WebSocket 1.1 and JASPIC 1.1](http://tomcat.apache.org/whichversion.html)

```xml
<!-- https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api -->
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
    <scope>provided</scope>
</dependency>
        <!-- https://mvnrepository.com/artifact/javax.servlet.jsp/javax.servlet.jsp-api -->
<dependency>
<groupId>javax.servlet.jsp</groupId>
<artifactId>javax.servlet.jsp-api</artifactId>
<version>2.3.3</version>
<scope>provided</scope>
</dependency>
        <!-- https://mvnrepository.com/artifact/javax.servlet/jstl -->
<dependency>
<groupId>javax.servlet</groupId>
<artifactId>jstl</artifactId>
<version>1.2</version>
</dependency>
```

add libs to project artifacts

### Servlet View Config

dispatcher-servlet.xml

```xml

<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
    <property name="prefix" value="/WEB-INF/jsp/"/>
    <property name="suffix" value=".jsp"/>
</bean>
```

## Persistence layer

### Maven Dependencies

[Add JDBC, MyBatis, MyBatis-Spring and Database Driver](https://mybatis.org/spring/)

```xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>5.2.3.RELEASE</version>
</dependency>

        <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
<dependency>
<groupId>org.mybatis</groupId>
<artifactId>mybatis</artifactId>
<version>3.5.3</version>
</dependency>
        <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
<dependency>
<groupId>org.mybatis</groupId>
<artifactId>mybatis-spring</artifactId>
<version>2.0.3</version>
</dependency>
        <!-- https://mvnrepository.com/artifact/org.postgresql/postgresql -->
<dependency>
<groupId>org.postgresql</groupId>
<artifactId>postgresql</artifactId>
<version>42.2.20</version>
</dependency>

```

### Configure DataSource

applicationContext.xml

```xml

<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <property name="driverClassName" value="org.postgresql.ds.PGSimpleDataSource"/>
    <property name="url" value="jdbc:postgresql://localhost:5432/postgres"/>
    <property name="username" value="postgres"/>
    <property name="password" value="postgres"/>
</bean>
```

### Configure SQLSessionFactory and Component Scan

applicationContext.xml

```xml

<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource"/>
    <property name="configLocation" value="classpath:/mybatis-config.xml"/>
    <property name="mapperLocations" value="classpath:/mapper/*.xml"/>
</bean>

<mybatis-spring:scan base-package="com.safecornerscoffee.medium.mapper"/>
```

mybatis-config.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <typeAliases>

    </typeAliases>
</configuration>
```

### Set up Transaction Manager

applicationContext.xml

```
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <constructor-arg ref="dataSource" />
    </bean>
    <tx:annotation-driven transaction-manager="transactionManager"/>
```

## UserService

### Create a model

```java
public class User {
    private Long id;
    private String email;
    private String name;
    private String password;
}
```

tables.sql

```postgresql
CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    email    TEXT UNIQUE NOT NULL,
    name     TEXT        NOT NULL,
    password TEXT        NOT NULL
);
```

### UserDao Interface and Mapper

UserDao.java

```java
public interface UserDao {
    List<User> selectAllUsers();

    User selectUserById(Long id);

    User selectUserByEmail(String email);

    Long insertUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
```

UserDaoMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.safecornerscoffee.medium.mapper.UserMapper">
    <select id="selectAllUsers" resultType="com.safecornerscoffee.medium.domain.User">
        SELECT id, email, name, password FROM users
    </select>
    <select id="selectUserById"
            parameterType="Long"
            resultType="com.safecornerscoffee.medium.domain.User">
        SELECT id, email, name, password
        FROM users
        WHERE id = #{id}
    </select>

    <select id="selectUserByEmail"
            parameterType="string"
            resultType="com.safecornerscoffee.medium.domain.User">
        SELECT id, email, name, password
        FROM users
        WHERE email = #{email}
    </select>

    <select id="insertUser"
            parameterType="com.safecornerscoffee.medium.domain.User"
            resultType="Long"
    >
        INSERT INTO users(email, name, password)
        VALUES(#{email}, #{name}, #{password})
        RETURNING id
    </select>

    <update id="updateUser"
            parameterType="com.safecornerscoffee.medium.domain.User">
        UPDATE users
        SET
        email = #{email},
        name = #{name},
        password = #{password}
        WHERE id = #{id}
    </update>
    <delete id="deleteUser" parameterType="com.safecornerscoffee.medium.domain.User">
        DELETE FROM users WHERE id = #{id}
    </delete>
</mapper>
```

Use `<select>` Clause instead of `<insert>` for Returning auto-generated sequence.

### Bcrypt

https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/crypto/bcrypt/BCrypt.html

### JWT

https://github.com/jwtk/jjwt

[javax.servlet.http.HttpSession](https://tomcat.apache.org/tomcat-9.0-doc/servletapi/javax/servlet/http/HttpSession.html)

## Logger

```xml
<!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-api -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.25</version>
</dependency>

        <!-- https://mvnrepository.com/artifact/ch.qos.logback/logback-classic -->
<dependency>
<groupId>ch.qos.logback</groupId>
<artifactId>logback-classic</artifactId>
<version>1.2.3</version>
</dependency>
```

## References

[Introduction to Spring MVC HandlerInterceptor](https://www.baeldung.com/spring-mvc-handlerinterceptor)
