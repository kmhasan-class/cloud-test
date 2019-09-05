package bd.edu.seu.studentservice.exception;

public class ResourceDoesNotExistException extends Exception {
    public ResourceDoesNotExistException(String item) {
        super(item + " does not exist");
    }
}
