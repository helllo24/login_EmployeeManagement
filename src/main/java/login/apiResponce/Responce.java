package login.apiResponce;


public class Responce<T>{


    private Boolean success;
    private String message;
    private T data;

    public void setSuccess(Boolean success){
        this.success=success;
    }

    public Boolean getSuccess(){
        return success;
    }

    public void setMessage(String message){

        this.message=message;
    }

    public String getMessage(){
        return message;

    }

    public void setData(T data){

        this.data=data;
    }

    public T getData(){

        return data;
    }

}
