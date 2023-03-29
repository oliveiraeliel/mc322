package factories;

import entidades.Veiculo;

public class VeiculoFactory {
    public static Veiculo generateVeiculo(){
        return new Veiculo("ABC-1234", "Ford", "K");
    }
}
