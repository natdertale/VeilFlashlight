package natdertale.flashlights.Item;

import natdertale.flashlights.FlashLights;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModItemTags {

    public static final TagKey<Item> GLASS_BLOCKS = of("glass_blocks");

    public static void RegisterModItemTags() {
    }

    private static TagKey<Item> of(String id) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(FlashLights.MOD_ID,id));
    }
}
