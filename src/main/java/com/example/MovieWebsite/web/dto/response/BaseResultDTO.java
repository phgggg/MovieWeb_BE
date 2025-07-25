package com.example.MovieWebsite.web.dto.response;
import com.example.MovieWebsite.utils.Constants;
import lombok.Getter;
import lombok.Setter;


public class BaseResultDTO {
    private String errorCode;
    private String description;
//    private List<?> listData = new ArrayList();
//    private Object data;
//    private Long totalRow = 0L;
//    private Integer totalPage = 0;

    public BaseResultDTO() {
        errorCode = Constants.ERROR;
        description = Constants.UNKNOWN;
    }

    public BaseResultDTO(String errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    public void setSuccess(){
        this.errorCode = Constants.SUCCESS;
//        this.statusCode = 200;
        this.description = "ok";
    }

    public void setFail(String msg) {
        this.errorCode = Constants.ERROR;
        this.description = msg;
    }

    public void setFail(String errorCode,String msg) {
        this.errorCode = errorCode;
        this.description = msg;
    }

    public void setItemNotfound(String msg){
        this.errorCode = Constants.ERR_CODE_ITEM_NOT_FOUND;
        this.description = msg;
    }

    public void setItemNotfound(){
        this.setItemNotfound("item not found");
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
