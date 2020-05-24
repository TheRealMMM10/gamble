package me.mmm10.gamble;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class Gamble extends JavaPlugin {
    private static Economy econ = null;

    @Override
    public void onEnable() {
        getCommand("gamble").setExecutor(this);
        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }


    int amount;
    int choice;
    String displaychoice;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("gamble") && args.length == 0) {
            sender.sendMessage("Proper usage is /gamble [amount] [heads or tails]");
        }
        if (command.getName().equalsIgnoreCase("gamble") && args.length == 2) ;
        {
            Player player = (Player) sender;
            amount = Integer.parseInt(args[0]);
            if (amount>99 && amount<=econ.getBalance(player)) {
                displaychoice = args[1];
                EconomyResponse s = econ.withdrawPlayer(player, amount);
                if (s.transactionSuccess()) {
                    sender.sendMessage(String.format(ChatColor.RED+"%s was taken", econ.format(s.amount)));
                }

                if (args[1].equalsIgnoreCase("heads")) {
                    choice = 0;
                    sender.sendMessage(ChatColor.BLUE+"You have wagered $" + amount + " and chose " + displaychoice);
                } else if (args[1].equalsIgnoreCase("tails")) {
                    choice = 1;
                    sender.sendMessage(ChatColor.BLUE+"You have wagered $" + amount + " and chose " + displaychoice);
                } else {
                    sender.sendMessage("Please pick heads or tails");
                    return true;
                }

                Random random = new Random();
                int result = random.nextInt(2);
                if (result == choice) {
                    sender.sendMessage(ChatColor.DARK_PURPLE+"The coin landed on " + displaychoice + "! \nYou win $" + amount + "!");
                    EconomyResponse r = econ.depositPlayer(player, amount * 2);
                    if (r.transactionSuccess()) {
                        sender.sendMessage(String.format("You now have %s", econ.format(r.balance)));
                    }

                }
                if (result != choice) {
                    sender.sendMessage(ChatColor.RED+"The coin did not land on " + displaychoice + "! \nYou lose $" + amount + "!");
                    sender.sendMessage(String.format("You now have %s", econ.format(s.balance)));

                }
            }
                else
            {
                sender.sendMessage("Invalid amount! Make sure the amount is $100+ and less than your balance!");
            }



            }
            return true;
        }

    }
