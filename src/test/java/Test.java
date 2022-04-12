import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import qc.MyCraft.Models.BaseModels.EType;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Test {

    @Autowired
    qc.MyCraft.dao.EType eTypeDao;
    @org.junit.Test
    public void test(){
        List<EType> allEtype = eTypeDao.getAllEtype();

        System.out.println(allEtype);
    }
}
