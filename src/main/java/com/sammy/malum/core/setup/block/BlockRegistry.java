package com.sammy.malum.core.setup.block;

import com.sammy.malum.MalumMod;
import com.sammy.malum.common.block.MalumSaplingBlock;
import com.sammy.malum.common.block.RunewoodLogBlock;
import com.sammy.malum.common.block.SapFilledLogBlock;
import com.sammy.malum.common.block.ether.EtherBlock;
import com.sammy.malum.common.block.ether.EtherBrazierBlock;
import com.sammy.malum.common.block.ether.EtherTorchBlock;
import com.sammy.malum.common.block.ether.WallEtherTorchBlock;
import com.sammy.malum.common.block.fusion_plate.FusionPlateComponentBlock;
import com.sammy.malum.common.block.fusion_plate.FusionPlateCoreBlock;
import com.sammy.malum.common.block.item_storage.*;
import com.sammy.malum.common.block.misc.MalumDirectionalBlock;
import com.sammy.malum.common.block.misc.MalumLeavesBlock;
import com.sammy.malum.common.block.misc.MalumLogBlock;
import com.sammy.malum.common.block.misc.sign.MalumStandingSignBlock;
import com.sammy.malum.common.block.misc.sign.MalumWallSignBlock;
import com.sammy.malum.common.block.obelisk.BrillianceObeliskCoreBlock;
import com.sammy.malum.common.block.obelisk.ObeliskComponentBlock;
import com.sammy.malum.common.block.obelisk.RunewoodObeliskCoreBlock;
import com.sammy.malum.common.block.spirit_altar.SpiritAltarBlock;
import com.sammy.malum.common.block.spirit_crucible.SpiritCrucibleComponentBlock;
import com.sammy.malum.common.block.spirit_crucible.SpiritCrucibleCoreBlock;
import com.sammy.malum.common.block.totem.TotemBaseBlock;
import com.sammy.malum.common.block.totem.TotemPoleBlock;
import com.sammy.malum.common.blockentity.EtherBlockEntity;
import com.sammy.malum.core.helper.DataHelper;
import com.sammy.malum.core.setup.SoundRegistry;
import com.sammy.malum.core.setup.content.SpiritTypeRegistry;
import com.sammy.malum.core.setup.item.ItemRegistry;
import com.sammy.malum.core.setup.worldgen.FeatureRegistry;
import com.sammy.malum.core.systems.block.SimpleBlockProperties;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static com.sammy.malum.MalumMod.MODID;
import static net.minecraft.world.level.block.PressurePlateBlock.Sensitivity.EVERYTHING;
import static net.minecraft.world.level.block.PressurePlateBlock.Sensitivity.MOBS;


@SuppressWarnings("unused")
public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public static SimpleBlockProperties TAINTED_ROCK_PROPERTIES() {
        return new SimpleBlockProperties(Material.STONE, MaterialColor.STONE).needsPickaxe().sound(SoundRegistry.TAINTED_ROCK).requiresCorrectToolForDrops().strength(1.25F, 9.0F);
    }

    public static SimpleBlockProperties TWISTED_ROCK_PROPERTIES() {
        return new SimpleBlockProperties(Material.STONE, MaterialColor.STONE).needsPickaxe().requiresCorrectToolForDrops().sound(SoundRegistry.TWISTED_ROCK).strength(1.25F, 9.0F);
    }

    public static SimpleBlockProperties SOULSTONE_PROPERTIES() {
        return new SimpleBlockProperties(Material.STONE, MaterialColor.NETHER).needsPickaxe().requiresCorrectToolForDrops().strength(5.0F, 3.0F).sound(SoundRegistry.SOULSTONE);
    }

    public static SimpleBlockProperties DEEPSLATE_SOULSTONE_PROPERTIES() {
        return new SimpleBlockProperties(Material.STONE, MaterialColor.NETHER).needsPickaxe().requiresCorrectToolForDrops().strength(7.0F, 6.0F).sound(SoundRegistry.DEEPSLATE_SOULSTONE);
    }

    public static SimpleBlockProperties BLAZE_QUARTZ_ORE_PROPERTIES() {
        return new SimpleBlockProperties(Material.STONE, MaterialColor.NETHER).needsPickaxe().requiresCorrectToolForDrops().strength(3.0F, 3.0F).sound(SoundRegistry.BLAZING_QUARTZ_ORE);
    }

    public static SimpleBlockProperties BLAZE_QUARTZ_PROPERTIES() {
        return new SimpleBlockProperties(Material.STONE, MaterialColor.COLOR_RED).needsPickaxe().requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundRegistry.BLAZING_QUARTZ_BLOCK);
    }

    public static SimpleBlockProperties ARCANE_CHARCOAL_PROPERTIES() {
        return new SimpleBlockProperties(Material.STONE, MaterialColor.COLOR_BLACK).needsPickaxe().requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundRegistry.ARCANE_CHARCOAL_BLOCK);
    }

    public static SimpleBlockProperties RUNEWOOD_PROPERTIES() {
        return new SimpleBlockProperties(Material.WOOD, MaterialColor.COLOR_YELLOW).needsAxe().sound(SoundType.WOOD).strength(1.75F, 4.0F);
    }

    public static SimpleBlockProperties RUNEWOOD_PLANTS_PROPERTIES() {
        return new SimpleBlockProperties(Material.PLANT, MaterialColor.COLOR_YELLOW).noCollission().noOcclusion().sound(SoundType.GRASS).instabreak();
    }

    public static SimpleBlockProperties SOULWOOD_PROPERTIES() {
        return new SimpleBlockProperties(Material.WOOD, MaterialColor.COLOR_PURPLE).needsAxe().sound(SoundType.WOOD).strength(1.75F, 4.0F);
    }

    public static SimpleBlockProperties SOULWOOD_PLANTS_PROPERTIES() {
        return new SimpleBlockProperties(Material.PLANT, MaterialColor.COLOR_PURPLE).noCollission().noOcclusion().sound(SoundType.GRASS).instabreak();
    }

    public static SimpleBlockProperties LEAVES_PROPERTIES() {
        return new SimpleBlockProperties(Material.LEAVES, MaterialColor.COLOR_YELLOW).needsHoe().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(Blocks::ocelotOrParrot).isSuffocating(Blocks::never).isViewBlocking(Blocks::never);
    }

    public static SimpleBlockProperties ETHER_BLOCK_PROPERTIES() {
        return new SimpleBlockProperties(Material.WOOL, MaterialColor.COLOR_YELLOW).sound(SoundRegistry.ETHER).noCollission().instabreak().lightLevel((b) -> 14);
    }

    public static SimpleBlockProperties HALLOWED_GOLD_PROPERTIES() {
        return new SimpleBlockProperties(Material.METAL, MaterialColor.COLOR_YELLOW).requiresCorrectToolForDrops().needsPickaxe().sound(SoundRegistry.HALLOWED_GOLD).noOcclusion().strength(2F, 16.0F);
    }

    public static SimpleBlockProperties SOUL_STAINED_STEEL_BLOCK_PROPERTIES() {
        return new SimpleBlockProperties(Material.METAL, MaterialColor.COLOR_PURPLE).requiresCorrectToolForDrops().needsPickaxe().sound(SoundRegistry.SOUL_STAINED_STEEL).strength(5f, 64.0f);
    }

    public static SimpleBlockProperties SPIRIT_JAR_PROPERTIES() {
        return new SimpleBlockProperties(Material.GLASS, MaterialColor.COLOR_BLUE).strength(0.5f, 64f).sound(SoundRegistry.HALLOWED_GOLD).noOcclusion();
    }

    public static SimpleBlockProperties SOUL_VIAL_PROPERTIES() {
        return new SimpleBlockProperties(Material.GLASS, MaterialColor.COLOR_BLUE).strength(0.75f, 64f).sound(SoundRegistry.SOUL_STAINED_STEEL).noOcclusion();
    }

    public static SimpleBlockProperties STONE_PROPERTIES() {
        return new SimpleBlockProperties(Material.STONE, MaterialColor.STONE).needsPickaxe().requiresCorrectToolForDrops().strength(3f, 3f);
    }

    public static SimpleBlockProperties DEEPSLATE_PROPERTIES() {
        return new SimpleBlockProperties(Material.STONE, MaterialColor.DEEPSLATE).needsPickaxe().requiresCorrectToolForDrops().strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE);
    }
    //region useful blocks
    public static final RegistryObject<Block> SPIRIT_ALTAR = BLOCKS.register("spirit_altar", () -> new SpiritAltarBlock(RUNEWOOD_PROPERTIES().ignoreBlockStateDatagen().noOcclusion()));
    public static final RegistryObject<Block> SPIRIT_JAR = BLOCKS.register("spirit_jar", () -> new SpiritJarBlock(SPIRIT_JAR_PROPERTIES().ignoreBlockStateDatagen().noOcclusion()));
    public static final RegistryObject<Block> SOUL_VIAL = BLOCKS.register("soul_vial", () -> new SoulVialBlock(SOUL_VIAL_PROPERTIES().ignoreBlockStateDatagen().noOcclusion()));

    public static final RegistryObject<Block> RUNEWOOD_OBELISK = BLOCKS.register("runewood_obelisk", () -> new RunewoodObeliskCoreBlock(RUNEWOOD_PROPERTIES().ignoreBlockStateDatagen().noOcclusion()));
    public static final RegistryObject<Block> RUNEWOOD_OBELISK_COMPONENT = BLOCKS.register("runewood_obelisk_component", () -> new ObeliskComponentBlock(RUNEWOOD_PROPERTIES().ignoreBlockStateDatagen().lootFrom(RUNEWOOD_OBELISK).noOcclusion(), ItemRegistry.RUNEWOOD_OBELISK));

    public static final RegistryObject<Block> BRILLIANT_OBELISK = BLOCKS.register("brilliant_obelisk", () -> new BrillianceObeliskCoreBlock(RUNEWOOD_PROPERTIES().ignoreBlockStateDatagen().noOcclusion()));
    public static final RegistryObject<Block> BRILLIANT_OBELISK_COMPONENT = BLOCKS.register("brilliant_obelisk_component", () -> new ObeliskComponentBlock(RUNEWOOD_PROPERTIES().ignoreBlockStateDatagen().lootFrom(BRILLIANT_OBELISK).noOcclusion(), ItemRegistry.BRILLIANT_OBELISK));

    public static final RegistryObject<Block> SPIRIT_CRUCIBLE = BLOCKS.register("spirit_crucible", () -> new SpiritCrucibleCoreBlock(TAINTED_ROCK_PROPERTIES().ignoreBlockStateDatagen().noOcclusion()));
    public static final RegistryObject<Block> SPIRIT_CRUCIBLE_COMPONENT = BLOCKS.register("spirit_crucible_component", () -> new SpiritCrucibleComponentBlock(TAINTED_ROCK_PROPERTIES().ignoreBlockStateDatagen().lootFrom(SPIRIT_CRUCIBLE).noOcclusion()));

    public static final RegistryObject<Block> SOULWOOD_PLINTH = BLOCKS.register("soulwood_plinth", () -> new PlinthCoreBlock(SOULWOOD_PROPERTIES().ignoreBlockStateDatagen().noOcclusion()));
    public static final RegistryObject<Block> SOULWOOD_PLINTH_COMPONENT = BLOCKS.register("soulwood_plinth_component", () -> new PlinthComponentBlock(SOULWOOD_PROPERTIES().ignoreBlockStateDatagen().lootFrom(SOULWOOD_PLINTH).noOcclusion(), ItemRegistry.SOULWOOD_PLINTH));

    public static final RegistryObject<Block> SOULWOOD_FUSION_PLATE = BLOCKS.register("soulwood_fusion_plate", () -> new FusionPlateCoreBlock(SOULWOOD_PROPERTIES().ignoreBlockStateDatagen().noOcclusion()));
    public static final RegistryObject<Block> SOULWOOD_FUSION_PLATE_COMPONENT = BLOCKS.register("soulwood_fusion_plate_component", () -> new FusionPlateComponentBlock(SOULWOOD_PROPERTIES().ignoreBlockStateDatagen().lootFrom(SOULWOOD_FUSION_PLATE).noOcclusion(), ItemRegistry.SOULWOOD_FUSION_PLATE));

    public static final RegistryObject<Block> RUNEWOOD_TOTEM_BASE = BLOCKS.register("runewood_totem_base", () -> new TotemBaseBlock(RUNEWOOD_PROPERTIES().noOcclusion(), false));
    public static final RegistryObject<Block> RUNEWOOD_TOTEM_POLE = BLOCKS.register("runewood_totem_pole", () -> new TotemPoleBlock(RUNEWOOD_PROPERTIES().noOcclusion(), BlockRegistry.RUNEWOOD_LOG, false));

    public static final RegistryObject<Block> SOULWOOD_TOTEM_BASE = BLOCKS.register("soulwood_totem_base", () -> new TotemBaseBlock(RUNEWOOD_PROPERTIES().noOcclusion(), true));
    public static final RegistryObject<Block> SOULWOOD_TOTEM_POLE = BLOCKS.register("soulwood_totem_pole", () -> new TotemPoleBlock(RUNEWOOD_PROPERTIES().noOcclusion(), BlockRegistry.SOULWOOD_LOG, true));
    //endregion

    //region tainted rock
    public static final RegistryObject<Block> TAINTED_ROCK = BLOCKS.register("tainted_rock", () -> new Block(TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> SMOOTH_TAINTED_ROCK = BLOCKS.register("smooth_tainted_rock", () -> new Block(TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> POLISHED_TAINTED_ROCK = BLOCKS.register("polished_tainted_rock", () -> new Block(TAINTED_ROCK_PROPERTIES()));

    public static final RegistryObject<Block> TAINTED_ROCK_BRICKS = BLOCKS.register("tainted_rock_bricks", () -> new Block(TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> CRACKED_TAINTED_ROCK_BRICKS = BLOCKS.register("cracked_tainted_rock_bricks", () -> new Block(TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> SMALL_TAINTED_ROCK_BRICKS = BLOCKS.register("small_tainted_rock_bricks", () -> new Block(TAINTED_ROCK_PROPERTIES()));

    public static final RegistryObject<Block> TAINTED_ROCK_TILES = BLOCKS.register("tainted_rock_tiles", () -> new Block(TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> CRACKED_TAINTED_ROCK_TILES = BLOCKS.register("cracked_tainted_rock_tiles", () -> new Block(TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> CRACKED_SMALL_TAINTED_ROCK_BRICKS = BLOCKS.register("cracked_small_tainted_rock_bricks", () -> new Block(TAINTED_ROCK_PROPERTIES()));

    public static final RegistryObject<Block> TAINTED_ROCK_PILLAR = BLOCKS.register("tainted_rock_pillar", () -> new RotatedPillarBlock(TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> TAINTED_ROCK_PILLAR_CAP = BLOCKS.register("tainted_rock_pillar_cap", () -> new MalumDirectionalBlock(TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> TAINTED_ROCK_COLUMN = BLOCKS.register("tainted_rock_column", () -> new RotatedPillarBlock(TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> TAINTED_ROCK_COLUMN_CAP = BLOCKS.register("tainted_rock_column_cap", () -> new MalumDirectionalBlock(TAINTED_ROCK_PROPERTIES()));

    public static final RegistryObject<Block> CUT_TAINTED_ROCK = BLOCKS.register("cut_tainted_rock", () -> new Block(TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> CHISELED_TAINTED_ROCK = BLOCKS.register("chiseled_tainted_rock", () -> new Block(TAINTED_ROCK_PROPERTIES()));

    public static final RegistryObject<Block> TAINTED_ROCK_SLAB = BLOCKS.register("tainted_rock_slab", () -> new SlabBlock(TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> SMOOTH_TAINTED_ROCK_SLAB = BLOCKS.register("smooth_tainted_rock_slab", () -> new SlabBlock(TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> POLISHED_TAINTED_ROCK_SLAB = BLOCKS.register("polished_tainted_rock_slab", () -> new SlabBlock(TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> TAINTED_ROCK_BRICKS_SLAB = BLOCKS.register("tainted_rock_bricks_slab", () -> new SlabBlock(TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> CRACKED_TAINTED_ROCK_BRICKS_SLAB = BLOCKS.register("cracked_tainted_rock_bricks_slab", () -> new SlabBlock(TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> SMALL_TAINTED_ROCK_BRICKS_SLAB = BLOCKS.register("small_tainted_rock_bricks_slab", () -> new SlabBlock(TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> TAINTED_ROCK_TILES_SLAB = BLOCKS.register("tainted_rock_tiles_slab", () -> new SlabBlock(TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> CRACKED_TAINTED_ROCK_TILES_SLAB = BLOCKS.register("cracked_tainted_rock_tiles_slab", () -> new SlabBlock(TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> CRACKED_SMALL_TAINTED_ROCK_BRICKS_SLAB = BLOCKS.register("cracked_small_tainted_rock_bricks_slab", () -> new SlabBlock(TAINTED_ROCK_PROPERTIES()));

    public static final RegistryObject<Block> TAINTED_ROCK_STAIRS = BLOCKS.register("tainted_rock_stairs", () -> new StairBlock(() -> TAINTED_ROCK.get().defaultBlockState(), TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> SMOOTH_TAINTED_ROCK_STAIRS = BLOCKS.register("smooth_tainted_rock_stairs", () -> new StairBlock(() -> TAINTED_ROCK.get().defaultBlockState(), TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> POLISHED_TAINTED_ROCK_STAIRS = BLOCKS.register("polished_tainted_rock_stairs", () -> new StairBlock(() -> TAINTED_ROCK.get().defaultBlockState(), TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> TAINTED_ROCK_BRICKS_STAIRS = BLOCKS.register("tainted_rock_bricks_stairs", () -> new StairBlock(() -> TAINTED_ROCK.get().defaultBlockState(), TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> CRACKED_TAINTED_ROCK_BRICKS_STAIRS = BLOCKS.register("cracked_tainted_rock_bricks_stairs", () -> new StairBlock(() -> TAINTED_ROCK.get().defaultBlockState(), TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> SMALL_TAINTED_ROCK_BRICKS_STAIRS = BLOCKS.register("small_tainted_rock_bricks_stairs", () -> new StairBlock(() -> TAINTED_ROCK.get().defaultBlockState(), TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> TAINTED_ROCK_TILES_STAIRS = BLOCKS.register("tainted_rock_tiles_stairs", () -> new StairBlock(() -> TAINTED_ROCK.get().defaultBlockState(), TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> CRACKED_TAINTED_ROCK_TILES_STAIRS = BLOCKS.register("cracked_tainted_rock_tiles_stairs", () -> new StairBlock(() -> TAINTED_ROCK.get().defaultBlockState(), TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> CRACKED_SMALL_TAINTED_ROCK_BRICKS_STAIRS = BLOCKS.register("cracked_small_tainted_rock_bricks_stairs", () -> new StairBlock(() -> TAINTED_ROCK.get().defaultBlockState(), TAINTED_ROCK_PROPERTIES()));

    public static final RegistryObject<Block> TAINTED_ROCK_PRESSURE_PLATE = BLOCKS.register("tainted_rock_pressure_plate", () -> new PressurePlateBlock(MOBS, TAINTED_ROCK_PROPERTIES()));

    public static final RegistryObject<Block> TAINTED_ROCK_WALL = BLOCKS.register("tainted_rock_wall", () -> new WallBlock(TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> TAINTED_ROCK_BRICKS_WALL = BLOCKS.register("tainted_rock_bricks_wall", () -> new WallBlock(TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> SMALL_TAINTED_ROCK_BRICKS_WALL = BLOCKS.register("small_tainted_rock_bricks_wall", () -> new WallBlock(TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> CRACKED_TAINTED_ROCK_BRICKS_WALL = BLOCKS.register("cracked_tainted_rock_bricks_wall", () -> new WallBlock(TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> TAINTED_ROCK_TILES_WALL = BLOCKS.register("tainted_rock_tiles_wall", () -> new WallBlock(TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> CRACKED_SMALL_TAINTED_ROCK_BRICKS_WALL = BLOCKS.register("cracked_small_tainted_rock_bricks_wall", () -> new WallBlock(TAINTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> CRACKED_TAINTED_ROCK_TILES_WALL = BLOCKS.register("cracked_tainted_rock_tiles_wall", () -> new WallBlock(TAINTED_ROCK_PROPERTIES()));

    public static final RegistryObject<Block> TAINTED_ROCK_ITEM_STAND = BLOCKS.register("tainted_rock_item_stand", () -> new ItemStandBlock(TAINTED_ROCK_PROPERTIES().noOcclusion()));
    public static final RegistryObject<Block> TAINTED_ROCK_ITEM_PEDESTAL = BLOCKS.register("tainted_rock_item_pedestal", () -> new ItemPedestalBlock(TAINTED_ROCK_PROPERTIES().noOcclusion()));
    //endregion

    //region twisted rock
    public static final RegistryObject<Block> TWISTED_ROCK = BLOCKS.register("twisted_rock", () -> new Block(TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> SMOOTH_TWISTED_ROCK = BLOCKS.register("smooth_twisted_rock", () -> new Block(TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> POLISHED_TWISTED_ROCK = BLOCKS.register("polished_twisted_rock", () -> new Block(TWISTED_ROCK_PROPERTIES()));

    public static final RegistryObject<Block> TWISTED_ROCK_BRICKS = BLOCKS.register("twisted_rock_bricks", () -> new Block(TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> CRACKED_TWISTED_ROCK_BRICKS = BLOCKS.register("cracked_twisted_rock_bricks", () -> new Block(TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> SMALL_TWISTED_ROCK_BRICKS = BLOCKS.register("small_twisted_rock_bricks", () -> new Block(TWISTED_ROCK_PROPERTIES()));

    public static final RegistryObject<Block> TWISTED_ROCK_TILES = BLOCKS.register("twisted_rock_tiles", () -> new Block(TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> CRACKED_TWISTED_ROCK_TILES = BLOCKS.register("cracked_twisted_rock_tiles", () -> new Block(TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> CRACKED_SMALL_TWISTED_ROCK_BRICKS = BLOCKS.register("cracked_small_twisted_rock_bricks", () -> new Block(TWISTED_ROCK_PROPERTIES()));

    public static final RegistryObject<Block> TWISTED_ROCK_PILLAR = BLOCKS.register("twisted_rock_pillar", () -> new RotatedPillarBlock(TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> TWISTED_ROCK_PILLAR_CAP = BLOCKS.register("twisted_rock_pillar_cap", () -> new MalumDirectionalBlock(TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> TWISTED_ROCK_COLUMN = BLOCKS.register("twisted_rock_column", () -> new RotatedPillarBlock(TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> TWISTED_ROCK_COLUMN_CAP = BLOCKS.register("twisted_rock_column_cap", () -> new MalumDirectionalBlock(TWISTED_ROCK_PROPERTIES()));

    public static final RegistryObject<Block> CUT_TWISTED_ROCK = BLOCKS.register("cut_twisted_rock", () -> new Block(TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> CHISELED_TWISTED_ROCK = BLOCKS.register("chiseled_twisted_rock", () -> new Block(TWISTED_ROCK_PROPERTIES()));

    public static final RegistryObject<Block> TWISTED_ROCK_SLAB = BLOCKS.register("twisted_rock_slab", () -> new SlabBlock(TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> SMOOTH_TWISTED_ROCK_SLAB = BLOCKS.register("smooth_twisted_rock_slab", () -> new SlabBlock(TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> POLISHED_TWISTED_ROCK_SLAB = BLOCKS.register("polished_twisted_rock_slab", () -> new SlabBlock(TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> TWISTED_ROCK_BRICKS_SLAB = BLOCKS.register("twisted_rock_bricks_slab", () -> new SlabBlock(TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> CRACKED_TWISTED_ROCK_BRICKS_SLAB = BLOCKS.register("cracked_twisted_rock_bricks_slab", () -> new SlabBlock(TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> SMALL_TWISTED_ROCK_BRICKS_SLAB = BLOCKS.register("small_twisted_rock_bricks_slab", () -> new SlabBlock(TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> TWISTED_ROCK_TILES_SLAB = BLOCKS.register("twisted_rock_tiles_slab", () -> new SlabBlock(TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> CRACKED_TWISTED_ROCK_TILES_SLAB = BLOCKS.register("cracked_twisted_rock_tiles_slab", () -> new SlabBlock(TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> CRACKED_SMALL_TWISTED_ROCK_BRICKS_SLAB = BLOCKS.register("cracked_small_twisted_rock_bricks_slab", () -> new SlabBlock(TWISTED_ROCK_PROPERTIES()));

    public static final RegistryObject<Block> TWISTED_ROCK_STAIRS = BLOCKS.register("twisted_rock_stairs", () -> new StairBlock(() -> TWISTED_ROCK.get().defaultBlockState(), TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> SMOOTH_TWISTED_ROCK_STAIRS = BLOCKS.register("smooth_twisted_rock_stairs", () -> new StairBlock(() -> TWISTED_ROCK.get().defaultBlockState(), TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> POLISHED_TWISTED_ROCK_STAIRS = BLOCKS.register("polished_twisted_rock_stairs", () -> new StairBlock(() -> TWISTED_ROCK.get().defaultBlockState(), TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> TWISTED_ROCK_BRICKS_STAIRS = BLOCKS.register("twisted_rock_bricks_stairs", () -> new StairBlock(() -> TWISTED_ROCK.get().defaultBlockState(), TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> CRACKED_TWISTED_ROCK_BRICKS_STAIRS = BLOCKS.register("cracked_twisted_rock_bricks_stairs", () -> new StairBlock(() -> TWISTED_ROCK.get().defaultBlockState(), TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> SMALL_TWISTED_ROCK_BRICKS_STAIRS = BLOCKS.register("small_twisted_rock_bricks_stairs", () -> new StairBlock(() -> TWISTED_ROCK.get().defaultBlockState(), TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> TWISTED_ROCK_TILES_STAIRS = BLOCKS.register("twisted_rock_tiles_stairs", () -> new StairBlock(() -> TWISTED_ROCK.get().defaultBlockState(), TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> CRACKED_TWISTED_ROCK_TILES_STAIRS = BLOCKS.register("cracked_twisted_rock_tiles_stairs", () -> new StairBlock(() -> TWISTED_ROCK.get().defaultBlockState(), TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> CRACKED_SMALL_TWISTED_ROCK_BRICKS_STAIRS = BLOCKS.register("cracked_small_twisted_rock_bricks_stairs", () -> new StairBlock(() -> TWISTED_ROCK.get().defaultBlockState(), TWISTED_ROCK_PROPERTIES()));

    public static final RegistryObject<Block> TWISTED_ROCK_PRESSURE_PLATE = BLOCKS.register("twisted_rock_pressure_plate", () -> new PressurePlateBlock(MOBS, TWISTED_ROCK_PROPERTIES()));

    public static final RegistryObject<Block> TWISTED_ROCK_WALL = BLOCKS.register("twisted_rock_wall", () -> new WallBlock(TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> TWISTED_ROCK_BRICKS_WALL = BLOCKS.register("twisted_rock_bricks_wall", () -> new WallBlock(TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> SMALL_TWISTED_ROCK_BRICKS_WALL = BLOCKS.register("small_twisted_rock_bricks_wall", () -> new WallBlock(TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> CRACKED_TWISTED_ROCK_BRICKS_WALL = BLOCKS.register("cracked_twisted_rock_bricks_wall", () -> new WallBlock(TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> TWISTED_ROCK_TILES_WALL = BLOCKS.register("twisted_rock_tiles_wall", () -> new WallBlock(TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> CRACKED_SMALL_TWISTED_ROCK_BRICKS_WALL = BLOCKS.register("cracked_small_twisted_rock_bricks_wall", () -> new WallBlock(TWISTED_ROCK_PROPERTIES()));
    public static final RegistryObject<Block> CRACKED_TWISTED_ROCK_TILES_WALL = BLOCKS.register("cracked_twisted_rock_tiles_wall", () -> new WallBlock(TWISTED_ROCK_PROPERTIES()));

    public static final RegistryObject<Block> TWISTED_ROCK_ITEM_STAND = BLOCKS.register("twisted_rock_item_stand", () -> new ItemStandBlock(TWISTED_ROCK_PROPERTIES().noOcclusion()));
    public static final RegistryObject<Block> TWISTED_ROCK_ITEM_PEDESTAL = BLOCKS.register("twisted_rock_item_pedestal", () -> new ItemPedestalBlock(TWISTED_ROCK_PROPERTIES().noOcclusion()));
    //endregion

    //region runewood
    public static final RegistryObject<Block> RUNEWOOD_SAPLING = BLOCKS.register("runewood_sapling", () -> new MalumSaplingBlock(RUNEWOOD_PLANTS_PROPERTIES().randomTicks(), FeatureRegistry.RUNEWOOD_TREE));
    public static final RegistryObject<Block> RUNEWOOD_LEAVES = BLOCKS.register("runewood_leaves", () -> new MalumLeavesBlock(LEAVES_PROPERTIES().ignoreLootDatagen(), new Color(175, 65, 48), new Color(251, 193, 76)));

    public static final RegistryObject<Block> STRIPPED_RUNEWOOD_LOG = BLOCKS.register("stripped_runewood_log", () -> new RotatedPillarBlock(RUNEWOOD_PROPERTIES()));
    public static final RegistryObject<Block> RUNEWOOD_LOG = BLOCKS.register("runewood_log", () -> new RunewoodLogBlock(RUNEWOOD_PROPERTIES(), STRIPPED_RUNEWOOD_LOG, false));
    public static final RegistryObject<Block> STRIPPED_RUNEWOOD = BLOCKS.register("stripped_runewood", () -> new RotatedPillarBlock(RUNEWOOD_PROPERTIES()));
    public static final RegistryObject<Block> RUNEWOOD = BLOCKS.register("runewood", () -> new MalumLogBlock(RUNEWOOD_PROPERTIES(), STRIPPED_RUNEWOOD));

    public static final RegistryObject<Block> REVEALED_RUNEWOOD_LOG = BLOCKS.register("revealed_runewood_log", () -> new SapFilledLogBlock(RUNEWOOD_PROPERTIES(), STRIPPED_RUNEWOOD_LOG, ItemRegistry.HOLY_SAP, SpiritTypeRegistry.INFERNAL_SPIRIT_COLOR));
    public static final RegistryObject<Block> EXPOSED_RUNEWOOD_LOG = BLOCKS.register("exposed_runewood_log", () -> new MalumLogBlock(RUNEWOOD_PROPERTIES(), REVEALED_RUNEWOOD_LOG));

    public static final RegistryObject<Block> RUNEWOOD_PLANKS = BLOCKS.register("runewood_planks", () -> new Block(RUNEWOOD_PROPERTIES()));
    public static final RegistryObject<Block> RUNEWOOD_PLANKS_SLAB = BLOCKS.register("runewood_planks_slab", () -> new SlabBlock(RUNEWOOD_PROPERTIES()));
    public static final RegistryObject<Block> RUNEWOOD_PLANKS_STAIRS = BLOCKS.register("runewood_planks_stairs", () -> new StairBlock(() -> RUNEWOOD_PLANKS.get().defaultBlockState(), RUNEWOOD_PROPERTIES()));

    public static final RegistryObject<Block> VERTICAL_RUNEWOOD_PLANKS = BLOCKS.register("vertical_runewood_planks", () -> new Block(RUNEWOOD_PROPERTIES()));
    public static final RegistryObject<Block> VERTICAL_RUNEWOOD_PLANKS_SLAB = BLOCKS.register("vertical_runewood_planks_slab", () -> new SlabBlock(RUNEWOOD_PROPERTIES()));
    public static final RegistryObject<Block> VERTICAL_RUNEWOOD_PLANKS_STAIRS = BLOCKS.register("vertical_runewood_planks_stairs", () -> new StairBlock(() -> VERTICAL_RUNEWOOD_PLANKS.get().defaultBlockState(), RUNEWOOD_PROPERTIES()));

    public static final RegistryObject<Block> RUNEWOOD_PANEL = BLOCKS.register("runewood_panel", () -> new Block(RUNEWOOD_PROPERTIES()));
    public static final RegistryObject<Block> RUNEWOOD_PANEL_SLAB = BLOCKS.register("runewood_panel_slab", () -> new SlabBlock(RUNEWOOD_PROPERTIES()));
    public static final RegistryObject<Block> RUNEWOOD_PANEL_STAIRS = BLOCKS.register("runewood_panel_stairs", () -> new StairBlock(() -> RUNEWOOD_PANEL.get().defaultBlockState(), RUNEWOOD_PROPERTIES()));

    public static final RegistryObject<Block> RUNEWOOD_TILES = BLOCKS.register("runewood_tiles", () -> new Block(RUNEWOOD_PROPERTIES()));
    public static final RegistryObject<Block> RUNEWOOD_TILES_SLAB = BLOCKS.register("runewood_tiles_slab", () -> new SlabBlock(RUNEWOOD_PROPERTIES()));
    public static final RegistryObject<Block> RUNEWOOD_TILES_STAIRS = BLOCKS.register("runewood_tiles_stairs", () -> new StairBlock(() -> RUNEWOOD_TILES.get().defaultBlockState(), RUNEWOOD_PROPERTIES()));

    public static final RegistryObject<Block> CUT_RUNEWOOD_PLANKS = BLOCKS.register("cut_runewood_planks", () -> new Block(RUNEWOOD_PROPERTIES()));
    public static final RegistryObject<Block> RUNEWOOD_BEAM = BLOCKS.register("runewood_beam", () -> new RotatedPillarBlock(RUNEWOOD_PROPERTIES()));

    public static final RegistryObject<Block> RUNEWOOD_DOOR = BLOCKS.register("runewood_door", () -> new DoorBlock(RUNEWOOD_PROPERTIES().noOcclusion()));
    public static final RegistryObject<Block> RUNEWOOD_TRAPDOOR = BLOCKS.register("runewood_trapdoor", () -> new TrapDoorBlock(RUNEWOOD_PROPERTIES().noOcclusion()));
    public static final RegistryObject<Block> SOLID_RUNEWOOD_TRAPDOOR = BLOCKS.register("solid_runewood_trapdoor", () -> new TrapDoorBlock(RUNEWOOD_PROPERTIES().noOcclusion()));

    public static final RegistryObject<Block> RUNEWOOD_PLANKS_BUTTON = BLOCKS.register("runewood_planks_button", () -> new WoodButtonBlock(RUNEWOOD_PROPERTIES()));
    public static final RegistryObject<Block> RUNEWOOD_PLANKS_PRESSURE_PLATE = BLOCKS.register("runewood_planks_pressure_plate", () -> new PressurePlateBlock(EVERYTHING, RUNEWOOD_PROPERTIES()));

    public static final RegistryObject<Block> RUNEWOOD_PLANKS_FENCE = BLOCKS.register("runewood_planks_fence", () -> new FenceBlock(RUNEWOOD_PROPERTIES()));
    public static final RegistryObject<Block> RUNEWOOD_PLANKS_FENCE_GATE = BLOCKS.register("runewood_planks_fence_gate", () -> new FenceGateBlock(RUNEWOOD_PROPERTIES()));

    public static final RegistryObject<Block> RUNEWOOD_ITEM_STAND = BLOCKS.register("runewood_item_stand", () -> new ItemStandBlock(RUNEWOOD_PROPERTIES().noOcclusion()));
    public static final RegistryObject<Block> RUNEWOOD_ITEM_PEDESTAL = BLOCKS.register("runewood_item_pedestal", () -> new WoodItemPedestalBlock(RUNEWOOD_PROPERTIES().noOcclusion()));

    public static final RegistryObject<Block> RUNEWOOD_SIGN = BLOCKS.register("runewood_sign", () -> new MalumStandingSignBlock(RUNEWOOD_PROPERTIES().noOcclusion().noCollission(), WoodTypeRegistry.RUNEWOOD));
    public static final RegistryObject<Block> RUNEWOOD_WALL_SIGN = BLOCKS.register("runewood_wall_sign", () -> new MalumWallSignBlock(RUNEWOOD_PROPERTIES().noOcclusion().noCollission(), WoodTypeRegistry.RUNEWOOD));
    //endregion

    //region soulwood
    public static final RegistryObject<Block> SOULWOOD_SAPLING = BLOCKS.register("soulwood_sapling", () -> new MalumSaplingBlock(SOULWOOD_PLANTS_PROPERTIES().randomTicks(), FeatureRegistry.SOULWOOD_TREE));
    public static final RegistryObject<Block> SOULWOOD_LEAVES = BLOCKS.register("soulwood_leaves", () -> new MalumLeavesBlock(LEAVES_PROPERTIES().ignoreLootDatagen(), new Color(152, 6, 45), new Color(224, 30, 214)));

    public static final RegistryObject<Block> STRIPPED_SOULWOOD_LOG = BLOCKS.register("stripped_soulwood_log", () -> new RotatedPillarBlock(SOULWOOD_PROPERTIES()));
    public static final RegistryObject<Block> SOULWOOD_LOG = BLOCKS.register("soulwood_log", () -> new RunewoodLogBlock(SOULWOOD_PROPERTIES(), STRIPPED_SOULWOOD_LOG, true));
    public static final RegistryObject<Block> STRIPPED_SOULWOOD = BLOCKS.register("stripped_soulwood", () -> new RotatedPillarBlock(SOULWOOD_PROPERTIES()));
    public static final RegistryObject<Block> SOULWOOD = BLOCKS.register("soulwood", () -> new MalumLogBlock(SOULWOOD_PROPERTIES(), STRIPPED_SOULWOOD));

    public static final RegistryObject<Block> REVEALED_SOULWOOD_LOG = BLOCKS.register("revealed_soulwood_log", () -> new SapFilledLogBlock(SOULWOOD_PROPERTIES(), STRIPPED_SOULWOOD_LOG, ItemRegistry.UNHOLY_SAP, new Color(214, 46, 83)));
    public static final RegistryObject<Block> EXPOSED_SOULWOOD_LOG = BLOCKS.register("exposed_soulwood_log", () -> new MalumLogBlock(SOULWOOD_PROPERTIES(), REVEALED_SOULWOOD_LOG));

    public static final RegistryObject<Block> SOULWOOD_PLANKS = BLOCKS.register("soulwood_planks", () -> new Block(SOULWOOD_PROPERTIES()));
    public static final RegistryObject<Block> SOULWOOD_PLANKS_SLAB = BLOCKS.register("soulwood_planks_slab", () -> new SlabBlock(SOULWOOD_PROPERTIES()));
    public static final RegistryObject<Block> SOULWOOD_PLANKS_STAIRS = BLOCKS.register("soulwood_planks_stairs", () -> new StairBlock(() -> SOULWOOD_PLANKS.get().defaultBlockState(), SOULWOOD_PROPERTIES()));

    public static final RegistryObject<Block> VERTICAL_SOULWOOD_PLANKS = BLOCKS.register("vertical_soulwood_planks", () -> new Block(SOULWOOD_PROPERTIES()));
    public static final RegistryObject<Block> VERTICAL_SOULWOOD_PLANKS_SLAB = BLOCKS.register("vertical_soulwood_planks_slab", () -> new SlabBlock(SOULWOOD_PROPERTIES()));
    public static final RegistryObject<Block> VERTICAL_SOULWOOD_PLANKS_STAIRS = BLOCKS.register("vertical_soulwood_planks_stairs", () -> new StairBlock(() -> VERTICAL_SOULWOOD_PLANKS.get().defaultBlockState(), SOULWOOD_PROPERTIES()));

    public static final RegistryObject<Block> SOULWOOD_PANEL = BLOCKS.register("soulwood_panel", () -> new Block(SOULWOOD_PROPERTIES()));
    public static final RegistryObject<Block> SOULWOOD_PANEL_SLAB = BLOCKS.register("soulwood_panel_slab", () -> new SlabBlock(SOULWOOD_PROPERTIES()));
    public static final RegistryObject<Block> SOULWOOD_PANEL_STAIRS = BLOCKS.register("soulwood_panel_stairs", () -> new StairBlock(() -> SOULWOOD_PANEL.get().defaultBlockState(), SOULWOOD_PROPERTIES()));

    public static final RegistryObject<Block> SOULWOOD_TILES = BLOCKS.register("soulwood_tiles", () -> new Block(SOULWOOD_PROPERTIES()));
    public static final RegistryObject<Block> SOULWOOD_TILES_SLAB = BLOCKS.register("soulwood_tiles_slab", () -> new SlabBlock(SOULWOOD_PROPERTIES()));
    public static final RegistryObject<Block> SOULWOOD_TILES_STAIRS = BLOCKS.register("soulwood_tiles_stairs", () -> new StairBlock(() -> SOULWOOD_TILES.get().defaultBlockState(), SOULWOOD_PROPERTIES()));

    public static final RegistryObject<Block> CUT_SOULWOOD_PLANKS = BLOCKS.register("cut_soulwood_planks", () -> new Block(SOULWOOD_PROPERTIES()));
    public static final RegistryObject<Block> SOULWOOD_BEAM = BLOCKS.register("soulwood_beam", () -> new RotatedPillarBlock(SOULWOOD_PROPERTIES()));

    public static final RegistryObject<Block> SOULWOOD_DOOR = BLOCKS.register("soulwood_door", () -> new DoorBlock(SOULWOOD_PROPERTIES().noOcclusion()));
    public static final RegistryObject<Block> SOULWOOD_TRAPDOOR = BLOCKS.register("soulwood_trapdoor", () -> new TrapDoorBlock(SOULWOOD_PROPERTIES().noOcclusion()));
    public static final RegistryObject<Block> SOLID_SOULWOOD_TRAPDOOR = BLOCKS.register("solid_soulwood_trapdoor", () -> new TrapDoorBlock(SOULWOOD_PROPERTIES().noOcclusion()));

    public static final RegistryObject<Block> SOULWOOD_PLANKS_BUTTON = BLOCKS.register("soulwood_planks_button", () -> new WoodButtonBlock(SOULWOOD_PROPERTIES()));
    public static final RegistryObject<Block> SOULWOOD_PLANKS_PRESSURE_PLATE = BLOCKS.register("soulwood_planks_pressure_plate", () -> new PressurePlateBlock(EVERYTHING, SOULWOOD_PROPERTIES()));

    public static final RegistryObject<Block> SOULWOOD_PLANKS_FENCE = BLOCKS.register("soulwood_planks_fence", () -> new FenceBlock(SOULWOOD_PROPERTIES()));
    public static final RegistryObject<Block> SOULWOOD_PLANKS_FENCE_GATE = BLOCKS.register("soulwood_planks_fence_gate", () -> new FenceGateBlock(SOULWOOD_PROPERTIES()));

    public static final RegistryObject<Block> SOULWOOD_ITEM_STAND = BLOCKS.register("soulwood_item_stand", () -> new ItemStandBlock(SOULWOOD_PROPERTIES().noOcclusion()));
    public static final RegistryObject<Block> SOULWOOD_ITEM_PEDESTAL = BLOCKS.register("soulwood_item_pedestal", () -> new WoodItemPedestalBlock(SOULWOOD_PROPERTIES().noOcclusion()));

    public static final RegistryObject<Block> SOULWOOD_SIGN = BLOCKS.register("soulwood_sign", () -> new MalumStandingSignBlock(SOULWOOD_PROPERTIES().noOcclusion().noCollission(), WoodTypeRegistry.SOULWOOD));
    public static final RegistryObject<Block> SOULWOOD_WALL_SIGN = BLOCKS.register("soulwood_wall_sign", () -> new MalumWallSignBlock(SOULWOOD_PROPERTIES().noOcclusion().noCollission(), WoodTypeRegistry.SOULWOOD));
    //endregion

    //region ether
    public static final RegistryObject<Block> ETHER_TORCH = BLOCKS.register("ether_torch", () -> new EtherTorchBlock(RUNEWOOD_PROPERTIES().noCollission().instabreak().lightLevel((b) -> 14)));
    public static final RegistryObject<Block> WALL_ETHER_TORCH = BLOCKS.register("wall_ether_torch", () -> new WallEtherTorchBlock(RUNEWOOD_PROPERTIES().noCollission().instabreak().lightLevel((b) -> 14).lootFrom(ETHER_TORCH)));
    public static final RegistryObject<Block> ETHER = BLOCKS.register("ether", () -> new EtherBlock(ETHER_BLOCK_PROPERTIES()));
    public static final RegistryObject<Block> TAINTED_ETHER_BRAZIER = BLOCKS.register("tainted_ether_brazier", () -> new EtherBrazierBlock(TAINTED_ROCK_PROPERTIES().lightLevel((b) -> 14).noOcclusion()));
    public static final RegistryObject<Block> TWISTED_ETHER_BRAZIER = BLOCKS.register("twisted_ether_brazier", () -> new EtherBrazierBlock(TWISTED_ROCK_PROPERTIES().lightLevel((b) -> 14).noOcclusion()));

    public static final RegistryObject<Block> IRIDESCENT_ETHER_TORCH = BLOCKS.register("iridescent_ether_torch", () -> new EtherTorchBlock(RUNEWOOD_PROPERTIES().noCollission().instabreak().lightLevel((b) -> 14)));
    public static final RegistryObject<Block> IRIDESCENT_WALL_ETHER_TORCH = BLOCKS.register("iridescent_wall_ether_torch", () -> new WallEtherTorchBlock(RUNEWOOD_PROPERTIES().noCollission().instabreak().lightLevel((b) -> 14).lootFrom(IRIDESCENT_ETHER_TORCH)));
    public static final RegistryObject<Block> IRIDESCENT_ETHER = BLOCKS.register("iridescent_ether", () -> new EtherBlock(ETHER_BLOCK_PROPERTIES()));
    public static final RegistryObject<Block> TAINTED_IRIDESCENT_ETHER_BRAZIER = BLOCKS.register("tainted_iridescent_ether_brazier", () -> new EtherBrazierBlock(TAINTED_ROCK_PROPERTIES().lightLevel((b) -> 14).noOcclusion()));
    public static final RegistryObject<Block> TWISTED_IRIDESCENT_ETHER_BRAZIER = BLOCKS.register("twisted_iridescent_ether_brazier", () -> new EtherBrazierBlock(TWISTED_ROCK_PROPERTIES().lightLevel((b) -> 14).noOcclusion()));
    //endregion

    public static final RegistryObject<Block> BLOCK_OF_ARCANE_CHARCOAL = BLOCKS.register("block_of_arcane_charcoal", () -> new Block(ARCANE_CHARCOAL_PROPERTIES()));

    public static final RegistryObject<Block> BLAZING_QUARTZ_ORE = BLOCKS.register("blazing_quartz_ore", () -> new OreBlock(BLAZE_QUARTZ_ORE_PROPERTIES().ignoreBlockStateDatagen().ignoreLootDatagen(), UniformInt.of(3, 4)));
    public static final RegistryObject<Block> BLOCK_OF_BLAZING_QUARTZ = BLOCKS.register("block_of_blazing_quartz", () -> new Block(BLAZE_QUARTZ_PROPERTIES()));

    public static final RegistryObject<Block> BRILLIANT_STONE = BLOCKS.register("brilliant_stone", () -> new OreBlock(STONE_PROPERTIES().ignoreBlockStateDatagen().ignoreLootDatagen(), UniformInt.of(7, 9)));
    public static final RegistryObject<Block> BRILLIANT_DEEPSLATE = BLOCKS.register("brilliant_deepslate", () -> new OreBlock(DEEPSLATE_PROPERTIES().ignoreBlockStateDatagen().ignoreLootDatagen(), UniformInt.of(8, 13)));
    public static final RegistryObject<Block> BLOCK_OF_BRILLIANCE = BLOCKS.register("block_of_brilliance", () -> new Block(STONE_PROPERTIES()));

    public static final RegistryObject<Block> SOULSTONE_ORE = BLOCKS.register("soulstone_ore", () -> new Block(SOULSTONE_PROPERTIES().ignoreLootDatagen()));
    public static final RegistryObject<Block> DEEPSLATE_SOULSTONE_ORE = BLOCKS.register("deepslate_soulstone_ore", () -> new Block(DEEPSLATE_SOULSTONE_PROPERTIES().ignoreLootDatagen().strength(6f, 4f)));
    public static final RegistryObject<Block> BLOCK_OF_SOULSTONE = BLOCKS.register("block_of_soulstone", () -> new Block(SOULSTONE_PROPERTIES()));

    public static final RegistryObject<Block> BLOCK_OF_HALLOWED_GOLD = BLOCKS.register("block_of_hallowed_gold", () -> new Block(HALLOWED_GOLD_PROPERTIES()));
    public static final RegistryObject<Block> BLOCK_OF_SOUL_STAINED_STEEL = BLOCKS.register("block_of_soul_stained_steel", () -> new Block(SOUL_STAINED_STEEL_BLOCK_PROPERTIES()));


    @Mod.EventBusSubscriber(modid= MalumMod.MODID, value= Dist.CLIENT, bus= Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientOnly {
        @SubscribeEvent
        public static void setBlockColors(ColorHandlerEvent.Block event) {
            BlockColors blockColors = event.getBlockColors();
            Set<RegistryObject<Block>> blocks = new HashSet<>(BLOCKS.getEntries());

            DataHelper.getAll(blocks, b -> b.get() instanceof EtherBlock).forEach(b -> blockColors.register((s, l, p, c) -> {
                BlockEntity blockEntity = l.getBlockEntity(p);
                if (blockEntity instanceof EtherBlockEntity etherBlockEntity) {
                    if (etherBlockEntity.firstColor != null) {
                        return c == 0 ? etherBlockEntity.firstColor.getRGB() : -1;
                    }
                }
                return -1;
            }, b.get()));

            DataHelper.takeAll(blocks, b -> b.get() instanceof MalumLeavesBlock).forEach(b -> blockColors.register((s, l, p, c) -> {
                float i = s.getValue(MalumLeavesBlock.COLOR);
                float max = MalumLeavesBlock.COLOR.getPossibleValues().size();
                MalumLeavesBlock malumLeavesBlock = (MalumLeavesBlock) s.getBlock();
                int red = (int) Mth.lerp(i / max, malumLeavesBlock.minColor.getRed(), malumLeavesBlock.maxColor.getRed());
                int green = (int) Mth.lerp(i / max, malumLeavesBlock.minColor.getGreen(), malumLeavesBlock.maxColor.getGreen());
                int blue = (int) Mth.lerp(i / max, malumLeavesBlock.minColor.getBlue(), malumLeavesBlock.maxColor.getBlue());
                return red << 16 | green << 8 | blue;
            }, b.get()));
        }

        @SubscribeEvent
        public static void setRenderLayers(FMLClientSetupEvent event) {
            Set<RegistryObject<Block>> blocks = new HashSet<>(BLOCKS.getEntries());
            DataHelper.takeAll(blocks, b -> b.get() instanceof EtherBlock).forEach(ClientOnly::setCutout);
            DataHelper.takeAll(blocks, b -> b.get() instanceof TrapDoorBlock).forEach(ClientOnly::setCutout);
            DataHelper.takeAll(blocks, b -> b.get() instanceof DoorBlock).forEach(ClientOnly::setCutout);
            DataHelper.takeAll(blocks, b -> b.get() instanceof SaplingBlock).forEach(ClientOnly::setCutout);
            DataHelper.takeAll(blocks, b -> b.get() instanceof LeavesBlock).forEach(ClientOnly::setCutout);
            DataHelper.takeAll(blocks, b -> b.get() instanceof BushBlock).forEach(ClientOnly::setCutout);
            DataHelper.takeAll(blocks, b -> b.get() instanceof LanternBlock).forEach(ClientOnly::setCutout);
            DataHelper.takeAll(blocks, b -> b.get() instanceof ItemStandBlock).forEach(ClientOnly::setCutout);
            DataHelper.takeAll(blocks, b -> b.get() instanceof ItemPedestalBlock).forEach(ClientOnly::setCutout);
            DataHelper.takeAll(blocks, b -> b.get() instanceof TotemBaseBlock).forEach(ClientOnly::setCutout);
            DataHelper.takeAll(blocks, b -> b.get() instanceof TotemPoleBlock).forEach(ClientOnly::setCutout);
            DataHelper.takeAll(blocks, b -> b.get() instanceof SpiritJarBlock).forEach(ClientOnly::setCutout);
            DataHelper.takeAll(blocks, b -> b.get() instanceof SoulVialBlock).forEach(ClientOnly::setCutout);
            DataHelper.takeAll(blocks, b -> b.get() instanceof SpiritAltarBlock).forEach(ClientOnly::setCutout);
            setCutout(BlockRegistry.BLAZING_QUARTZ_ORE);
            setCutout(BlockRegistry.BRILLIANT_STONE);
            setCutout(BlockRegistry.BRILLIANT_DEEPSLATE);
        }

        public static void setCutout(RegistryObject<Block> b) {
            ItemBlockRenderTypes.setRenderLayer(b.get(), RenderType.cutoutMipped());
        }
    }
}