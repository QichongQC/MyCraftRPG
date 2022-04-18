package qc.MyCraft.Service;

import org.springframework.stereotype.Service;
import qc.MyCraft.Models.FunctionModels.ModulesModels;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminHomeServices {

    public HashMap<String,String> getAdminModules(){
        return ModulesModels.module_urls;
    }
}
