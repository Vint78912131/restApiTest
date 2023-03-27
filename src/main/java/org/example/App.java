package org.example;

import java.io.IOException;
import java.net.InetAddress;
public class App {
    private static final int TIMEOUT = 3 * 1000;
    private static boolean ping(String addr) {
        try {
            InetAddress address = InetAddress.getByName(addr);
            return address.isReachable(TIMEOUT);
        } catch (IOException exc){
            return false;
        }
    }
    public static void main(String[] args) {
        long t = System.currentTimeMillis();
        if (ping("192.168.12.50"))
            System.out.println("Сервер доступен");
        else
            System.out.println("Сервер недоступен");
        System.out.println(System.currentTimeMillis() - t);
    }

}
