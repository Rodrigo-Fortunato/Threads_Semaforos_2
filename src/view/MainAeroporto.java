package view;

import controller.Aeroporto;

public class MainAeroporto {
    public static void main(String[] args) {
        for (int cont=1;cont<=12;cont++){
            Aeroporto aeroporto = new Aeroporto(cont);
            aeroporto.start();
        }
    }
}
