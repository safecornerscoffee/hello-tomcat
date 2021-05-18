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
<servlet-mapping>
    <servlet-name>dispatcher</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
```
dispatcher-servlet.xml
```xml
<context:component-scan base-package="com.safecornerscoffee.controller">
    <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
</context:component-scan>

<mvc:annotation-driven/>
```
- [context:component-scan](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-controller)
- [mvc:annotation-driven](<mvc:annotation-driven/>)



