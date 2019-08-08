package com.ex.framework;

import com.ex.framework.database.DaoSupport;
import com.ex.framework.model.JdbcTestPo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FrameworkApplication.class)
@ComponentScan("com.ex")
public class DaoSupportTest {

    @Autowired
    private DaoSupport daoSupport;

    /**
     * 根据id获取一个 tet po对象
     *
     * @param id id值
     * @return po对象
     */
    private JdbcTestPo get(Integer id) {
        return daoSupport.queryForObject(JdbcTestPo.class, id);
    }

    /**
     * 根据泛型类插入数据
     */
    @Test
    public void insertByT() {

        JdbcTestPo testPo = new JdbcTestPo();
        testPo.setName("wf");
        testPo.setNum(18);
        testPo.setTime(12345678901L);
        testPo.setTotalPrice(22.2);
        testPo.setAveragePrice(11.11f);
        this.daoSupport.insert(testPo);

        // 断言和库中的一致
        testPo.setTestId(1);

        JdbcTestPo dbPo = this.get(1);
        Assert.assertEquals(testPo, dbPo);

    }

    /**
     * 获取最后添加的id
     */
    @Test
    public void getLastId() {

        this.insertByT();

        JdbcTestPo testPo = new JdbcTestPo();
        testPo.setTestId(1);
        testPo.setName("wf");
        testPo.setNum(18);
        testPo.setTime(12345678901L);
        testPo.setTotalPrice(22.2);
        testPo.setAveragePrice(11.11f);

        JdbcTestPo dbPo = this.get(this.daoSupport.getLastId("es_jdbc_test"));
        Assert.assertEquals(testPo, dbPo);
    }
}
