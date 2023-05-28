package execeptions;

public class ClienteNaoAssociadoException extends Exception {
    public ClienteNaoAssociadoException() {
    }

    public ClienteNaoAssociadoException(String message) {
        super(message);
    }
}
