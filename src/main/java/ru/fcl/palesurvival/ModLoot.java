package ru.fcl.palesurvival;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;
import ru.fcl.palesurvival.item.ModItems;

public class ModLoot {
    private static final Identifier PALE_OAK_LEAVES_TABLE = Identifier.of("minecraft", "blocks/pale_oak_leaves");

    public static void inject() {
        LootTableEvents.MODIFY.register((key, table, source, registries) -> {
            if (!source.isBuiltin()) {
                return;
            }
            if (!key.getValue().equals(PALE_OAK_LEAVES_TABLE)) {
                return;
            }
            table.pool(LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1.0f))
                    .conditionally(RandomChanceLootCondition.builder(0.15f).build())
                    .with(ItemEntry.builder(ModItems.PALE_BERRY).build()));
        });
    }
}
