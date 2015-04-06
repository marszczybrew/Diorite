package org.diorite.material.blocks.stony;

import java.util.Map;

import org.diorite.cfg.magic.MagicNumbers;
import org.diorite.utils.collections.SimpleStringHashMap;

import gnu.trove.map.TByteObjectMap;
import gnu.trove.map.hash.TByteObjectHashMap;

public class BrickBlock extends Stony
{
    public static final byte  USED_DATA_VALUES = 1;
    public static final float BLAST_RESISTANCE = MagicNumbers.MATERIAL__BRICK_BLOCK__BLAST_RESISTANCE;
    public static final float HARDNESS         = MagicNumbers.MATERIAL__BRICK_BLOCK__HARDNESS;

    public static final BrickBlock BRICK_BLOCK = new BrickBlock();

    private static final Map<String, BrickBlock>    byName = new SimpleStringHashMap<>(USED_DATA_VALUES, SMALL_LOAD_FACTOR);
    private static final TByteObjectMap<BrickBlock> byID   = new TByteObjectHashMap<>(USED_DATA_VALUES, SMALL_LOAD_FACTOR);

    @SuppressWarnings("MagicNumber")
    protected BrickBlock()
    {
        super("BRICK_BLOCK", 45, "minecraft:brick_block", "BRICK_BLOCK", (byte) 0x00);
    }

    public BrickBlock(final String enumName, final int type)
    {
        super(BRICK_BLOCK.name(), BRICK_BLOCK.getId(), BRICK_BLOCK.getMinecraftId(), enumName, (byte) type);
    }

    public BrickBlock(final int maxStack, final String typeName, final byte type)
    {
        super(BRICK_BLOCK.name(), BRICK_BLOCK.getId(), BRICK_BLOCK.getMinecraftId(), maxStack, typeName, type);
    }

    @Override
    public float getBlastResistance()
    {
        return BLAST_RESISTANCE;
    }

    @Override
    public float getHardness()
    {
        return HARDNESS;
    }

    @Override
    public BrickBlock getType(final String name)
    {
        return getByEnumName(name);
    }

    @Override
    public BrickBlock getType(final int id)
    {
        return getByID(id);
    }

    public static BrickBlock getByID(final int id)
    {
        return byID.get((byte) id);
    }

    public static BrickBlock getByEnumName(final String name)
    {
        return byName.get(name);
    }

    public static void register(final BrickBlock element)
    {
        byID.put(element.getType(), element);
        byName.put(element.name(), element);
    }

    static
    {
        BrickBlock.register(BRICK_BLOCK);
    }
}
