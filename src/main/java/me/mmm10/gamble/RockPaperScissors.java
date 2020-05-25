/*package me.mmm10.gamble;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RockPaperScissors implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
    Player rpsSender;
    Player opponent;
        if (command.getName().equalsIgnoreCase("rps")&&(args.length>=2)) {
            rpsSender = (Player) sender;
            opponent = Bukkit.getPlayer(args[0]);
            String senderChoice;
            String opponentChoice;
            opponent.sendMessage(rpsSender+" has challenged you to a game of Rock, Paper, Scissors! Use /rps [rock paper or scissors] to play!");
            if (args[1].equalsIgnoreCase("rock")){
                senderChoice = "rock";            }
            else if (args[1].equalsIgnoreCase("scissors")){
                senderChoice = "scissors";
            }
            else if (args[1].equalsIgnoreCase("paper")){
                senderChoice = "paper";
            }
            else {
                rpsSender.sendMessage("Please type in rock paper or scissors");
                return true;
            }
            if (command.getName().equalsIgnoreCase("rps")&&(args.length==1)&&(sender == opponent)) {
                if (args[0].equalsIgnoreCase("rock")) {
                    opponentChoice = "rock";
                } else if (args[0].equalsIgnoreCase("scissors")) {
                    opponentChoice = "scissors";
                } else if (args[0].equalsIgnoreCase("paper")) {
                    opponentChoice = "paper";
                } else {
                    opponent.sendMessage("Please type in rock paper or scissors");
                    return true;
                }
            }
            if (senderChoice == "rock"&&(opponentChoice == "scissors")||(senderChoice == "paper"&&(opponentChoice == "rock")||(senderChoice == "scissors"&&(opponentChoice == "paper")))){
                rpsSender.sendMessage("You win! "+opponent+" chose "+opponentChoice);
                opponent.sendMessage("You lose. "+rpsSender+" chose "+senderChoice);
            }
            else if (opponentChoice == "rock"&&(senderChoice == "scissors")||(opponentChoice == "paper"&&(senderChoice == "rock")||(opponentChoice == "scissors"&&(senderChoice == "paper")))) {
                rpsSender.sendMessage("You lose. " + opponent + " chose " + opponentChoice);
                opponent.sendMessage("You win! " + rpsSender + " chose " + senderChoice);
            }
            else if (opponentChoice==senderChoice) {
                rpsSender.sendMessage("Tie! " + opponent + " chose " + opponentChoice);
                opponent.sendMessage("Tie! " + rpsSender + " chose " + senderChoice);
            }
        }
         else if (command.getName().equalsIgnoreCase("rps")&&(args.length<2)) {
             sender.sendMessage("Proper usage is /rps [player] [rock, paper, or scissors] [wager]");
             return true;
        }
        return true;
    }

}
*/
