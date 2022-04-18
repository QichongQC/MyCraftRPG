package Common.ValidateUtils;

import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用JSR303进行数据校验的工具类
 */
public class ValidateUtils {

    /**
     * 将FieldError列表 转换为Map ，以便前端使用
     * @param fieldErrors
     * @return
     */
    public static Map<String,String> FieldErrorList_To_Map(List<FieldError> fieldErrors){

        Map<String,String> field_errors=new HashMap<>();
        for (FieldError error:
                fieldErrors) {
            field_errors.put(error.getField(),error.getDefaultMessage());
        }

        return field_errors;
    }
}
