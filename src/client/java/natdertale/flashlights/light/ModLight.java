package natdertale.flashlights.light;

import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.light.AreaLight;
import foundry.veil.platform.VeilEventPlatform;
import natdertale.flashlights.Item.items.FlashLightItem;
import natdertale.flashlights.component.ModComponents;
import natdertale.flashlights.utils.OrientationUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;

public class ModLight {
    public static final HashMap<Integer, AreaLight> ITEM_LIGHT = new HashMap<>();

    public static void init() {
        VeilEventPlatform.INSTANCE.onVeilRenderLevelStage((stage, levelRenderer, bufferSource, matrixStack, frustumMatrix, projectionMatrix, renderTick, deltaTracker, camera, frustum) -> {
            for (Entity entity : MinecraftClient.getInstance().world.getEntities()) {
                if (entity instanceof PlayerEntity player) {
                    tickPlayerFlashlight(player, deltaTracker);
                }
            }

        });
    }

    private static void tickPlayerFlashlight(PlayerEntity player, RenderTickCounter deltaTracker) {

        // TODO if player has a flashlight in hand else set delete his light
        if (player.getMainHandStack().getItem() instanceof FlashLightItem || player.getOffHandStack().getItem() instanceof FlashLightItem) {

            Hand hand = (player.getOffHandStack().getItem() instanceof FlashLightItem) ? Hand.OFF_HAND : Hand.MAIN_HAND;

            ItemStack stack = player.getStackInHand(hand);

            AreaLight light = new AreaLight();
            Vec3d renderPosition = player.getLerpedPos(deltaTracker.getTickDelta(false));
            Vec3d eyepos = player.getEyePos();

            if (ITEM_LIGHT.containsKey(player.getId())) {
                light = ITEM_LIGHT.get(player.getId());
            } else {
                light.setBrightness(1.0f);
                light.setColor(0xDA8888); //slight red tint
                light.setDistance(20.0f);
                light.setAngle(1f);

                ITEM_LIGHT.put(player.getId(), light);
                VeilRenderSystem.renderer().getLightRenderer().addLight(light);
            }
            light.setPosition(renderPosition.x, renderPosition.y+1.5, renderPosition.z);
            light.setOrientation(OrientationUtils.getOrientation(player.getRotationVector()));

            // compoent on/off
            if (stack.get(ModComponents.IS_ON)!=null) {
                if (!stack.get(ModComponents.IS_ON)) {
                    light.setBrightness(1.0f);
                } else {
                    light.setBrightness(0.0f);
                }
            } else {
                light.setBrightness(0.0f);
            }



        } else if (ITEM_LIGHT.containsKey(player.getId())){
            AreaLight light = ITEM_LIGHT.get(player.getId());
            VeilRenderSystem.renderer().getLightRenderer().removeLight(light);
            ITEM_LIGHT.remove(player.getId());
        }

    }
}
