import Common.MD5;
import Common.Search.BaseSerach;
import Common.Search.ETypeSearchModel;
import Common.Search.EquimentSearchModel;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import qc.MyCraft.Models.BaseModels.EType;
import qc.MyCraft.Models.BaseModels.Equiment;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Test {
    @Autowired
    qc.MyCraft.Service.Equiment equiment;

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
        eType.setId("6");
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
        equiment.setEtype("2");
        equiment.setPicture("gg");
        equiment.setId("6");
        equimentDao.addEquiment(equiment);

    }
    @org.junit.Test
    public void testEdit_E(){
        qc.MyCraft.Models.BaseModels.Equiment equiment=new Equiment();
        equiment.setAchieving("gg2");
        equiment.setDescribe("gg2");
        equiment.setEtype("2");
        equiment.setPicture("gg2gggg");
        equiment.setId("2");
        equiment.setId("6");
        equimentDao.editEquiment(equiment);

    }
    @org.junit.Test
    public void testRm_E(){
        equimentDao.rmEquiment(3);
        System.out.println(equimentDao.getEquimentById(1));
    }
    @org.junit.Test
    public void testSearch_E(){
        EquimentSearchModel instance = new EquimentSearchModel();
        instance.setEtype("2");
        instance.setAchieving("sb");
        instance.setName("武器");
        List<Equiment> equimentListBySearch = equimentDao.getEquimentListBySearch(instance);
        System.out.println(equimentListBySearch.stream().findAny().get().getDescribe());
    }

    @org.junit.Test
    public void TestService_Equiment(){
        System.out.println(equiment.getEquimentById(3));

    }
    @Autowired
    qc.MyCraft.Service.ETypeImpl eTypeService;
    @org.junit.Test
    public void TestService_Etype(){
        //eTypeService.getAllEtype();
        ETypeSearchModel esm=new ETypeSearchModel();
        esm.setIntroduce("intro");
        eTypeDao.getEtypeListBySearch(esm);
    }


    @org.junit.Test
    public void TestRecordCount_Equiment(){
        EquimentSearchModel equimentSearchModel = new EquimentSearchModel();
        equimentSearchModel.setEtype("1");
        System.out.println(equimentDao.getRecordCount(equimentSearchModel));

    }

    @org.junit.Test
    public void testMD5(){
        System.out.println(MD5.encryptToMD5("shabimaposhabiyandaishun12138"));
    }
}
