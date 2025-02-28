package vesper.pw.component.poi;

import net.minecraft.block.BlockState;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.Set;
import java.util.function.Predicate;

public record POI(Set<BlockState> blockStates, int ticketCount, int searchDistance) {
    public static final Predicate<RegistryEntry<POI>> NONE = type -> false;

    public POI(Set<BlockState> blockStates, int ticketCount, int searchDistance){
        blockStates = Set.copyOf(blockStates);
        this.blockStates = blockStates;
        this.ticketCount = ticketCount;
        this.searchDistance = searchDistance;
    }
    public boolean contains(BlockState state){return this.blockStates.contains(state);}
}
