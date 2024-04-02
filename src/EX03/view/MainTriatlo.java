package EX03.view;

import EX03.controller.Triatlo;

public class MainTriatlo {
    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("GrupoThread");
        for (int cont = 0; cont < 25; cont++) {
            Triatlo triatlo = new Triatlo();
            triatlo.start();


        }

    }
}
