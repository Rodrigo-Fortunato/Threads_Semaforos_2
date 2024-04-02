package controller;


import java.util.concurrent.Semaphore;

public class CorridaCavaleiros extends Thread {

    private static final Semaphore semaphoreTorch = new Semaphore(1);
    private static final Semaphore semaphoreShineStone = new Semaphore(1);
    private static final Semaphore semaphorePortas = new Semaphore(1);
    private int[] portas = {0, 1, 0, 0};
    private static boolean torch;
    private static boolean shineStone;
    private final int id;
    private int velocidadeMax = 3;
    private int velocidadeMin = 2;

    public CorridaCavaleiros(int id) {
        this.id = id;
    }


    @Override
    public void run() {

        cavaleiro();
    }

    private void cavaleiro() {


        int passos;
        int distanciaPercorrida = 0;
        while (distanciaPercorrida < 2000) {
            passos = (int) ((Math.random() * velocidadeMax) + velocidadeMin);
            distanciaPercorrida += passos;
            System.out.println("O cavaleiro " + id + " andou: " + passos + " e ja percorreu: " + distanciaPercorrida);


            try {
                sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            if (!torch && distanciaPercorrida >= 500) {
                setTorch();
            }
            if (!shineStone && distanciaPercorrida >= 1500) {
                setShineStone();
            }

        }
        System.out.println("Cavaleiro " + id + " chegou as portas");
        portas();

    }

    private void setTorch() {


        try {
            semaphoreTorch.acquire();
            if (!torch) {
                velocidadeMin = 4;
                velocidadeMax = 0;
                torch = true;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphoreTorch.release();
        }


    }

    private void setShineStone() {

        try {
            semaphoreShineStone.acquire();
            if (!shineStone) {
                velocidadeMin = 6;
                shineStone = true;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphoreShineStone.release();
        }

    }

    private void portas() {
        int portaEscolhida = 0;
        boolean isClose = false;
        try {
            semaphorePortas.acquire();
            while (!isClose) {
                portaEscolhida = (int) (Math.random() * 4);
                if (portas[portaEscolhida] != -1) {

                isClose=true;
                }
            }

            if (portaEscolhida == 1){
                System.out.println("O cavaleiro "+id+" Escolheu a porta certa, e conseguiu sair");
            }else {
                System.out.println("O cavaleiro "+id+ " Foi de arrasta pra cima... F no chat");
            }

            portas[portaEscolhida] = -1;

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphorePortas.release();
        }

    }


}
