package me.wyrmforce.forceEcho.commands;

import me.wyrmforce.forceEcho.echo.echo;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Pay implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player p = (Player) sender;

        if (args.length != 2) {
            p.sendMessage("§cUsage: /pay <player> <amount>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            p.sendMessage("§cPlayer not found!");
            return true;
        }

        double amount;

        try {
            amount = Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            p.sendMessage("§cInvalid amount!");
            return true;
        }

        if (amount <= 0) {
            p.sendMessage("§cAmount must be positive!");
            return true;
        }

        if (!echo.remove(p.getUniqueId(), amount)) {
            p.sendMessage("§cNot enough money!");
            return true;
        }

        echo.add(target.getUniqueId(), amount);

        p.sendMessage("§aYou paid $" + amount + " to " + target.getName());
        target.sendMessage("§aYou received $" + amount + " from " + p.getName());

        return true;
    }
}
