package com.snowy.snowynetworkpms.command;

import com.snowy.snowynetworkpms.SnowyNetworkPMs;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ReplyCommand extends Command {

    private SnowyNetworkPMs network;
    public ReplyCommand(SnowyNetworkPMs network) {
        super("r");
        this.network = network;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if (args.length >= 1) {
                if (network.getRecentMessages().containsKey(player.getUniqueId())) {
                    ProxiedPlayer target = ProxyServer.getInstance().getPlayer(network.getRecentMessages().get(player.getUniqueId()));
                    if (target != null) {
                        StringBuilder builder = new StringBuilder();
                        for (int i = 0; i < args.length; i++) {
                            builder.append(args[i] + " ");
                        }

                        player.sendMessage("You -> " + target.getName() + ": " + builder);
                        target.sendMessage(player.getName() + " -> You: " + builder);
                    } else {
                        player.sendMessage(ChatColor.RED + "This player has gone offline");
                        network.getRecentMessages().remove(player.getUniqueId());
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "You have not messaged anyone recently.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Invalid usage! Please use /r <message>.");
            }
        }
    }
}
