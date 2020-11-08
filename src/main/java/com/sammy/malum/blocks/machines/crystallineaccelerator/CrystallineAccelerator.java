package com.sammy.malum.blocks.machines.crystallineaccelerator;

import com.sammy.malum.MalumHelper;
import com.sammy.malum.blocks.utility.multiblock.MultiblockBlock;
import com.sammy.malum.blocks.utility.multiblock.MultiblockStructure;
import net.hypherionmc.hypcore.api.ColoredLightBlock;
import net.hypherionmc.hypcore.api.ColoredLightEvent;
import net.hypherionmc.hypcore.api.ColoredLightManager;
import net.hypherionmc.hypcore.api.Light;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.ModList;


public class CrystallineAccelerator extends MultiblockBlock
{
    //region structure
    public static final MultiblockStructure structure = new MultiblockStructure(
            new BlockPos(0,1,0)
    );
    //endregion
    
    public CrystallineAccelerator(Properties properties)
    {
        super(properties);
        if (ModList.get().isLoaded("hypcore")) {
            ColoredLightManager.registerProvider(this, this::produceColoredLight);
        }
    }
    
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        worldIn.getTileEntity(pos);
        return super.getShape(state, worldIn, pos, context);
    }
    
    @Override
    public boolean hasTileEntity(final BlockState state)
    {
        return true;
    }
    
    @Override
    public TileEntity createTileEntity(final BlockState state, final IBlockReader world)
    {
        return new CrystallineAcceleratorTileEntity();
    }
    @Override
    public ActionResultType activateBlock(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit, BlockPos boundingBlockSource)
    {
        if (worldIn.getTileEntity(pos) instanceof CrystallineAcceleratorTileEntity)
        {
            CrystallineAcceleratorTileEntity furnaceTileEntity = (CrystallineAcceleratorTileEntity) worldIn.getTileEntity(pos);
    
            boolean success = MalumHelper.singleItemTEHandling(player, handIn, player.getHeldItemMainhand(), furnaceTileEntity.inventory, 0);
            if (success)
            {
                player.world.notifyBlockUpdate(pos, state, state, 3);
                player.swingArm(handIn);
                return ActionResultType.SUCCESS;
            }
        }
        return super.activateBlock(state, worldIn, pos, player, handIn, hit, boundingBlockSource);
    }
    public Light produceColoredLight(BlockPos pos, BlockState state) {
        return Light.builder().pos(pos).color(0.65f,0.2f,0.7f, 1.0f) .radius(8).build();
    }
}