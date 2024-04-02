package EX02.controller;

import java.util.concurrent.Semaphore;

public class Aeroporto extends Thread {

    private static final Semaphore semaphorePistaNorte = new Semaphore(1);
    private static final Semaphore semaphorePistaSul = new Semaphore(1);
    private final int id;
    private int pista;
    public Aeroporto(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        aviao();
    }

    public void aviao() {
        try {
            pista = (int) (Math.random() * 2);
            System.out.println(pista);
            if (pista == 0) {
                semaphorePistaNorte.acquire();
            } else {
                semaphorePistaSul.acquire();
            }

            decolagem();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            if (pista == 0) {
                semaphorePistaNorte.release();
            } else {
                semaphorePistaSul.release();
            }

        }

    }

    public void decolagem() throws InterruptedException {
        System.out.println("Avião "+ id+" Realizando fase de manobra ");
        sleep((long) (Math.random() * 401) + 300);
        System.out.println("Avião "+ id+" Realizando fase de taxiar ");
        sleep((long) (Math.random() * 501) + 500);
        System.out.println("Avião "+ id+" Realizando fase de decolagem ");
        sleep((long) (Math.random() * 201) + 600);
        System.out.println("Avião "+ id+" Realizando fase de afastamento ");
        sleep((long) (Math.random() * 501) + 300);
    }


}
