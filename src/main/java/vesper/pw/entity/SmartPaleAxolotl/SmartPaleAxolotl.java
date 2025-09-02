package vesper.pw.entity.SmartPaleAxolotl;

import net.minecraft.entity.Bucketable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Targeter;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.SmartBrainProvider;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.MoveToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetPlayerLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetRandomLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.AxolotlSpecificSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;

import java.util.List;

public class SmartPaleAxolotl extends AxolotlEntity implements Bucketable {

    public SmartPaleAxolotl(EntityType<? extends AxolotlEntity> entityType, World world) {
        super(entityType, world);
    }

    /*@Override
    public List<? extends ExtendedSensor<? extends SmartPaleAxolotl>> getSensors() {
        return List.of(
                new AxolotlSpecificSensor<>(),
                new HurtBySensor<>(),
                new NearbyLivingEntitySensor<>()
        );
    }

    @Override
    protected void mobTick(ServerWorld world) {
        tickBrain(this);
    }

    @Override
    protected Brain.Profile<AxolotlEntity> createBrainProfile() {
        return new SmartBrainProvider<>(this);
    }

    @Override
    public BrainActivityGroup<? extends SmartPaleAxolotl> getCoreTasks() {
        return BrainActivityGroup.coreTasks(
                new LookAtTarget<>(),
                new MoveToWalkTarget<>()
        );
    }

    @Override
    public BrainActivityGroup<? extends SmartPaleAxolotl> getIdleTasks() {
        return BrainActivityGroup.idleTasks(
                new FirstApplicableBehaviour<SmartPaleAxolotl>(
                        new TargetOrRetaliate<>(),
                        new SetPlayerLookTarget<>(),
                        new SetRandomLookTarget<>()),
                new OneRandomBehaviour<>(
                        new SetRandomWalkTarget<>(),
                        new Idle<>().runFor(entity -> entity.getRandom().nextBetweenExclusive(30, 60))));
    }

    @Override
    public BrainActivityGroup<? extends SmartPaleAxolotl> getFightTasks() {
        return BrainActivityGroup.fightTasks();
    }*/
}
