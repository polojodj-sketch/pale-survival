package ru.fcl.palesurvival.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import ru.fcl.palesurvival.PaleSurvivalMod;

import java.util.function.Function;

public class ModBlocks {
    public static final Block RESIN_LANTERN = register("resin_lantern",
            Block::new,
            Block.Settings.copy(Blocks.GLOWSTONE).luminance(state -> 15));

    public static final Block CREAKING_PLUSH = register("creaking_plush",
            Block::new,
            Block.Settings.copy(Blocks.WHITE_WOOL).burnable());

    private static Block register(String name, Function<Block.Settings, Block> factory, Block.Settings settings) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(PaleSurvivalMod.MOD_ID, name));
        Block block = Registry.register(Registries.BLOCK, key, factory.apply(settings.registryKey(key)));
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(PaleSurvivalMod.MOD_ID, name));
        Registry.register(Registries.ITEM, itemKey,
                new BlockItem(block, new Item.Settings().registryKey(itemKey).useBlockPrefixedTranslationKey()));
        return block;
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL)
                .register(entries -> {
                    entries.add(RESIN_LANTERN);
                    entries.add(CREAKING_PLUSH);
                });
    }
}
