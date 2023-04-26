package enums.menu;

public enum MenuListar {
    LISTAR_CLIENTE_PF(1),
    LISTAR_CLIENTE_PJ(2),
    LISTAR_SINISTROS(3),
    LISTAR_SINISTROS_CLIENTE(4),
    LISTAR_VEICULO_CLIENTE(5),
    LISTAR_VEICULO_SEGURADORA(6);

    private final int value;

    MenuListar(int value) {
        this.value = value;
    }

    public static void menu()

    public int getValor(){
        return value;
    }
}
