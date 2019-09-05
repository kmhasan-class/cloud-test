package bd.edu.seu.studentservice.exception;

public class ResourceAlreadyExistsException extends Exception {
    public ResourceAlreadyExistsException(String item) {
        super(item + " already exists");
    }
}
