package noBug;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;

public class M extends JavaPlugin implements Listener{
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	@Override
	public void onDisable() {
		HandlerList.unregisterAll();
	}
	@EventHandler
	public void aa(PlayerInteractEvent e){
		Player p =e.getPlayer();
		if(p.hasPermission("bAntiBug.bypass")) return;
		if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) return;
		if(p.getItemInHand().getType().equals(Material.getMaterial(383))){
			Block clickBlokead = e.getClickedBlock();
			// Poderia ter feito com BlockFace,porém achei melhor assim
			Location locCima = new Location(clickBlokead.getWorld(),clickBlokead.getLocation().getX(), clickBlokead.getLocation().getY() + 2,clickBlokead.getLocation().getZ());
			if((locCima.getBlock().getType() != Material.AIR)){
				e.setCancelled(true);
				p.sendMessage(getConfig().getString("msg1").replace("&", "§"));
				if(getConfig().getString("punicao.tipo").equalsIgnoreCase("NONE") == false){
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), getConfig().getString("punicao.comando").replace("%player%", p.getName()));
				}
			}
		}
		
	}
	
	
}
