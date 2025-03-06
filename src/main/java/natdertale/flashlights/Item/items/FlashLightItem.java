package natdertale.flashlights.Item.items;

import foundry.veil.api.client.render.light.AreaLight;
import natdertale.flashlights.component.ModComponents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class FlashLightItem extends Item {

    private AreaLight light = null;

    public FlashLightItem(Settings settings) {
        super(settings);
    }



    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // on/off
        ItemStack stack = user.getStackInHand(hand);

        if (stack.get(ModComponents.IS_ON) == null && !world.isClient) {
            stack.set(ModComponents.IS_ON, true);
        } else if (!world.isClient) { //make sure to run this on only one side or the light will stay turned off
            if (stack.get(ModComponents.IS_ON)) {
                stack.set(ModComponents.IS_ON, false);
            } else {
                stack.set(ModComponents.IS_ON, true);
            }
        }

        return super.use(world, user, hand);
    }
}
