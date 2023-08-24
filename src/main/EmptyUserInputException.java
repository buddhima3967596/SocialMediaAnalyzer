public class EmptyUserInputException extends Exception{

    public EmptyUserInputException(String errorMessage, Throwable origin){
        super(errorMessage,origin);
    }

    public EmptyUserInputException(String errorMessage){
        super(errorMessage);
    }
}

