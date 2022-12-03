package gr.uom.opensource.exceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException() {
        super("Course Not Found");
    }

}
