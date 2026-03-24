package login.exception;

public class BadCredentialException extends  RuntimeException {

    public BadCredentialException(String exe){
        super(exe);
    }

}