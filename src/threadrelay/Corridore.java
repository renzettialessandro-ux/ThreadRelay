/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadrelay;
public class Corridore implements Runnable {
    private String nome;
    private int posizione;
    private static final Object testimone = new Object();
    private static int posizioneCorrente = 1;
    public Corridore(String nome, int posizione) {
        this.nome = nome;
        this.posizione = posizione;
    }
    @Override
    public void run() {
        try {
            synchronized (testimone) {
                while (posizione != posizioneCorrente) {
                    testimone.wait();
                }
            }
            System.out.println(nome + " inizia a correre nella posizione " + posizione);
            Thread.sleep((long) (Math.random() * 3000 + 1000));
            System.out.println(nome + " finisce la sua corsa");
            synchronized (testimone) {
                posizioneCorrente++;
                testimone.notifyAll();
            }
        } catch (InterruptedException e) {
            System.out.println(nome + " è stato interrotto");
        }
    }
}