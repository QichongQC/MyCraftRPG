package qc.MyCraft.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import qc.MyCraft.Models.BaseModels.*;

import java.util.List;


@Repository
public interface SuitMapper extends BaseMapper<Suit> {

    @Select("select * from suit")
    List<Suit> findSuitByPage(IPage<Suit> iPage);
}
