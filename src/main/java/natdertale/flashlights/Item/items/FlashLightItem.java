package natdertale.flashlights.Item.items;

import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.light.AreaLight;
import natdertale.flashlights.component.ModComponents;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class FlashLightItem extends Item {

    private AreaLight light = null;

    public FlashLightItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {

        if ( this.getComponents().get(ModComponents.COLOR)!=null) {
            int color = this.getComponents().get(ModComponents.COLOR);
            int red = (color >> 16) & 0xFF;
            int green = (color >> 8) & 0xFF;
            int blue = color & 0xFF;

            tooltip.add(Text.literal("#" + red + green + blue)); //TODO add a formatting color with int color
        }

        super.appendTooltip(stack, context, tooltip, type);
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
