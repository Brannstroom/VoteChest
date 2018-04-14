package de.Gerrit.VoteChest.Commands;

import de.Gerrit.VoteChest.Listener.SuperChestListener;
import de.Gerrit.VoteChest.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        SuperChestListener chestListener = new SuperChestListener();

        if (sender instanceof Player) {
            Player p = (Player) sender;


            switch (args.length) {
                case 0:
                    p.sendMessage(Utils.PREFIX + "/VoteChest help | Um alle Befehle anzuzeigen");
                    break;

                case 1:
                    if (p.hasPermission("VoteChest.Admin") || p.isOp()) {
                        if (args[0].equalsIgnoreCase("help")) {
                            p.sendMessage(Utils.PREFIX + "1. /VoteChest create  | Wechselt zwischen dem VoteChest setzen " +
                                    "Modus hin und her");
                            p.sendMessage(Utils.PREFIX + "2. /VoteChest delete  | Löscht die VoteChest");
                        }

                        if (args[0].equalsIgnoreCase("create")) {
                            if (p.hasPermission("VoteChest.Admin") || p.isOp()) {
                                if (Utils.voteChestCreationToggle) {
                                    Utils.voteChestCreationToggle = false;
                                    p.sendMessage(Utils.PREFIX + "Du erstellst beim setzen einer Kiste nun keine VoteChest mehr");

                                } else {
                                    Utils.voteChestCreationToggle = true;
                                    p.sendMessage(Utils.PREFIX + "Du erstellst nun beim setzen einer Kiste eine VoteChest");
                                }

                            } else {
                                p.sendMessage(Utils.NOPERMISSIONS);
                            }
                        }
                        if (args[0].equalsIgnoreCase("delete")) {
                            if (p.hasPermission("VoteChest.Admin") || p.isOp()) {
                                chestListener.resetVoteChestLocation();
                                p.sendMessage("Die VoteChest wurde gelöscht");
                            } else {
                                p.sendMessage(Utils.NOPERMISSIONS);
                            }
                        }

                        if (args[0].equalsIgnoreCase("reload")) {
                            if (p.hasPermission("VoteChest.Admin") || p.isOp()) {
                                Utils.getPlugin().reloadConfig();
                                p.sendMessage("Die Config wurde neu geladen");
                            } else {
                                p.sendMessage(Utils.NOPERMISSIONS);
                            }
                        }

                    } else {
                        p.sendMessage(Utils.NOPERMISSIONS);
                    }
                    break;
                case 2:
                    if (args[0].equalsIgnoreCase("giveKey")) {
                        if (p.hasPermission("VoteChest.Admin") || p.isOp()) {
                            try {
                                new GiveVoteChestKey(args[1]);
                            }catch (NullPointerException exception){
                                p.sendMessage(Utils.PREFIX + "Der Spieler ist nicht online");
                            }
                        } else {
                            p.sendMessage(Utils.NOPERMISSIONS);
                        }
                    }
                break;
            }

        } else {
            switch(args.length){
                case 2:
                if (args[0].equalsIgnoreCase("giveKey")) {
                    try {
                        new GiveVoteChestKey(args[1]);
                    }catch (NullPointerException exception){
                        System.out.println("Der Spieler ist nicht Online");
                    }
                    }
                break;
                }
            }
        return true;
        }
    }

