package carpet_extention.logger;

import carpet.logging.LoggerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;

public class ExampleHUDController {






    public static void addToHud(MinecraftServer server) {

        if (ExampleLoggers.__my_pos) LoggerRegistry.getLogger("my_pos").log(ExampleHUDController::send_tps_display);

    }






    private static Text[] send_tps_display(String playerOptions, PlayerEntity player) {



            return new Text[]{
                    Text.literal("current position: ").formatted(Formatting.GRAY)
                            .append(Text.literal("(").formatted(Formatting.GRAY))
                            .append(Text.literal(
                                    MathHelper.floor(player.getPos().getX())
                                    + ", " +
                                    MathHelper.floor(player.getPos().getY())
                                    + ", " +
                                    MathHelper.floor(player.getPos().getZ())).formatted(Formatting.BOLD))
                            .append(Text.literal(")").formatted(Formatting.GRAY))
            };
    }



}
