package natdertale.flashlights.mixin;

import natdertale.flashlights.Item.ModItems;
import natdertale.flashlights.component.ModComponents;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public class AnvilMixin {

    @Inject(method = "onTakeOutput", at = @At("HEAD"))
    private void colorManager(PlayerEntity player, ItemStack stack, CallbackInfo ci) {

        if (stack.getName()!= ModItems.FLASH_LIGHT.getName()) {
            String newName = stack.getName().getString();
            if (newName.contains(" ")) {
                String[] parts = newName.split(" ");
                if (parts[parts.length -1].startsWith("#")) {
                    String hex = parts[parts.length -1];
                    hex = hex.replace("#", "");

                    int rgb = Integer.parseInt(hex, 16);
                    int color = 0xFF000000 | rgb;

                    // dont set custom name if the name do not change
                    if (parts.length == 2 && parts[0].equals(ModItems.FLASH_LIGHT.getName())) {
                        stack.remove(DataComponentTypes.CUSTOM_NAME);
                    }
                    stack.set(DataComponentTypes.CUSTOM_NAME, Text.literal(newName.replace(parts[parts.length -1], "")));
                    stack.set(ModComponents.COLOR, color);

                }

            }
        }

    }

}
