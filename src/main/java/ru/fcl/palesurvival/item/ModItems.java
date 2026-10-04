package ru.fcl.palesurvival.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import ru.fcl.palesurvival.PaleSurvivalMod;

import java.util.List;
import java.util.function.Function;

public class ModItems {
    public static final Item PALE_BERRY = register("pale_berry", Item::new,
            new Item.Settings().food(
                    new FoodComponent.Builder()
                            .nutrition(3)
                            .saturationModifier(0.5f)
                            .build(),
                    ConsumableComponents.food()
                            .consumeEffect(new ApplyEffectsConsumeEffect(
                                    List.of(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 200, 0)), 1.0f))
                            .build()));

    public static final Item EYEBLOSSOM_STEW = register("eyeblossom_stew", EyeblossomStewItem::new,
            new Item.Settings().maxCount(1).food(
                    new FoodComponent.Builder()
                            .nutrition(7)
                            .saturationModifier(0.8f)
                            .build(),
                    ConsumableComponents.food()
                            .consumeEffect(new ApplyEffectsConsumeEffect(
                                    List.of(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 400, 0)), 1.0f))
                            .build()));

    private static Item register(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(PaleSurvivalMod.MOD_ID, name));
        return Registry.register(Registries.ITEM, key, factory.apply(settings.registryKey(key)));
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register(entries -> {
                    entries.add(PALE_BERRY);
                    entries.add(EYEBLOSSOM_STEW);
                });
    }
}
