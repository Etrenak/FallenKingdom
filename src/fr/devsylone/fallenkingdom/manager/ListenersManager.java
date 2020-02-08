package fr.devsylone.fallenkingdom.manager;

import fr.devsylone.fallenkingdom.Fk;
import fr.devsylone.fallenkingdom.listeners.block.BlockExplodeListener;
import fr.devsylone.fallenkingdom.listeners.block.BlockListener;
import fr.devsylone.fallenkingdom.listeners.block.BucketListener;
import fr.devsylone.fallenkingdom.listeners.block.LockedChestInteractListener;
import fr.devsylone.fallenkingdom.listeners.block.PortalCreateListener;
import fr.devsylone.fallenkingdom.listeners.entity.DamageListener;
import fr.devsylone.fallenkingdom.listeners.entity.mob.MobSpawn;
import fr.devsylone.fallenkingdom.listeners.entity.player.ChangeGamemodeListener;
import fr.devsylone.fallenkingdom.listeners.entity.player.ChatListener;
import fr.devsylone.fallenkingdom.listeners.entity.player.FoodListener;
import fr.devsylone.fallenkingdom.listeners.entity.player.GoToNetherListener;
import fr.devsylone.fallenkingdom.listeners.entity.player.InventoryListener;
import fr.devsylone.fallenkingdom.listeners.entity.player.JoinListener;
import fr.devsylone.fallenkingdom.listeners.entity.player.MoveListener;
import fr.devsylone.fallenkingdom.listeners.entity.player.ParticlesListener;
import fr.devsylone.fallenkingdom.listeners.entity.player.PotionListener;
import fr.devsylone.fallenkingdom.listeners.entity.player.PvpListener;
import fr.devsylone.fallenkingdom.listeners.entity.player.RespawnListener;
import fr.devsylone.fallenkingdom.listeners.entity.player.ScrollListener;
import fr.devsylone.fallenkingdom.listeners.entity.player.UsePortalListener;
import fr.devsylone.fallenkingdom.listeners.motd.MotdListener;

public class ListenersManager
{
	public static final void registerListeners(Fk plugin)
	{
		plugin.getServer().getPluginManager().registerEvents(new BlockListener(plugin), plugin);
		plugin.getServer().getPluginManager().registerEvents(new BucketListener(plugin), plugin);
		plugin.getServer().getPluginManager().registerEvents(new ChatListener(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new JoinListener(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new MoveListener(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new PvpListener(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new DamageListener(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new GoToNetherListener(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new FoodListener(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new MobSpawn(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new UsePortalListener(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new InventoryListener(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new ScrollListener(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new LockedChestInteractListener(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new BlockExplodeListener(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new PortalCreateListener(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new ChangeGamemodeListener(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new RespawnListener(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new MotdListener(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new PotionListener(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new ParticlesListener(), plugin);
	}
}
