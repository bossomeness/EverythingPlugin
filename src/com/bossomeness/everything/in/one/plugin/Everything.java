package com.bossomeness.everything.in.one.plugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Everything extends JavaPlugin {

	@Override
	public void onEnable() {
		PluginManager pm = this.getServer().getPluginManager();
		/**
		 * adds permissions when the server starts up
		 */
		pm.addPermission(new Permissions().fire);
	}

	@Override
	public void onDisable() {
		/**
		 * This section disables the permissions
		 */
		getServer().getPluginManager().removePermission(
				new Permissions().heal);
		getServer().getPluginManager().removePermission(
				new Permissions().fire);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String commandLabel, String[] args) {
		Player player = (Player) sender;
		if (commandLabel.equalsIgnoreCase("heal")) {
			if (args.length == 0) {
				if (sender.hasPermission(new Permissions().heal)) {
					player.setHealth(20);
					player.setFoodLevel(20);
					player.setRemainingAir(20);
					player.setFireTicks(0);
					player.sendMessage(ChatColor.AQUA + "Healed!");
				} else {
					sender.sendMessage(ChatColor.RED
							+ "You do not have permission to perform this command!");
				}
			} else if (args.length == 1) {
				if (player.getServer().getPlayer(args[0]) != null) {
					if (sender.hasPermission(new Permissions().heal)) {
						Player targetPlayer = player.getServer().getPlayer(
								args[0]);
						targetPlayer.setHealth(20);
						targetPlayer.setFoodLevel(20);
						targetPlayer.setRemainingAir(20);
						targetPlayer.setFireTicks(0);
						targetPlayer.sendMessage(ChatColor.AQUA
								+ "You have just been healed!");
					} else {
						sender.sendMessage(ChatColor.RED
								+ "You do not have permission to perform this command!");
					}
				} else {
					player.sendMessage(ChatColor.RED + "Player Not Found!");
				}
			} else if (commandLabel.equalsIgnoreCase("fire")) {
				if (args.length == 0) {
					if (sender.hasPermission(new Permissions().fire)) {
						player.setFireTicks(Integer.parseInt(args[1]));
						player.sendMessage(ChatColor.DARK_RED + "You have been set on fire!");
					}
				}if (player.getServer().getPlayer(args[0]) != null) {
					if (sender.hasPermission(new Permissions().heal)) {
						Player targetPlayer = player.getServer().getPlayer(
								args[0]);
						targetPlayer.setFireTicks(Integer.parseInt(args[1]));
						targetPlayer.sendMessage(ChatColor.DARK_RED
								+ "You have been set on fire!");
					} else {
						sender.sendMessage(ChatColor.RED
								+ "You do not have permission to perform this command!");
					}
				} else {
					player.sendMessage(ChatColor.RED + "Player Not Found!");
				}
			}
		}
		return false;
	}

}
