package me.mmm10.gamble;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class Cards implements CommandExecutor {
    Economy econ;

    public Cards(Economy economy) {
        econ = economy;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("cards") && (args.length == 3)) {
            int wager;
            int cardAMT;
            int cardPick;
            try {
                wager = Integer.parseInt(args[0]);
                cardAMT = Integer.parseInt(args[1]);
                cardPick = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.GRAY+"Please type a valid number");
                return true;
            }
            if ((cardAMT>=10)&&(wager<=econ.getBalance(player))) {
            Random randomcard = new Random();
            int result = randomcard.nextInt(cardAMT)+1;
            if (cardPick == result) {
                long winnings = Math.round(Math.pow(wager / 2, .93)*(cardAMT/1.5));
                EconomyResponse t = econ.depositPlayer(player, winnings);
                if (t.transactionSuccess()) {
                    sender.sendMessage(String.format(ChatColor.GREEN+"The card was " + result + " You win %s!", econ.format(winnings)));
                }

            } else if (cardPick != result) {
                EconomyResponse t = econ.withdrawPlayer(player, (wager));
                if (t.transactionSuccess()) {
                    sender.sendMessage(String.format(ChatColor.AQUA + "The card was " + result + " You lose %s!", econ.format(wager)));

                }

            }
        }
            else {
                sender.sendMessage(ChatColor.GRAY+"Please make sure the card amount is greater than 10 and you can afford your wager");
                return true;
            }
        } else if (command.getName().equalsIgnoreCase("cards") && (args.length != 3)) {
            sender.sendMessage(ChatColor.GRAY+"Proper usage is /cards [wager] [amount of cards] [card you pick]");
        }
        return true;
    }
}
