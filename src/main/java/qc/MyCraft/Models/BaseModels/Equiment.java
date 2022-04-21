package qc.MyCraft.Models.BaseModels;

import com.baomidou.mybatisplus.annotation.*;
import com.sun.xml.internal.ws.developer.Serialization;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@Data
@TableName("equiment")

public class Equiment  implements Serializable {

    @TableId(type = IdType.AUTO)
    String id;
    @NotEmpty(message = "武器种类不能为空！")
    String Etype;
    @NotEmpty(message = "武器名称不能为空！")
    String name;
    String picture;

    @NotEmpty(message = "武器描述不能为空！")
    @TableField("`describe`")
    String describe;
    @NotEmpty(message = "获取方式不能为空！")
    String achieving;
    /*空值过滤*/
    @TableField(insertStrategy = FieldStrategy.NOT_EMPTY)
    String richTxt;
    /*空值过滤*/
    @TableField(insertStrategy = FieldStrategy.NOT_EMPTY)
    String suitId;
}
