package dhbw.karlsruhe.it.solar.core.exceptions;

public class DeprecatedException extends RuntimeException {

    public static void printWarning(String message) {
        DeprecatedException exception = new DeprecatedException(message);
        exception.printStackTrace();
    }

    public DeprecatedException(String message) {
        super(message);
    }

}
