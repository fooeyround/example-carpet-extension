package carpet_extention.mixin.logger;


import carpet.logging.LoggerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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


    @Inject(method = "afterBreak", at= @At("HEAD"))
    private static void carpet_example$block_logger(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack tool, CallbackInfo ci) {
        if (!world.isClient()) {





            LoggerRegistry.getLogger("block_logger").log(() -> new MutableText[]{
                    Text.literal("Block Broken at "+pos.toShortString()),
                    Text.literal("Type: "+ state.getBlock().getName())
            });
        }
    }

}
