package net.lazlecraft.dynamicmotd;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class reloadmotd extends Command {
	
	DynamicMOTD plugin;
	
	public reloadmotd(DynamicMOTD plugin) {
		super("reloadmotd");
		this.plugin = plugin;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender.hasPermission("dynamictmotd.reload")) {
		plugin.reloadConfig();
		sender.sendMessage(ChatColor.GOLD + "DynamicMOTD reloaded!");
		}

	}

}
