package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class CorridaCavaleiros extends Thread {

    private Semaphore semaphoreTorch;
    private Semaphore semaphoreShineStone;
    private boolean torch= false;
    private boolean shineStone;
    private int id=0;

    public CorridaCavaleiros(int id){
        this.id = id;
    }


    @Override
    public void run() {

        cavaleiro();
    }

    private void cavaleiro(){
        Random random = new Random();
        int velocidadeMax=4;
        int velocidadeMin=2;
        int passos=0;
        int distanciaPercorrida=0;
        while (distanciaPercorrida < 2000){
            passos =random.nextInt(velocidadeMin,velocidadeMax);
            distanciaPercorrida += passos;
            System.out.println("O cavaleiro andou: "+ passos + " e ja percorreu: "+distanciaPercorrida);


            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!torch && distanciaPercorrida >= 500){
                velocidadeMin = setTorch(velocidadeMin);
            }
            if (!shineStone && distanciaPercorrida >= 1500){
               velocidadeMin = setShineStone(velocidadeMin);
            }

        }
        System.out.println("Cavaleiro "+ id + " chegou as portas");

    }

    private int setTorch(int velocidadeMin){


            try {
                semaphoreTorch.acquire();
                if (!torch) {
                    velocidadeMin = 4;
                    torch = true;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphoreTorch.release();
            }

        return velocidadeMin;
    }

    private int setShineStone(int velocidadeMin) {

        try {
            semaphoreShineStone.acquire();
            if (!shineStone){
                velocidadeMin =6;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphoreShineStone.release();
        }
        return velocidadeMin;

    }


}
