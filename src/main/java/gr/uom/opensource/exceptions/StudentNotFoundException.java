package gr.uom.opensource.exceptions;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException() {
        super("Student Not Found");
    }
}
