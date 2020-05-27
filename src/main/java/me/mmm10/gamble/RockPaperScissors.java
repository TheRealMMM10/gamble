/*package me.mmm10.gamble;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class RockPaperScissors implements CommandExecutor {
    Economy econ;

    public RockPaperScissors(Economy economy)
    {
        econ = economy;
    }
    Map<Player,String> rpsSender = new HashMap<Player,String>();
    Map<Player,String> rpsReceiver = new HashMap<Player,String>();
    String receiverChoiceMap;
    String senderChoiceMap;
    Player rpssender;
    Player opponent;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args)
    {
        if (command.getName().equalsIgnoreCase("rps") && (args.length == 2))
        {
            rpssender = (Player) sender;
            opponent = Bukkit.getPlayer(args[0]);
            opponent.sendMessage(rpsSender + " has challenged you to a game of Rock, Paper, Scissors! Use /rps [rock paper or scissors] to play!");
            if (args[1].equalsIgnoreCase("rock"))
            {
                String senderChoice = "rock";
                rpsSender.put(rpssender, senderChoice);
                rpsReceiver.put(opponent, null);
            } else if (args[1].equalsIgnoreCase("scissors"))
            {
                String senderChoice = "scissors";
                rpsSender.put(rpssender, senderChoice);
            } else if (args[1].equalsIgnoreCase("paper"))
            {
                String senderChoice = "paper";
                rpsSender.put(rpssender, senderChoice);
            } else
            {
                rpssender.sendMessage("Please type in rock paper or scissors");
                return true;
            }
        }
        if (command.getName().equalsIgnoreCase("rps") && (args.length == 1) && (rpsReceiver.containsKey(sender)))
        {
            if (args[0].equalsIgnoreCase("rock"))
            {
                String opponentChoice = "rock";
                rpsReceiver.replace(opponent, opponentChoice);
            } else if (args[0].equalsIgnoreCase("scissors"))
            {
                String opponentChoice = "scissors";
                rpsReceiver.replace(opponent, opponentChoice);
            } else if (args[0].equalsIgnoreCase("paper"))
            {
                String opponentChoice = "paper";
                rpsReceiver.replace(opponent, opponentChoice);
            } else
            {
                opponent.sendMessage("Please type in rock paper or scissors");
                return true;
            }
        }
        if (rpsSender.containsValue("rock") && (rpsReceiver.containsValue("scissors")) || rpsSender.containsValue("paper") && (rpsReceiver.containsValue("rock")) || rpsSender.containsValue("scissors") && (rpsReceiver.containsValue("paper")))
        {
            Set receiver = rpsReceiver.entrySet();//Converting to Set so that we can traverse
            Iterator itr = receiver.iterator();
            while (itr.hasNext())
            {
                //Converting to Map.Entry so that we can get key and value separately
                Map.Entry entry = (Map.Entry) itr.next();
                receiverChoiceMap = (String) entry.getValue();
                Set setsender = rpsSender.entrySet();//Converting to Set so that we can traverse
                Iterator itr2 = setsender.iterator();
                while (itr.hasNext())
                {
                    //Converting to Map.Entry so that we can get key and value separately
                    Map.Entry entry2 = (Map.Entry) itr2.next();
                    senderChoiceMap = (String) entry2.getValue();

                }
            }
            rpssender.sendMessage("You win! " + opponent + " chose " + receiverChoiceMap);
            opponent.sendMessage("You lose. " + rpsSender + " chose " + senderChoiceMap);
        } else if (rpsSender.containsValue("scissors") && (rpsReceiver.containsValue("rock")) || rpsSender.containsValue("rock") && (rpsReceiver.containsValue("paper")) || rpsSender.containsValue("paper") && (rpsReceiver.containsValue("scissors")))
        {
            rpssender.sendMessage("You lose. " + opponent + " chose " + receiverChoiceMap);
            opponent.sendMessage("You win! " + rpsSender + " chose " + senderChoiceMap);
        } else
        {
            rpssender.sendMessage("Tie! " + opponent + " chose " + receiverChoiceMap);
            opponent.sendMessage("Tie! " + rpsSender + " chose " + senderChoiceMap);
        }

         if (command.getName().equalsIgnoreCase("rps") && (args.length != 2 && args.length != 1))
    {
        sender.sendMessage("Proper usage is /rps [player] [rock, paper, or scissors] [wager]");
        return true;
    }

        return true;

    }
    }
*/


