package natdertale.flashlights;


import natdertale.flashlights.rendering.ModLight;
import net.fabricmc.api.ClientModInitializer;

import java.util.function.Supplier;

public class FlashLightsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {

		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		ModLight.init();
	}
}
