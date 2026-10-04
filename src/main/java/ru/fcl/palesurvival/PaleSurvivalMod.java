package ru.fcl.palesurvival;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.fcl.palesurvival.block.ModBlocks;
import ru.fcl.palesurvival.item.ModItems;

public class PaleSurvivalMod implements ModInitializer {
    public static final String MOD_ID = "palesurvival";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.initialize();
        ModBlocks.initialize();
        ModLoot.inject();
        PlushAuraHandler.register();
        LOGGER.info("Pale Survival initialized — good luck in the Pale Garden!");
    }
}
