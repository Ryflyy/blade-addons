package blade.addon.features.dungeon.f7.dragons;

import blade.addon.utils.events.Events;
import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;

public class DragonTracer {

    public enum Team {
        ARCHER_TEAM("Archer team"), BERS_TEAM("Bers team");

        private final String label;

        Team(String label) {
            this.label = label;
        }

        @Override
        public String getLabel() {
            return label;
        }
    }

    private static final int SPAWN_DURATION = 100;

    private  static Dragon currentDragon = Dragon.NONE;
    private static boolean hasDoneSplit = false;
    private  static int tick = 0;
    
    public  static  void  init() {
        Events.ON_PARTICLE.register(packet-> {
            if (!validParticle(packet)) return false;
            
        }
    }

    private static boolean validParticle(ParticleS2CPacket packet) {
    }
}