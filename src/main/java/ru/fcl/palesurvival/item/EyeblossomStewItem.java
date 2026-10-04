package ru.fcl.palesurvival.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class EyeblossomStewItem extends Item {
    public EyeblossomStewItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack result = super.finishUsing(stack, world, user);
        ItemStack bowl = new ItemStack(Items.BOWL);
        if (result.isEmpty()) {
            return bowl;
        }
        if (user instanceof PlayerEntity player && !player.getAbilities().creativeMode) {
            if (!player.getInventory().insertStack(bowl)) {
                player.dropItem(bowl, false);
            }
        }
        return result;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.palesurvival.eyeblossom_stew").formatted(Formatting.GRAY));
    }
}
