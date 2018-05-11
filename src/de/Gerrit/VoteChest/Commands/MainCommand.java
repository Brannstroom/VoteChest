package de.Gerrit.VoteChest.Commands;

import de.Gerrit.VoteChest.Listener.SuperChestListener;
import de.Gerrit.VoteChest.Utils;
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
                    p.sendMessage(Utils.PREFIX + "/VoteChest help | Shows a list of all VoteChest Commands");
                    break;

                case 1:
                    if (p.hasPermission("VoteChest.Admin") || p.isOp()) {
                        if (args[0].equalsIgnoreCase("help")) {
                            p.sendMessage(Utils.PREFIX + "1. /VoteChest create  | Switches between, votechest placing and not placing mode");
                            p.sendMessage(Utils.PREFIX + "2. /VoteChest delete  | Deletes the VoteChest");
                            p.sendMessage(Utils.PREFIX + "3. /VoteChest reload  | Reloads the VoteChest Config");
                            p.sendMessage(Utils.PREFIX + "4. /VoteChest giveKey <Player>  | Gives a Player a VoteChestKey");
                        }

                        if (args[0].equalsIgnoreCase("create")) {
                            if (p.hasPermission("VoteChest.Admin") || p.isOp()) {
                                if (SuperChestListener.voteChestCreationToggle) {
                                    SuperChestListener.voteChestCreationToggle = false;
                                    p.sendMessage(Utils.PREFIX + "You no longer create a VoteChest when you place a Chest");

                                } else {
                                    SuperChestListener.voteChestCreationToggle = true;
                                    p.sendMessage(Utils.PREFIX + "You now create a VoteChest if you place a Chest");
                                }

                            } else {
                                p.sendMessage(Utils.NOPERMISSIONS);
                            }
                        }
                        if (args[0].equalsIgnoreCase("delete")) {
                            if (p.hasPermission("VoteChest.Admin") || p.isOp()) {
                                chestListener.resetVoteChestLocation();
                                p.sendMessage(Utils.PREFIX + "VoteChest deleted");
                            } else {
                                p.sendMessage(Utils.NOPERMISSIONS);
                            }
                        }

                        if (args[0].equalsIgnoreCase("reload")) {
                            if (p.hasPermission("VoteChest.Admin") || p.isOp()) {
                                Utils.getPlugin().reloadConfig();
                                p.sendMessage(Utils.PREFIX + "Configuration reloaded");
                            } else {
                                p.sendMessage(Utils.NOPERMISSIONS);
                            }
                        }

                    } else {
                        p.sendMessage(Utils.NOPERMISSIONS);
                    }
                    break;
                case 2:
                    //Falls der Command Sender ein Spieler ist
                    if (args[0].equalsIgnoreCase("giveKey")) {
                        if (p.hasPermission("VoteChest.Admin") || p.isOp()) {
                            try {
                                new GiveVoteChestKey(args[1]);
                            }catch (NullPointerException exception){
                                p.sendMessage(Utils.PREFIX + "Player not online");
                            }
                        } else {
                            p.sendMessage(Utils.NOPERMISSIONS);
                        }
                    }
                break;
            }

            //Falls der Command sender die Console ist
        } else {
            switch(args.length){
                case 2:
                if (args[0].equalsIgnoreCase("giveKey")) {
                    try {
                        new GiveVoteChestKey(args[1]);
                    }catch (NullPointerException exception){
                        System.out.println("Player not online");
                    }
                    }
                break;
                }
            }
        return true;
        }
    }

