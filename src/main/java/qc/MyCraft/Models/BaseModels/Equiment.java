package qc.MyCraft.Models.BaseModels;

import com.sun.xml.internal.ws.developer.Serialization;
import lombok.Data;

import java.lang.annotation.Annotation;

@Data
public class Equiment  {

    Integer id;
    Integer Etype;
    String name;
    String picture;
    String describe;
    String achieving;

}
