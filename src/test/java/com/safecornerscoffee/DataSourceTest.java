package com.safecornerscoffee;



import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/web/WEB-INF/applicationContext.xml")
public class DataSourceTest {
    @Autowired
    DataSource dataSource;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Test
    public void testConnection() {
        try {
            Connection conn = dataSource.getConnection();
            SqlSession session = sqlSessionFactory.openSession();

            System.out.println("conn: " + conn);
            System.out.println("session: " + session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
