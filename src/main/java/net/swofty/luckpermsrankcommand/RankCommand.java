package net.swofty.luckpermsrankcommand;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import net.swofty.luckpermsrankcommand.LuckpermsRankMain;
import org.bukkit.command.CommandExecutor;

public class RankCommand implements CommandExecutor
{
    private LuckpermsRankMain plugin;
    public RankCommand rank;

    public RankCommand(final LuckpermsRankMain plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Console")));
            return false;
        }
        final LuckPerms rank = LuckPermsProvider.get();
        final Player player = (Player)sender;
        if (!player.hasPermission("swofty.rankeverything")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Permission")));
            return false;
        }
        if (args.length == 0) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Invalid Usage")));
            return false;
        }
        if (!sender.hasPermission("swofty.rankreload")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', LuckpermsRankMain.plugin.getConfig().getString("Permission")));
            return false;
        }

        switch (args[0]) {
            case "reload":
                this.plugin.reloadConfig();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', LuckpermsRankMain.plugin.getConfig().getString("Reload")));
                return false;

            case "help":
                if (!sender.hasPermission("swofty.rankhelp")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', LuckpermsRankMain.plugin.getConfig().getString("Permission")));
                    return false;
                }
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', LuckpermsRankMain.plugin.getConfig().getString("Help")));
                return false;
        }
            final Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', LuckpermsRankMain.plugin.getConfig().getString("Player Offline").replace("%PlayerOffline%", args[0])));
                return false;
            }
            final Group rankGroup = rank.getGroupManager().getGroup(args[1]);
            if (rankGroup == null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', LuckpermsRankMain.plugin.getConfig().getString("Rank Not Found").replace("%Rank%", args[1])));
                return false;
            }
            if (!sender.hasPermission("swofty.rankset")) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', LuckpermsRankMain.plugin.getConfig().getString("Permission")));
                return false;
            }
            Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "lp user " + args[0] + " parent set " + args[1]);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', LuckpermsRankMain.plugin.getConfig().getString("Rank Set").replace("%Target%", args[0]).replace("%Rank%", args[1])));
            target.sendMessage(ChatColor.translateAlternateColorCodes('&', LuckpermsRankMain.plugin.getConfig().getString("Rank Set Other").replace("%Rank%", args[1]).replace("%Player%", args[0])));
            return false;
    }
}
