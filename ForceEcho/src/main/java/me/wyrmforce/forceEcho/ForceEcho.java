package me.wyrmforce.forceEcho;

import me.wyrmforce.forceEcho.commands.bal;
import me.wyrmforce.forceEcho.commands.Pay;
import me.wyrmforce.forceEcho.gui.StoreGUI;
import me.wyrmforce.forceEcho.commands.AdminCommand;
import me.wyrmforce.forceEcho.vaulthook.VaultHook;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public class ForceEcho extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        // -----------------------
        // Register Commands
        // -----------------------
        getCommand("bal").setExecutor(new bal());
        getCommand("pay").setExecutor(new Pay());
        getCommand("store").setExecutor(new StoreGUI());
        getCommand("ecoadmin").setExecutor(new AdminCommand());

        // -----------------------
        // Setup Vault economy
        // -----------------------
        setupVault();

        getLogger().info("ForceEcho Economy Enabled!");
    }

    private void setupVault() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            getLogger().severe("Vault not found! Vault support disabled.");
            return;
        }

        getServer().getServicesManager().register(
                Economy.class,
                new VaultHook(),
                this,
                ServicePriority.Highest
        );

        getLogger().info("Vault economy hooked successfully!");
    }
}
