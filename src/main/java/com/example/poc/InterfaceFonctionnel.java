package com.example.poc;

public interface InterfaceFonctionnel {
    public void show();
    public default int addition(int a, int b) {
        return a + b;
    }
    public static int soustraction(int a, int b) {
        return a - b;
    }
}
