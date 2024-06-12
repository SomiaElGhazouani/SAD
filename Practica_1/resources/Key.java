package Practica_1.resources;

public class Key {

    // ASCII
    public static final int in_DRETA = 67;      // ^[[C
    public static final int in_ESQUERRA = 68;   // ^[[D
    public static final int in_INICI = 72;      // ^[[1~
    public static final int in_FI = 70;         // ^[[4~
    public static final int in_SUPR = 51;       // ^[[3~
    public static final int in_INSERT = 50;     // ^[[2~

    public static final int ENTER = 13;
    public static final int ESC = 27;       // ^[ o ^[[
    public static final int BPSK = 127;
    public static final int GUIO = 126;     // "~"
    public static final int CORXET = 91;    // "["

    // Conversions de les sequencies d'escape
    public final static int DRETA = -2;
    public final static int ESQUERRA = -3;
    public final static int INICI = -4;
    public final static int FI = -5;
    public final static int INSERT = -6;
    public final static int SUPR = -9;

}
