package net.lazlecraft.dynamicmotd;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class dynmotd extends Command {

	public dynmotd(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	DynamicMOTD plugin;
	
	@Override
	public void execute(CommandSender sender, String[] arg1) {
		sender.sendMessage(ChatColor.GOLD + "DynamicMOTD by the almighty " + ChatColor.AQUA + "LaxWasHere");
		sender.sendMessage(ChatColor.GREEN + "Running Version: " + ChatColor.RED + "0.5");

	}

}
