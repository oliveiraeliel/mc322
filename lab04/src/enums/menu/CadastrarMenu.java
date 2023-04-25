package enums.menu;

public enum CadastrarMenu {
    CADASTRAR_CLIENTE_PF(1),
    CADASTRAR_CLIENTE_PJ(2),
    CADASTRAR_VEICULO(3),
    CADASTRAR_SEGURADORA(4);

    private final int value;

    CadastrarMenu(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


    public static void handle(CadastrarMenu operacao){
        if (operacao == CADASTRAR_CLIENTE_PF){
            
        }
    }
}
