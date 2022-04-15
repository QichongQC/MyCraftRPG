package qc.MyCraft.Models.BaseModels;

import com.sun.xml.internal.ws.developer.Serialization;
import lombok.Data;

@Data
@Serialization
public class EType {
    String id;
    String typeName;
    String introduce;
    String TYPE;
}
