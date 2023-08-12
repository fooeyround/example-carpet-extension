package carpet_extention.mixin.flyingPigs;


import carpet_extention.ExampleSimpleSettings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemSteerable;
import net.minecraft.entity.Saddleable;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PigEntity.class)
public abstract class PigEntityMixin extends AnimalEntity implements ItemSteerable, Saddleable {


    protected PigEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }


    @Override
    public void tick() {
        super.tick();

        if (ExampleSimpleSettings.flyingPigs && this.isOnGround() && !this.isAiDisabled()) {
            this.addVelocity(new Vec3d(0,1,0));
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 20 ,5, false, false));
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 30 ,5, false, false));

        }

    }
}
