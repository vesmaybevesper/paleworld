package vesper.pw.entity.VampireBat;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import vesper.pw.entity.PaleAxolotl.PaleAxolotl;
import vesper.pw.item.PaleWorldItems;

public class VampireBat extends FlyingEntity implements Monster {
    private static final TrackedData<Byte> VAMPIRE_BAT_FLAGS = DataTracker.registerData(VampireBat.class, TrackedDataHandlerRegistry.BYTE);

    public VampireBat(EntityType<? extends VampireBat> entityType, World world) {
        super(entityType, world);
        if (!world.isClient) {
            this.setRoosting(false);
        }
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.goalSelector.add(3, new AttackGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PaleAxolotl.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, AxolotlEntity.class, true));
        this.targetSelector.add(5, new ActiveTargetGoal<>(this, SpiderEntity.class, true));
        this.targetSelector.add(5, new ActiveTargetGoal<>(this, CreeperEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createHostileAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.FOLLOW_RANGE, (double)35.0F)
                .add(EntityAttributes.FLYING_SPEED, (double)0.23F)
                .add(EntityAttributes.ATTACK_DAMAGE, (double)2.0F);
    }
    public int getWingFlapTickOffset() {
        return this.getId() * 3;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VAMPIRE_BAT_FLAGS, (byte) 0);
    }

    public void setRoosting(boolean roosting) {
        if (this.dataTracker == null) return;
        byte b = (Byte)this.dataTracker.get(VAMPIRE_BAT_FLAGS);
        if (roosting) {
            this.dataTracker.set(VAMPIRE_BAT_FLAGS, (byte)(b | 1));
        } else {
            this.dataTracker.set(VAMPIRE_BAT_FLAGS, (byte)(b & -2));
        }

    }

    @Nullable
    public SoundEvent getAmbientSound() {
        return this.isRoosting() && this.random.nextInt(4) != 0 ? null : SoundEvents.ENTITY_BAT_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_BAT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_BAT_DEATH;
    }


    public boolean isRoosting() {
        return ((Byte)this.dataTracker.get(VAMPIRE_BAT_FLAGS) & 1) != 0;
    }


    @Override
    protected boolean shouldDropLoot() {
        return true;
    }

    @Override
    public @Nullable ItemStack getPickBlockStack() {
        return new ItemStack(PaleWorldItems.VAMPIRE_BAT_SPAWN_EGG);
    }
}
