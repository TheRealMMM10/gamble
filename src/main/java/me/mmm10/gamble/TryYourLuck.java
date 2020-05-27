package me.mmm10.gamble;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;
import java.util.*;


public class TryYourLuck implements CommandExecutor
{
    Economy econ;

    public TryYourLuck(Economy economy)
    {
        econ = economy;
    }
    int[] Wager = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    int [] ResultPop = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    int[] Counter = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    Player[] tylPlayer = {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null};


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args)
    {
        if (command.getName().equalsIgnoreCase("tyl") && (args.length == 1)&&(!args[0].equalsIgnoreCase("pump") && !args[0].equalsIgnoreCase("stop")))
        {
            Player toFind = (Player) sender;
            for (int i = 0; i < tylPlayer.length; i++)
            {
                if (toFind.equals(tylPlayer[i]))
                {
                    sender.sendMessage(ChatColor.GRAY + "Please finish pumping!");
                    return true;
                }
            }
            int wager;
            try
            {
                wager = Integer.parseInt(args[0]);
            } catch (NumberFormatException e)
            {
                sender.sendMessage(ChatColor.GRAY + "Please type a valid number");
                return true;
            }
            if (wager <= econ.getBalance((Player) sender))
            {
                int resultPop;
                Player tylplayer = (Player) sender;
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "You have started test your luck! Do /tyl pump to pump or /tyl to stop. The balloon will pop sometime between pump 1-21");
                Random randomPop = new Random();
                resultPop = randomPop.nextInt(21) + 1;
                int count = 0;
                while (0 != Wager[count])
                {
                    count++;
                }
                Wager[count] = wager;
                count = 0;
                while (0 != ResultPop[count])
                {
                    count++;
                }
                ResultPop[count] = resultPop;
                count = 0;
                while (null != tylPlayer[count])
                {
                    count++;
                }
                tylPlayer[count] = tylplayer;
            } else {
                sender.sendMessage(ChatColor.GRAY+"You do not have enough money for that wager");
                return true;
            }
        }

        if (command.getName().equalsIgnoreCase("tyl") && (args.length == 1) && (args[0].equalsIgnoreCase("pump")))
        {
            Player toFind = (Player) sender;
                    for (int i = 0; i < tylPlayer.length; i++)
                    {
                        if (toFind.equals(tylPlayer[i]))
                        {

                            int counter = Counter[i];
                            counter++;
                            Counter[i] = counter;
                            if (Counter[i] == 20) {
                                long wincalc = Counter[i]*10;
                                long winnings = (long) (Wager[i]/3 * Math.pow(wincalc,1.11)/40);
                                EconomyResponse d = econ.depositPlayer(tylPlayer[i], winnings);
                                if (d.transactionSuccess())
                                {
                                    sender.sendMessage(String.format(ChatColor.GOLD + "You made it to 20 pumps! and won %s!", econ.format(winnings)));
                                    Wager[i] = 0;
                                    Counter[i] = 0;
                                    tylPlayer[i] = null;
                                    ResultPop[i] = 0;
                                    return true;
                                }
                            }
                            else if (ResultPop[i] != Counter[i])
                            {
                                sender.sendMessage(ChatColor.DARK_GREEN + "The balloon did not pop! Total pumps: " + Counter[i]);
                            } else if (ResultPop[i] == Counter[i])
                            {
                                EconomyResponse c = econ.withdrawPlayer(tylPlayer[i], Wager[i]);
                                if (c.transactionSuccess())
                                {
                                    sender.sendMessage(String.format(ChatColor.RED + "The balloon popped! You lost %s", econ.format(Wager[i])));
                                    Wager[i] = 0;
                                    Counter[i] = 0;
                                    tylPlayer[i] = null;
                                    ResultPop[i] = 0;
                                    return true;
                                }
                            }
                        }
                    }


        } else if (command.getName().equalsIgnoreCase("tyl") && (args.length == 1) && (args[0].equalsIgnoreCase("stop")))
        {
            Player toFind = (Player) sender;
            for (int i = 0; i < tylPlayer.length; i++)
            {
                if (toFind.equals(tylPlayer[i]))
                {
                    long wincalc = Counter[i]*10;
                    long winnings = (long) (Wager[i]/4 * Math.pow(wincalc,1.11)/(10*(20-Counter[i])));
                    EconomyResponse d = econ.depositPlayer(tylPlayer[i], winnings);
                    if (d.transactionSuccess())
                    {
                        sender.sendMessage(String.format(ChatColor.GOLD + "You won %s!", econ.format(winnings)));
                        Wager[i] = 0;
                        Counter[i] = 0;
                        tylPlayer[i] = null;
                        ResultPop[i] = 0;
                        return true;
                    }
                } else if (tylPlayer[i] != sender)
                {
                    sender.sendMessage(ChatColor.GRAY + "Use /tyl [wager] first. Then use /tyl pump or /tyl stop");
                    return true;
                }
            }
        }
        if (command.getName().equalsIgnoreCase("tyl")&&(args.length != 1))
        {
            sender.sendMessage(ChatColor.GRAY + "Proper usage is /tyl [wager]. Then use /tyl pump or /tyl stop");
            return true;
        }

        return true;
    }

}
/*class TYLdata {

    int resultPop;
    int wager;
    int counter;
    Player tylplayer;
    public TYLdata(int counter, int resultPop, int wager, Player tylplayer){
        this.counter = 0;
        this.wager = 0;
        this.resultPop = 0;
        this.tylplayer = null;
    }

    public int getCounter()
    {
        return counter;
    }

    public int getResultpop()
    {
        return resultPop;
    }

    public int getWager()
    {
        return wager;
    }

    public Player getTylplayer()
    {
        return tylplayer;
    }

}
*/