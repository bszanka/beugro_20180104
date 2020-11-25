package egyetem;

public class NincsTesztException extends Exception {
    @Override
    public String getMessage() {
        return "Nincs teszt.\n";
    }
}
