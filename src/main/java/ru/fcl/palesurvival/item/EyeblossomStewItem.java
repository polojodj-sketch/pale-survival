package ru.fcl.palesurvival.item;

import net.minecraft.item.ItemStack;
import net.minecraft.item.StewItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class EyeblossomStewItem extends StewItem {
    public EyeblossomStewItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.palesurvival.eyeblossom_stew").formatted(Formatting.GRAY));
    }
}
