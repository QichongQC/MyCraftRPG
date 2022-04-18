package qc.MyCraft.Models.BaseModels;

import com.sun.istack.internal.NotNull;
import com.sun.xml.internal.ws.developer.Serialization;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.lang.annotation.Annotation;

@Data
public class Equiment  {

    String id;
    @NotEmpty(message = "武器种类不能为空！")
    String Etype;
    @NotEmpty(message = "武器名称不能为空！")
    String name;
    String picture;
    @NotEmpty(message = "武器描述不能为空！")
    String describe;
    @NotEmpty(message = "获取方式不能为空！")
    String achieving;
    String richTxt;
}
