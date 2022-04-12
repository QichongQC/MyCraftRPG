package qc.MyCraft.Models.BaseModels;

import com.sun.xml.internal.ws.developer.Serialization;
import lombok.Data;

import java.lang.annotation.Annotation;

@Data
public class Equiment  {

    int id;
    int Etype;
    String picture;
    String describe;
    String achieving;

}
