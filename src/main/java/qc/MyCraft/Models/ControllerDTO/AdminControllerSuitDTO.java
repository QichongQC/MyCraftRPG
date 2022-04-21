package qc.MyCraft.Models.ControllerDTO;

import lombok.Data;

@Data
public class AdminControllerSuitDTO {
    int status;
    Error error;
    String message;
    @Data
    public class Error{
        Object errorResult;
        String message;
    }
}
