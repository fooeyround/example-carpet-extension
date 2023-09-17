package carpet_extention.mixin.logger;


import carpet.logging.LoggerRegistry;
import carpet_extention.logger.ExampleLoggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {


    @Inject(method = "onBreak", at= @At("HEAD"))
    private void carpet_example$block_logger(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfo ci) {
        if (ExampleLoggers.__block_break && !world.isClient()) {

            //Works for players in survival
            LoggerRegistry.getLogger("block_break").log(() -> new MutableText[]{
                    Text.literal("Block Broken at " + pos.toShortString()),
                    Text.literal("Type: " + state.getBlock().getName().getString()),
                    Text.literal("Player: " + player.getName().getString())
            });
        }
    }

}
