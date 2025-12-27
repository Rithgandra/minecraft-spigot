package me.wyrmforce.forceEcho.commands;

import me.wyrmforce.forceEcho.echo.Bank;
import me.wyrmforce.forceEcho.echo.echo;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!sender.hasPermission("forceecho.admin")) {
            sender.sendMessage("§cYou do not have permission to use this command!");
            return true;
        }

        if (args.length < 3) {
            sender.sendMessage("§cUsage: /ecoadmin <add|remove|set|bankadd|bankremove|reset> <player> <amount>");
            return true;
        }

        String action = args[0].toLowerCase();
        Player target = Bukkit.getPlayer(args[1]);
        if (target == null) {
            sender.sendMessage("§cPlayer not found!");
            return true;
        }

        double amount = 0;
        if (!action.equals("reset")) {
            try { amount = Double.parseDouble(args[2]); }
            catch (NumberFormatException e) {
                sender.sendMessage("§cInvalid amount!");
                return true;
            }
        }

        switch (action) {
            case "add" -> {
                echo.add(target.getUniqueId(), amount);
                sender.sendMessage("§aAdded $" + amount + " to " + target.getName());
            }
            case "remove" -> {
                if (!echo.remove(target.getUniqueId(), amount)) {
                    sender.sendMessage("§cPlayer doesn't have enough money!");
                    return true;
                }
                sender.sendMessage("§aRemoved $" + amount + " from " + target.getName());
            }
            case "set" -> {
                echo.remove(target.getUniqueId(), echo.get(target.getUniqueId())); // reset wallet
                echo.add(target.getUniqueId(), amount);
                sender.sendMessage("§aSet " + target.getName() + "'s balance to $" + amount);
            }
            case "bankadd" -> {
                Bank.deposit(target.getUniqueId(), amount);
                sender.sendMessage("§aAdded $" + amount + " to " + target.getName() + "'s bank");
            }
            case "bankremove" -> {
                if (!Bank.withdraw(target.getUniqueId(), amount)) {
                    sender.sendMessage("§cPlayer doesn't have enough bank money!");
                    return true;
                }
                sender.sendMessage("§aRemoved $" + amount + " from " + target.getName() + "'s bank");
            }
            case "reset" -> {
                echo.remove(target.getUniqueId(), echo.get(target.getUniqueId()));
                Bank.withdraw(target.getUniqueId(), Bank.get(target.getUniqueId()));
                sender.sendMessage("§aReset " + target.getName() + "'s wallet and bank to $0");
            }
            default -> sender.sendMessage("§cUnknown action! Use add, remove, set, bankadd, bankremove, reset");
        }

        return true;
    }
}
