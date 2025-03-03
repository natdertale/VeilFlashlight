package natdertale.flashlights.Item.items;

import foundry.veil.api.client.registry.LightTypeRegistry;
import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.light.AreaLight;
import foundry.veil.api.client.render.light.PointLight;
import foundry.veil.platform.VeilEventPlatform;
import natdertale.flashlights.FlashLights;
import natdertale.flashlights.utils.OrientationUtils;
import net.minecraft.block.enums.Orientation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector3dc;

public class FlashLightItem extends Item {

    private AreaLight light = null;

    public FlashLightItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player && this.light != null && world.isClient()) {
            if (player.getMainHandStack().equals(stack) || player.getOffHandStack().equals(stack)) {
                Vec3d eyepos = player.getEyePos();

                this.light.setOrientation(OrientationUtils.getOrientation(player.getRotationVector()));
                this.light.setPosition(eyepos.getX(), eyepos.getY(), eyepos.getZ());
            }
            else if (this.light.getBrightness()  >= 1.0f) {
                this.light.setBrightness(0.0f);
            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // on/off
        if (this.light != null) {


            if (this.light.getBrightness() <= 0.0f) {
                this.light.setBrightness(1.0f);
            } else {
                this.light.setBrightness(0.0f);
            }
        } else { // else make new light

            this.light = new AreaLight();
            this.light.setBrightness(1.0f);
            this.light.setColor(0xDA8888); //slight red tint
            this.light.setDistance(20.0f);
            this.light.setAngle(1f);

            //TODO make this work for everyone on the server & do it every time it got turned on/off while making sure to not add it if already done
            if (VeilRenderSystem.renderer().getLightRenderer() != null) {
                FlashLights.LOGGER.info("added light to render system");
                VeilRenderSystem.renderer().getLightRenderer().addLight(light);
            }
        }

        return super.use(world, user, hand);
    }
}
