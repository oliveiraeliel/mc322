package execeptions;

public class SeguroNaoEncontradoException extends Exception {
    public SeguroNaoEncontradoException() {
    }

    public SeguroNaoEncontradoException(String message) {
        super(message);
    }
}
