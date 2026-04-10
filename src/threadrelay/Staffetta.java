/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadrelay;

/**
 *
 * @author renzetti.alessandro
 */
public class Staffetta {
    private Corridore[] corridori;
    private Thread[] threads;
    private final int nCorridori = 4;

    public Staffetta() {
        corridori = new Corridore[nCorridori];
        threads = new Thread[nCorridori];
        for (int i = 0; i < nCorridori; i++) {
            corridori[i] = new Corridore("Corridore " + (i + 1), i + 1);
            threads[i] = new Thread(corridori[i]);
        }
    }

    public void avviaStaffetta() {
        System.out.println("La staffetta inizia!");
        
        /**
         * di mettere la variabile long e System.currentTimeMillis() mi è stato consigliato dall'ai
         * System.currentTimeMillis() restituisce il numero di millisecondi
         * in questo caso da long inizio a long fine
         */
        long inizio = System.currentTimeMillis();
        
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Staffetta interrotta");
            }
        }
         //di mettere la variabile long e System.currentTimeMillis() mi è stato consigliato dall'ai
        long fine = System.currentTimeMillis();
        System.out.println("La staffetta è finita! Tempo totale: " + (fine - inizio) + " ms");
    }
}
