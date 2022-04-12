import Common.Search.BaseSerach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import qc.MyCraft.Models.BaseModels.EType;
import qc.MyCraft.Models.BaseModels.Equiment;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Test {

    @Autowired
    qc.MyCraft.dao.Equiment equimentDao;
    @Autowired
    qc.MyCraft.dao.EType eTypeDao;
    @org.junit.Test
    public void test(){
        List<EType> allEtype = eTypeDao.getAllEtype();

        System.out.println(allEtype);
    }

    @org.junit.Test
    public void testTypeADD(){
        qc.MyCraft.Models.BaseModels.EType eType=new EType();
        eType.setTypeName("法杖");
        eType.setIntroduce("远程武器");
        int i = eTypeDao.addEtype(eType);

        System.out.println(i);
    }


    @org.junit.Test
    public void testTypeEdit(){
        qc.MyCraft.Models.BaseModels.EType eType=new EType();
        eType.setTypeName("法杖2");
        eType.setIntroduce("远程武器2");
        eType.setId(6);
        int i = eTypeDao.editEType(eType);

        System.out.println(i);
    }

    @org.junit.Test
    public void testTypeDelete(){

        int i = eTypeDao.rmEType(6);

        System.out.println(i);
    }

    @org.junit.Test
    public void testADD_E(){
        qc.MyCraft.Models.BaseModels.Equiment equiment=new Equiment();
        equiment.setAchieving("gg");
        equiment.setDescribe("gg");
        equiment.setEtype(2);
        equiment.setPicture("gg");
        equiment.setId(6);
        equimentDao.addEquiment(equiment);

    }

    @org.junit.Test
    public void testEdit_E(){
        qc.MyCraft.Models.BaseModels.Equiment equiment=new Equiment();
        equiment.setAchieving("gg2");
        equiment.setDescribe("gg2");
        equiment.setEtype(2);
        equiment.setPicture("gg2gggg");
        equiment.setId(2);
        equiment.setId(6);
        equimentDao.editEquiment(equiment);

    }

    @org.junit.Test
    public void testRm_E(){
        equimentDao.rmEquiment(3);
        System.out.println(equimentDao.getEquimentById(1));
    }
    @org.junit.Test
    public void testSearch_E(){
        System.out.println(equimentDao.getEquimentListBySearch(new BaseSerach()));
    }
}
