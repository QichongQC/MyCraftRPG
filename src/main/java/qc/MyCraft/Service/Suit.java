package qc.MyCraft.Service;

import com.baomidou.mybatisplus.extension.service.IService;

public interface Suit  extends IService<qc.MyCraft.Models.BaseModels.Suit> {

    void release_deleted_resource(qc.MyCraft.Models.BaseModels.Suit byId);
}
