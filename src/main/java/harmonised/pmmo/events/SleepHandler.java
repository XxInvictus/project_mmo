package harmonised.pmmo.events;

import harmonised.pmmo.config.Config;
import harmonised.pmmo.pmmo_saved_data.PmmoSavedData;
import harmonised.pmmo.util.XP;
import net.minecraft.world.World;
import net.minecraftforge.event.world.SleepFinishedTimeEvent;

public class SleepHandler
{
    private static final boolean sleepRechargesAllPlayersVeinCharge = Config.forgeConfig.sleepRechargesAllPlayersVeinCharge.get();
    private static final double maxVeinCharge = Config.forgeConfig.maxVeinCharge.get();

    public static void handleSleepFinished( SleepFinishedTimeEvent event )
    {
        if( sleepRechargesAllPlayersVeinCharge )
        {
            ( (World) event.getWorld() ).getServer().getPlayerList().getPlayers().forEach(player ->
            {
                Config.getAbilitiesMap( player ).put( "veinLeft", maxVeinCharge );
            });
        }
    }
}