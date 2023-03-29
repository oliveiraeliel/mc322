package factories;

import entidades.Seguradora;

public class SeguradoraFactory {
    public static Seguradora generateSeguradora() {
        return new Seguradora("Eliel Seguros", "12345679", "eliel123@dac.unicamp.br", "Rua Saturnino");
    }
}
