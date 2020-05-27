package me.mmm10.gamble;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.SoundCategory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class Gamble extends JavaPlugin {
    private static Economy econ = null;
    public void loadConfiguration(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onEnable() {

        getCommand("gamble").setExecutor(this);
        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getCommand("cards").setExecutor(new Cards(econ));
        getCommand("slots").setExecutor(new SlotMachine(econ));
        getCommand("tyl").setExecutor(new TryYourLuck(econ));
        //getCommand("rps").setExecutor(new RockPaperScissors(econ));
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
        if (command.getName().equalsIgnoreCase("gamble") && (args.length <= 1)) {
            if (command.getName().equalsIgnoreCase("gamble")&&(args.length == 1)&&(args[0].equalsIgnoreCase("help"))){
                sender.sendMessage(ChatColor.DARK_AQUA+"/gamble help: brings up this message \n/gamble [amount] [heads or tails]: allows you to play a 50/50 heads or tails game \n/rps [player] [rock or paper or scissors] [wager]: allows you to challenge someone to a game of rock, paper, scissors \n/cards [wager] [amount of cards being chosen from] [your card pick]: Pick the right card and win serious cash! (The more cards that can be chosen, the more you win) \n/slots spin: get 3 of the same to win! \n/tyl [wager]: starts 'try your luck' then use /tyl pump or /tyl stop");
                return true;
            }
            else {
                sender.sendMessage(ChatColor.GRAY+"Proper usage is /gamble [amount] [heads or tails]");
                return true;
            }
        }

        if (command.getName().equalsIgnoreCase("gamble") && args.length == 2)
        {
            try {
                amount = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.GRAY+"Please type a valid number");
                return true;
            }
        }
        Player player = (Player) sender;
        if (amount > 99 && amount <= econ.getBalance(player)) {
            displaychoice = args[1];


            if (args[1].equalsIgnoreCase("heads")) {
                EconomyResponse s = econ.withdrawPlayer(player, amount);
                if (s.transactionSuccess()) {
                    sender.sendMessage(String.format(ChatColor.RED + "%s was taken", econ.format(s.amount)));
                    choice = 0;
                    sender.sendMessage(ChatColor.BLUE + "You have wagered $" + amount + " and chose " + displaychoice);
                }
            } else if (args[1].equalsIgnoreCase("tails")) {
                EconomyResponse s = econ.withdrawPlayer(player, amount);
                if (s.transactionSuccess()) {
                    sender.sendMessage(String.format(ChatColor.RED + "%s was taken", econ.format(s.amount)));
                    choice = 1;
                    sender.sendMessage(ChatColor.BLUE + "You have wagered $" + amount + " and chose " + displaychoice);
                }
            } else {
                sender.sendMessage(ChatColor.GRAY+"Please pick heads or tails");
                return true;
            }

            Random random = new Random();
            int result = random.nextInt(2);
            if (result == choice) {
                // sounds soon ((Player) sender).playSound(player, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                sender.sendMessage(ChatColor.DARK_PURPLE + "The coin landed on " + displaychoice + "! \nYou win $" + amount + "!");
                EconomyResponse r = econ.depositPlayer(player, amount * 2);
                if (r.transactionSuccess()) {
                    sender.sendMessage(String.format("You now have %s", econ.format(r.balance)));
                }

            }
            if (result != choice) {
                double r = econ.getBalance(player);
                sender.sendMessage(ChatColor.RED + "The coin did not land on " + displaychoice + "! \nYou lose $" + amount + "!");
                sender.sendMessage(String.format("You now have %s", econ.format(r)));
            }
            } else {
                sender.sendMessage(ChatColor.GRAY+"Invalid amount! Make sure the amount is $100+ and less than your balance!");
            }

            return true;
        }

    }
