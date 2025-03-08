package natdertale.flashlights.mixin;


import natdertale.flashlights.FlashLights;
import natdertale.flashlights.Item.ModItemTags;
import natdertale.flashlights.Item.ModItems;
import natdertale.flashlights.component.ModComponents;
import natdertale.flashlights.utils.ColorUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Item.class)
public class CraftingMixin {
	@Inject(at = @At("HEAD"), method = "onCraftByPlayer", cancellable = false)
	private void addColor(ItemStack stack, World world, PlayerEntity player, CallbackInfo ci) {

		if (world.isClient || !stack.getItem().equals(ModItems.FLASH_LIGHT)) return;
		for (int i =1 ; i <= 9; i++) {
			//FlashLights.LOGGER.info("slot : " + i + " " + player.currentScreenHandler.slots.get(i).getStack());
			ItemStack ingredient = player.currentScreenHandler.slots.get(i).getStack();

			if (ingredient.isIn(ModItemTags.GLASS_BLOCKS)) {
				stack.set(ModComponents.COLOR, ColorUtils.getGlassItemColor(ingredient.getItem()));
				//FlashLights.LOGGER.info("set color to " + ColorUtils.getGlassItemColor(ingredient.getItem()));
			}
		}
	}
}