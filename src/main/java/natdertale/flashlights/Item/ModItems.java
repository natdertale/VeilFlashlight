package natdertale.flashlights.Item;

import natdertale.flashlights.FlashLights;
import natdertale.flashlights.Item.items.FlashLightItem;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModItems {

    public static FlashLightItem FLASH_LIGHT = registerItem("flashlight", new FlashLightItem(new Item.Settings().maxCount(1)));

    private static <T extends Item> T registerItem(String name, T item) {
        return Registry.register(Registries.ITEM, Identifier.of(FlashLights.MOD_ID, name), item);
    }

    private static void addItemsToToolsItemGroup(FabricItemGroupEntries entries) {
        entries.add(FLASH_LIGHT);
    }

    public static void RegisterModItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToToolsItemGroup);

    }





}
