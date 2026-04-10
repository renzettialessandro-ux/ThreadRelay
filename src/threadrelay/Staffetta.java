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
    private GUIStaffetta gui;

    public Staffetta(GUIStaffetta gui) {
        this.gui = gui;
        corridori = new Corridore[nCorridori];
        threads = new Thread[nCorridori];
        for (int i = 0; i < nCorridori; i++) {
            corridori[i] = new Corridore("Runner " + (i + 1), i + 1, gui);
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
    }
    //di mettere la variabile long e System.currentTimeMillis() mi è stato consigliato dall'ai
    public void sospendi() {
        Corridore.suspendStaffetta();
    }

    public void riprendi() {
        Corridore.resumeStaffetta();
    }

    public void ferma() {
        for (Thread t : threads) {
            t.interrupt();
        }
        Corridore.resumeStaffetta();
    }
