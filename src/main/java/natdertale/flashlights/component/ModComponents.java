package natdertale.flashlights.component;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.serialization.Codec;
import natdertale.flashlights.FlashLights;
import net.minecraft.component.Component;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ModComponents {
    public static final ComponentType<Boolean> IS_ON = register("is_on", booleanBuilder -> booleanBuilder.codec(Codec.BOOL));
    public static final ComponentType<Integer> COLOR = register("color", booleanBuilder -> booleanBuilder.codec(Codec.INT));

    private static <T> ComponentType<T> register(
            String name, UnaryOperator<ComponentType.Builder<T>>
            builderOperator ) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(FlashLights.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentsTypes() {
        FlashLights.LOGGER.info("Registering Components Types for " + FlashLights.MOD_ID);
    }

}
