package EX01.view;

import EX01.controller.CorridaCavaleiros;

public class CavaleirosMain {
    public static void main(String[] args) {

        for (int cont =1;cont <=4;cont++){
            CorridaCavaleiros cavaleiros= new CorridaCavaleiros(cont);
            cavaleiros.start();
        }
    }
}
