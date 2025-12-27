package me.wyrmforce.forceEcho.commands;

import me.wyrmforce.forceEcho.echo.Bank;
import me.wyrmforce.forceEcho.echo.echo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class bal implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (!(sender instanceof Player p)) return true;

    double wallet = echo.get(p.getUniqueId());
    double bank = Bank.get(p.getUniqueId());

    p.sendMessage("§aWallet: §e$" + wallet);
    p.sendMessage("§aBank: §e$" + bank);
    return true;
}

}
