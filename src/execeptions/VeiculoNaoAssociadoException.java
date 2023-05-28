package execeptions;

public class VeiculoNaoAssociadoException extends Exception {
    public VeiculoNaoAssociadoException() {
    }

    public VeiculoNaoAssociadoException(String message) {
        super(message);
    }
}
