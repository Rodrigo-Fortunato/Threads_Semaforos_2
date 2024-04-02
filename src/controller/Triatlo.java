package controller;

import java.util.concurrent.Semaphore;

public class Triatlo extends Thread {

    private static final Semaphore semaphoreTiroAlvo = new Semaphore(5);
    private static final Semaphore semaphoreColocacao = new Semaphore(1);

    private static int id=0;
    private int idAtleta=0;
    private int pontosTotais;
    private int pontosCorrida;
    private int pontosTiroAlvo;
    private int pontosCiclismo;

    private static int colocacaoCorrida = 0;
    private static int colocacaoCiclismo = 0;
    private static ThreadGroup threadGroup = new ThreadGroup("GrupoTeste");


    public Triatlo() {
        /*this.threadGroup = threadGroup;*/
        //this.id = id;
    }

    @Override
    public void run() {

        monitor();



    }



    public void monitor() {

        try {
            Triatlo triatlo = new Triatlo();
            setId();
            corrida();
            triatlo.join();



        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    private synchronized void setId(){
        id++;
        idAtleta = id;
    }

    public void corrida() {
        try {
            int velocidade;
            int distanciaPercorrida = 0;
            while (distanciaPercorrida <= 3000) {
                velocidade = (int) (Math.random() * 6) + 20;
                distanciaPercorrida += velocidade;
                sleep(30);
                System.out.println("O atleta: " + id + " correu " + velocidade + " e ja percorreu ao todo " + distanciaPercorrida);

            }
            System.out.println("O atleta: " + id + " Terminou a corrida");
            semaphoreColocacao.acquire();
            pontosCorrida = (25 - colocacaoCorrida) * 10;
            colocacaoCorrida += 1;

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphoreColocacao.release();
        }

        tiroAlvo();

    }


    public void tiroAlvo() {

        try {
            semaphoreTiroAlvo.acquire();
            System.out.println("O atleta: " + id + " entrou no tiro ao alvo");
            for (int cont = 0; cont < 3; cont++) {
                pontosTiroAlvo += (int) (Math.random() * 11);
                long sleepTime = (long) (Math.random() * 2500 + 500);
                System.out.println("O atleta " + id + " esta atirando pela " + (cont + 1) + "° vez " + sleepTime);
                sleep(sleepTime);

            }
            System.out.println("O atleta: " + id + " saiu do tiro ao alvo");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphoreTiroAlvo.release();
        }
        ciclismo();

    }

    private void ciclismo() {
        int velocidade;
        int distanciaPercorrida = 0;
        while (distanciaPercorrida <= 5000) {
            velocidade = (int) (Math.random() * 11) + 30;
            distanciaPercorrida += velocidade;

            try {
                sleep(40);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("O atleta " + id + " correu " + velocidade + " e ja percorreu ao todo " + distanciaPercorrida);

        }
        try {
            semaphoreColocacao.acquire();
            pontosCiclismo = (25 - colocacaoCiclismo) * 10;
            colocacaoCiclismo += 1;
            System.out.println("O atleta " + id + " Terminou o ciclismo");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphoreColocacao.release();
        }

        ranking();
    }

    private synchronized void ranking() {

        pontosTotais = pontosCiclismo + pontosCorrida + pontosTiroAlvo;
        System.out.println("O atleta " + id + " fez ao todo: " + pontosTotais);
        System.out.println("Pontos Corrida: " + pontosCorrida +
                "\n  Pontos Tiro ao alvo: " + pontosTiroAlvo +
                "\n  Pontos Ciclismos: " + pontosCiclismo);




       /* System.out.println(threadGroup.getName());
        System.out.println(threadGroup.getParent());
        System.out.println(threadGroup.activeCount());
        threadGroup.list();*/



        /*int threadsAtivas = threadGroup.activeGroupCount();
        System.out.println(threadsAtivas);*/

        /*pontosTotais[id] = pontosCiclismo + pontosCorrida + pontosTiroAlvo;
        Quick_Sort quickSort = new Quick_Sort();
        quickSort.quickSort(pontosTotais,0,pontosTotais.length-1);

        for (int i : pontosTotais) {
            System.out.println("O atleta " + id + " Obteve um total de: " + i + " Pontos");
        }
*/
    }


}
