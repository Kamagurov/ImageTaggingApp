package codeinside.imagetaggingapp.exception;

public class NotValidIdException extends RuntimeException {

    public NotValidIdException(Long id) {
        super(String.format("Чувак такого id - %s нет", id));
    }
}
