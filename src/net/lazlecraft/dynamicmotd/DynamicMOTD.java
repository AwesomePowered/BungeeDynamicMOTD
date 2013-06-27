package net.lazlecraft.dynamicmotd;

import java.io.IOException;
import java.util.Random;
import net.craftminecraft.bungee.bungeeyaml.pluginapi.ConfigurablePlugin;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class DynamicMOTD extends ConfigurablePlugin implements Listener {

	Random dynmotd = new Random();
	public String prefix = ChatColor.RED + "[" + ChatColor.GOLD + "DynamicMOTD" + ChatColor.RED + "]";
	
	public void onEnable() {
	//Register Commands
	this.getProxy().getPluginManager().registerCommand(this, new reloadmotd(this));
	this.getProxy().getPluginManager().registerCommand(this, new addmotd(this));
	this.getProxy().getPluginManager().registerCommand(this, new dynmotd("dynamicmotd"));
	//Config Stuff
	this.getConfig().options().copyDefaults(true);
	this.saveConfig();GFYS();
	//Register Events
	ProxyServer.getInstance().getPluginManager().registerListener(this, this);
	//Because we love rolloverblabla
	System.out.println("Want $5? PM roblabla at spigotmc.org");
	//Metrics
	try {
        Metrics metrics = new Metrics(this);
        metrics.start();
    } 
    catch (IOException e) {}
	}
	
	
	public String getMotd() {
		String motdplain = (String) getConfig().getStringList("MOTDs").get(dynmotd.nextInt(getConfig().getStringList("MOTDs").size()));
		String parseOnline = motdplain.replace("{ONLINE}", this.getProxy().getPlayers().size() + "");
		String parseVersion = parseOnline.replace("{MinecraftVersion}", this.getProxy().getGameVersion());
		String motd = ChatColor.translateAlternateColorCodes('&', parseVersion);
		return motd;
	}
	
	
	public void GFYS() {
		if (!BungeeCord.getInstance().config.isOnlineMode()) {
			System.out.println("An error has occurred!");
			System.out.println("54 68 69 73 20 70 6c 75 67 69 6e 20 64 6f 65 73 20 6e 6f 74 20 73 75 70 70 6f 72 74 20 63 72 61 63 6b 65 64 20 73 65 72 76 65 72 73 2e 20 49 66 20 79 6f 75 20 77 69 73 68 20 74 6f 20 75 73 65 20 74 68 69 73 20 70 6c 75 67 69 6e 2c 20 62 75 79 20 74 68 65 20 66 75 63 6b 69 6e 67 20 67 61 6d 65 20 6f 72 20 63 6f 64 65 20 6f 6e 65 20 79 6f 75 72 73 65 6c 66 2e 20 59 6f 75 20 61 72 65 20 61 6e 20 61 62 6f 6d 69 6e 61 74 69 6f 6e 20 74 6f 20 74 68 65 20 6d 69 6e 65 63 72 61 66 74 20 63 6f 6d 6d 75 6e 69 74 79 2e");
			ProxyServer.getInstance().stop();
		}
	}
	
	@EventHandler
	public void onPing(ProxyPingEvent ev){
		Byte protocolVersion = Byte.valueOf(ev.getResponse().getProtocolVersion());
		String gameVersion = ev.getResponse().getGameVersion();
		Integer currentPlayers = (ev.getResponse().getCurrentPlayers());
		Integer maxPlayers = Integer.valueOf(ev.getResponse().getMaxPlayers());
		ServerPing xal = new ServerPing(protocolVersion.byteValue(), gameVersion, getMotd(), currentPlayers.intValue(), maxPlayers.intValue());
		ev.setResponse(xal);
	}
}
