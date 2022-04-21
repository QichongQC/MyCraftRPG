import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import qc.MyCraft.Models.BaseModels.Equiment;
import qc.MyCraft.Models.BaseModels.Suit;
import qc.MyCraft.Service.Mybatis_plus_serviceDemo.IServicesEquiment;
import qc.MyCraft.dao.SuitMapper;

import java.sql.SQLOutput;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MybatisPlus_Demo {


    @Autowired
    qc.MyCraft.dao.TestMybatis_EquimentMapper equimentMapper;

    @Test
    public void testDemo(){
        System.out.println(equimentMapper);
        Equiment equiment = equimentMapper.selectById(1);
        System.out.println(equiment);
    }


    @Autowired
    IServicesEquiment qcservicesDemo;
    @Test
    public void TestServiceDemo(){
        Equiment byId = qcservicesDemo.getById(38);
    }

@Autowired
SuitMapper suitMapper;

    @Test
    public void TestMybatisPlus_Paging(){
        Page<qc.MyCraft.Models.BaseModels.Suit> iPage=new Page<>();
        QueryWrapper<Suit> suitQueryWrapper = new QueryWrapper<>();
        Page<Suit> suitPage = suitMapper.selectPage(iPage, suitQueryWrapper);
        System.out.println("getPages:"+suitPage.getPages());
        System.out.println("getTotal:"+suitPage.getTotal());

    }

    @Test
    public void TestEquimentAdd(){

    }
}
