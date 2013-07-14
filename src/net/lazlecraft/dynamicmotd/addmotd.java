package net.lazlecraft.dynamicmotd;

import java.util.Arrays;
import java.util.List;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;

public class addmotd extends Command {
	
	DynamicMOTD plugin;

	public addmotd(DynamicMOTD plugin) {
		super("addmotd");
		this.plugin = plugin;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (ProxyServer.getInstance().getConfigurationAdapter().getBoolean("online_mode",true)) {
		if (sender.hasPermission("dynamicmotd.add")) 
			if (args.length < 1) {
			sender.sendMessage(plugin.prefix + ChatColor.RED + "/addmotd <MOTD>");
		}
		List<String> motdlist = plugin.getConfig().getStringList("MOTDs");
		motdlist.addAll(Arrays.asList(args));
		plugin.getConfig().set("MOTDs", motdlist);
		plugin.saveConfig();
		}
	}
}