package natdertale.flashlights;

import natdertale.flashlights.Item.ModItemTags;
import natdertale.flashlights.Item.ModItems;
import natdertale.flashlights.component.ModComponents;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class FlashLightsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(RecipeGen::new);
		pack.addProvider(TagProvider::new);
	}


	// Recipe generator
	private static class RecipeGen extends FabricRecipeProvider {


		public RecipeGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
			super(output, registriesFuture);
		}

		@Override
		public void generate(RecipeExporter exporter) {

			ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.FLASH_LIGHT).pattern("iii").pattern("lrg").pattern("iii")
					.input('l', Items.LEVER)
					.input('r', Items.REDSTONE_LAMP)
					.input('g', ModItemTags.GLASS_BLOCKS)
					.input('i', Items.IRON_INGOT)
					.criterion(FabricRecipeProvider.hasItem(Items.REDSTONE_LAMP),
							FabricRecipeProvider.conditionsFromItem(Items.REDSTONE_LAMP))
					.offerTo(exporter);

		}
	}

	// Tag Provider
	public class TagProvider extends FabricTagProvider<Item> {
		public TagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
			super(output, RegistryKeys.ITEM, registriesFuture);
		}

		@Override
		protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
			getOrCreateTagBuilder(ModItemTags.GLASS_BLOCKS)
					.add(Items.GLASS)
					.add(Items.GRAY_STAINED_GLASS)
					.add(Items.GREEN_STAINED_GLASS)
					.add(Items.BLACK_STAINED_GLASS)
					.add(Items.BLUE_STAINED_GLASS)
					.add(Items.BROWN_STAINED_GLASS)
					.add(Items.CYAN_STAINED_GLASS)
					.add(Items.LIGHT_BLUE_STAINED_GLASS)
					.add(Items.LIGHT_GRAY_STAINED_GLASS)
					.add(Items.LIME_STAINED_GLASS)
					.add(Items.MAGENTA_STAINED_GLASS)
					.add(Items.ORANGE_STAINED_GLASS)
					.add(Items.PINK_STAINED_GLASS)
					.add(Items.PURPLE_STAINED_GLASS)
					.add(Items.RED_STAINED_GLASS)
					.add(Items.TINTED_GLASS)
					.add(Items.WHITE_STAINED_GLASS)
					.add(Items.YELLOW_STAINED_GLASS)

			;

		}
	}


}
