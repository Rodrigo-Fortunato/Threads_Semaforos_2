package view;

import controller.CorridaCavaleiros;

public class Main {
    public static void main(String[] args) {

        for (int cont =0;cont <4;cont++){
            CorridaCavaleiros cavaleiros= new CorridaCavaleiros(cont);
            cavaleiros.run();
        }
    }
}
