package natdertale.flashlights.Item;

import natdertale.flashlights.FlashLights;
import natdertale.flashlights.Item.items.FlashLightItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static FlashLightItem FLASH_LIGHT = registerItem("flash_light", new FlashLightItem(new Item.Settings()));

    private static <T extends Item> T registerItem(String name, T item) {
        return Registry.register(Registries.ITEM, Identifier.of(FlashLights.MOD_ID, name), item);
    }

    public static void RegisterModItems() {

    }

}
