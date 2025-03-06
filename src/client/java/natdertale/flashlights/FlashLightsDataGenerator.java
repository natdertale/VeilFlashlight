package natdertale.flashlights;

import natdertale.flashlights.Item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class FlashLightsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(RecipeGen::new);
	}

	private static class RecipeGen extends FabricRecipeProvider {


		public RecipeGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
			super(output, registriesFuture);
		}

		@Override
		public void generate(RecipeExporter exporter) {

			ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.FLASH_LIGHT).pattern("iii").pattern("lrg").pattern("iii")
					.input('l', Items.LEVER)
					.input('r', Items.REDSTONE_LAMP)
					.input('g', Items.GLASS)
					.input('i', Items.IRON_BARS)
					.criterion(FabricRecipeProvider.hasItem(Items.REDSTONE_LAMP),
							FabricRecipeProvider.conditionsFromItem(Items.REDSTONE_LAMP))
					.offerTo(exporter);

		}
	}
}
