package qc.MyCraft.Models.FunctionModels;

import Common.ThreadLocalUtils;

import java.util.HashMap;

/**
 * 此类用于保存Admin中的各个模块
 */
public class ModulesModels {

    public static HashMap<String,String> module_urls=new HashMap<>();
    /**
     * 初始化
     */
    static {
        module_urls.put("装备", "/Admin/Home");
    }
}
