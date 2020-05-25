package me.mmm10.gamble;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class SlotMachine implements CommandExecutor {
    Economy econ;

    public SlotMachine(Economy economy) {
        econ = economy;
    }
    int cost = 1000;
    int coal = 3000;
    int iron = 8000;
    int gold = 15000;
    int diamond = 30000;
    int emerald = 100000;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("slots") && (args.length == 1) && (args[0].equalsIgnoreCase("spin"))) {
            String spin1 = null;
            String spin2 = null;
            String spin3 = null;
            if (cost <= econ.getBalance(player)) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE+"Spinning...");
                Random randomslot1 = new Random();
                int slot1 = randomslot1.nextInt(100) + 1;
                Random randomslot2 = new Random();
                int slot2 = randomslot2.nextInt(100);
                Random randomslot3 = new Random();
                int slot3 = randomslot3.nextInt(100);
                if (slot1 <= 40) {
                    spin1 = "Coal";
                    sender.sendMessage(ChatColor.BLACK + "Coal");
                } else if ((slot1 > 40) && (slot1 <= 65)) {
                    spin1 = "Iron";
                    sender.sendMessage(ChatColor.WHITE + "Iron");
                } else if ((slot1 > 65) && (slot1 <= 85)) {
                    spin1 = "Gold";
                    sender.sendMessage(ChatColor.GOLD + "Gold");
                } else if ((slot1 > 85) && (slot1 <= 95)) {
                    spin1 = "Diamond";
                    sender.sendMessage(ChatColor.BLUE + "Diamond");
                } else if ((slot1 > 95) && (slot1 <= 100)) {
                    spin1 = "Emerald";
                    sender.sendMessage(ChatColor.GREEN + "Emerald");
                }
                if (slot2 <= 40) {
                    spin2 = "Coal";
                    sender.sendMessage(ChatColor.BLACK + "Coal");
                } else if ((slot2 > 40) && (slot2 <= 65)) {
                    spin2 = "Iron";
                    sender.sendMessage(ChatColor.WHITE + "Iron");
                } else if ((slot2 > 65) && (slot2 <= 85)) {
                    spin2 = "Gold";
                    sender.sendMessage(ChatColor.GOLD + "Gold");
                } else if ((slot2 > 85) && (slot2 <= 95)) {
                    spin2 = "Diamond";
                    sender.sendMessage(ChatColor.BLUE + "Diamond");
                } else if ((slot2 > 95) && (slot2 <= 100)) {
                    spin2 = "Emerald";
                    sender.sendMessage(ChatColor.GREEN + "Emerald");
                }
                if (slot3 <= 40) {
                    spin3 = "Coal";
                    sender.sendMessage(ChatColor.BLACK + "Coal");
                } else if ((slot3 > 40) && (slot3 <= 65)) {
                    spin3 = "Iron";
                    sender.sendMessage(ChatColor.WHITE + "Iron");
                } else if ((slot3 > 65) && (slot3 <= 85)) {
                    spin3 = "Gold";
                    sender.sendMessage(ChatColor.GOLD + "Gold");
                } else if ((slot3 > 85) && (slot3 <= 95)) {
                    spin3 = "Diamond";
                    sender.sendMessage(ChatColor.BLUE + "Diamond");
                } else if ((slot3 > 95) && (slot3 <= 100)) {
                    spin3 = "Emerald";
                    sender.sendMessage(ChatColor.GREEN + "Emerald");
                }
                if (spin1 == "Coal" && spin2 == "Coal" && spin3 == "Coal") {
                    EconomyResponse a = econ.depositPlayer(player, coal);
                    if (a.transactionSuccess()) {
                        sender.sendMessage(String.format(ChatColor.GREEN + "You got all coal! You won $" + coal));
                    }

                } else if (spin1 == "Iron" && spin2 == "Iron" && spin3 == "Iron") {
                    EconomyResponse a = econ.depositPlayer(player, iron);
                    if (a.transactionSuccess()) {
                        sender.sendMessage(String.format(ChatColor.GREEN + "You got all iron! You won $" + iron));
                    }
                } else if (spin1 == "Gold" && spin2 == "Gold" && spin3 == "Gold") {
                    EconomyResponse a = econ.depositPlayer(player, gold);
                    if (a.transactionSuccess()) {
                        sender.sendMessage(String.format(ChatColor.GREEN + "You got all gold! You won $" + gold));
                    }
                } else if (spin1 == "Diamond" && spin2 == "Diamond" && spin3 == "Diamond") {
                    EconomyResponse a = econ.depositPlayer(player, diamond);
                    if (a.transactionSuccess()) {
                        sender.sendMessage(String.format(ChatColor.GREEN + "You got all diamond! You won $" + diamond));
                    }
                } else if (spin1 == "Emerald" && spin2 == "Emerald" && spin3 == "Emerald") {
                    EconomyResponse a = econ.depositPlayer(player, emerald);
                    if (a.transactionSuccess()) {
                        sender.sendMessage(String.format(ChatColor.GREEN + "You got all emerald! You won $" + emerald));
                    }
                } else {
                    EconomyResponse a = econ.withdrawPlayer(player, cost);
                    if (a.transactionSuccess()) {
                        sender.sendMessage(String.format(ChatColor.AQUA + "They didn't match. You lost $" + cost));
                    }

                }


            }
                else {
                    sender.sendMessage(ChatColor.GRAY + "You do not have enough money to spin");
                    return true;
                }
            }
            else {
                sender.sendMessage(ChatColor.GRAY + "Proper usage: /slots spin (will cost $" + cost+")");
                return true;
            }
        return true;
        }
    }

