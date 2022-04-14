package qc.MyCraft.Models.BaseModels;

import com.sun.xml.internal.ws.developer.Serialization;
import lombok.Data;

@Data
@Serialization
public class EType {
    int id;
    String typeName;
    String introduce;
    int TYPE;
}
