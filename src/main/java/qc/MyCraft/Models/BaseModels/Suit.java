package qc.MyCraft.Models.BaseModels;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.*;

import java.io.Serializable;

@TableName("suit")
@Data

public class Suit implements Serializable {
    String id;
    @NotEmpty
    String name;
    String richTxt;
    @TableField("`describe`")
    @NotEmpty
    String describe;
    String picture;
}
