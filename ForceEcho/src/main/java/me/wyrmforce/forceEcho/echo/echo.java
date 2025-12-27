package me.wyrmforce.forceEcho.echo;

import java.util.HashMap;
import java.util.UUID;

public class echo {

    private static final HashMap<UUID,Double> balance = new HashMap<>();

    public static double get(UUID uuid){

       return balance.getOrDefault(uuid , 0.0);
    }

    public static void add(UUID uuid , Double amount){
        balance.put(uuid , get(uuid)+amount);
    }
    public static boolean remove(UUID uuid, double amount) {
        if (get(uuid) < amount) return false;
        balance.put(uuid, get(uuid) - amount);
        return true;
    }

}
