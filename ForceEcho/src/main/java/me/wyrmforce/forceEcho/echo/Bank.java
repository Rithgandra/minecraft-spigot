package me.wyrmforce.forceEcho.echo;

import java.util.HashMap;
import java.util.UUID;

public class Bank {

    private static final HashMap<UUID, Double> bank = new HashMap<>();

    public static double get(UUID uuid) {
        return bank.getOrDefault(uuid, 0.0);
    }

    public static void deposit(UUID uuid, double amount) {
        bank.put(uuid, get(uuid) + amount);
    }

    public static boolean withdraw(UUID uuid, double amount) {
        if (get(uuid) < amount) return false;
        bank.put(uuid, get(uuid) - amount);
        return true;
    }
}
