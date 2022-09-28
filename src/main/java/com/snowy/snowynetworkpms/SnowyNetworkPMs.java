package com.snowy.snowynetworkpms;

import com.snowy.snowynetworkpms.command.MessageCommand;
import com.snowy.snowynetworkpms.command.ReplyCommand;
import com.snowy.snowynetworkpms.listener.NetworkListener;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.HashMap;
import java.util.UUID;

public final class SnowyNetworkPMs extends Plugin {

    private HashMap<UUID, UUID> recentMessages;
    @Override
    public void onEnable() {
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new MessageCommand(this));
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new ReplyCommand(this));

        ProxyServer.getInstance().getPluginManager().registerListener(this, new NetworkListener(this));

        recentMessages = new HashMap<>();
    }

    public HashMap<UUID, UUID> getRecentMessages() { return recentMessages; }
}
