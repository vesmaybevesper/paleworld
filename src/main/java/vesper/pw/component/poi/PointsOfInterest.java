/*
package vesper.pw.component.poi;

import com.google.common.collect.Maps;
import net.minecraft.block.BlockState;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class PointsOfInterest {
    public static final RegistryKey<POI> PALE_GARDEN = of("pale_garden");
    private static final Map<BlockState, RegistryEntry<POI>> POI_STATES_TO_TYPE = Maps.<BlockState, RegistryEntry<POI>>newHashMap();



    private static RegistryKey<POI> of(String id) {
        return RegistryKey.of((RegistryKey<? extends Registry<POI>>) RegistryKeys.POINT_OF_INTEREST_TYPE, Identifier.of(id));
    }

    private static POI register(
            Registry<PointsOfInterest> registry, RegistryKey<PointsOfInterest> key, Set<BlockState> states, int ticketCount, int searchDistance
    ) {
        POI pointsOfInterest = new POI(states, ticketCount, searchDistance);
        Registry.register(registry, key, pointsOfInterest);
        registerStates(registry.getOrThrow(key), states);
        return pointsOfInterest;
    }

    private static void registerStates(RegistryEntry<POI> poiTypeEntry, Set<BlockState> states) {
        states.forEach(state -> {
            RegistryEntry<POI> registryEntry2 = (RegistryEntry<POI>)POI_STATES_TO_TYPE.put(state, poiTypeEntry);
            if (registryEntry2 != null) {
                throw (IllegalStateException) Util.getFatalOrPause(new IllegalStateException(String.format(Locale.ROOT, "%s is defined in more than one PoI type", state)));
            }
        });
    }
}
*/
