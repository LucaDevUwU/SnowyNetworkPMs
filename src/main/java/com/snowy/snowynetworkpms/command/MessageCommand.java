package com.snowy.snowynetworkpms.command;

import com.snowy.snowynetworkpms.SnowyNetworkPMs;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class MessageCommand extends Command {
    private SnowyNetworkPMs network;
    public MessageCommand(SnowyNetworkPMs network) {
        super("msg");
        this.network = network;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;

            if (args.length >= 2) {
                ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
                if (target != null) {
                    StringBuilder builder = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        builder.append(args[i] + " ");
                    }

                    player.sendMessage("You -> " + target.getName() + ": " + builder);
                    target.sendMessage(player.getName() + " -> You: " + builder);

                    network.getRecentMessages().put(player.getUniqueId(), target.getUniqueId());
                } else {
                    player.sendMessage(ChatColor.RED + "This player was not found");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Invalid usage! Please use /msg <player> <message>.");
            }
        }
    }
}
