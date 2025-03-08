package natdertale.flashlights.utils;

import natdertale.flashlights.FlashLights;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.item.Item;

public class ColorUtils {

    public static int getGlassColor(Block block) {

        int color = block.getDefaultMapColor().color;

        int i = 255; //TODO tweak util brightness is right

        // Extracting RGB values
        int red = (color >> 16 & 0xFF) * i / 255;
        int green = (color >> 8 & 0xFF) * i / 255;
        int blue = (color & 0xFF) * i / 255;

        // Returning color in 0xAARRGGBB format
        return 0xFF000000 | red << 16 | green << 8 | blue;
    }
    public static int getGlassItemColor(Item item) {
        Block block = Block.getBlockFromItem(item);
        return getGlassColor(block);
    }

    public static int blendColors(int baseColor, int overlayColor) {
        int baseAlpha = (baseColor >> 24) & 0xFF;
        int baseRed = (baseColor >> 16) & 0xFF;
        int baseGreen = (baseColor >> 8) & 0xFF;
        int baseBlue = baseColor & 0xFF;

        int overlayAlpha = (overlayColor >> 24) & 0xFF;
        int overlayRed = (overlayColor >> 16) & 0xFF;
        int overlayGreen = (overlayColor >> 8) & 0xFF;
        int overlayBlue = overlayColor & 0xFF;

        // Calculate blended alpha
        int finalAlpha = overlayAlpha + (baseAlpha * (255 - overlayAlpha) / 255);

        // Calculate blended RGB
        int finalRed = (overlayRed * overlayAlpha + baseRed * (255 - overlayAlpha)) / 255;
        int finalGreen = (overlayGreen * overlayAlpha + baseGreen * (255 - overlayAlpha)) / 255;
        int finalBlue = (overlayBlue * overlayAlpha + baseBlue * (255 - overlayAlpha)) / 255;

        // Combine the components back into a single ARGB color
        return (finalAlpha << 24) | (finalRed << 16) | (finalGreen << 8) | finalBlue;
    }

    public static int getNextColor(int colorInt) {

        int baseAlpha = (colorInt >> 24) & 0xFF;
        int red = (colorInt >> 16) & 0xFF;
        int green = (colorInt >> 8) & 0xFF;
        int blue = colorInt & 0xFF;

        // TODO do some math to make the color change"

        return 0xFF000000 | red << 16 | green << 8 | blue;
    }
}
