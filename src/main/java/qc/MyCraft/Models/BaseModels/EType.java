package qc.MyCraft.Models.BaseModels;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sun.xml.internal.ws.developer.Serialization;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("etype")

public class EType implements Serializable {
    String id;
    String typeName;
    String introduce;
    String TYPE;
}
