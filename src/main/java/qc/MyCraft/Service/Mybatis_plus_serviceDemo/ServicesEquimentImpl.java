package qc.MyCraft.Service.Mybatis_plus_serviceDemo;

import org.springframework.stereotype.Service;
import qc.MyCraft.Models.BaseModels.Equiment;
import qc.MyCraft.dao.TestMybatis_EquimentMapper;

@Service
public class ServicesEquimentImpl
        extends com.baomidou.mybatisplus.extension.service.impl.ServiceImpl<TestMybatis_EquimentMapper, Equiment>
        implements IServicesEquiment {

}
