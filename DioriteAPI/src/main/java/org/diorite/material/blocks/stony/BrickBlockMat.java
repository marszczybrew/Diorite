package org.diorite.material.blocks.stony;

import java.util.Map;

import org.diorite.utils.collections.maps.CaseInsensitiveMap;

import gnu.trove.map.TByteObjectMap;
import gnu.trove.map.hash.TByteObjectHashMap;

/**
 * Class representing block "BrickBlock" and all its subtypes.
 */
public class BrickBlockMat extends StonyMat
{
    /**
     * Sub-ids used by diorite/minecraft by default
     */
    public static final int USED_DATA_VALUES = 1;

    public static final BrickBlockMat BRICK_BLOCK = new BrickBlockMat();

    private static final Map<String, BrickBlockMat>    byName = new CaseInsensitiveMap<>(USED_DATA_VALUES, SMALL_LOAD_FACTOR);
    private static final TByteObjectMap<BrickBlockMat> byID   = new TByteObjectHashMap<>(USED_DATA_VALUES, SMALL_LOAD_FACTOR, Byte.MIN_VALUE);

    @SuppressWarnings("MagicNumber")
    protected BrickBlockMat()
    {
        super("BRICK_BLOCK", 45, "minecraft:brick_block", "BRICK_BLOCK", (byte) 0x00, 2, 30);
    }

    protected BrickBlockMat(final String enumName, final int id, final String minecraftId, final int maxStack, final String typeName, final byte type, final float hardness, final float blastResistance)
    {
        super(enumName, id, minecraftId, maxStack, typeName, type, hardness, blastResistance);
    }

    @Override
    public BrickBlockMat getType(final String name)
    {
        return getByEnumName(name);
    }

    @Override
    public BrickBlockMat getType(final int id)
    {
        return getByID(id);
    }

    /**
     * Returns one of BrickBlock sub-type based on sub-id, may return null
     *
     * @param id sub-type id
     *
     * @return sub-type of BrickBlock or null
     */
    public static BrickBlockMat getByID(final int id)
    {
        return byID.get((byte) id);
    }

    /**
     * Returns one of BrickBlock sub-type based on name (selected by diorite team), may return null
     * If block contains only one type, sub-name of it will be this same as name of material.
     *
     * @param name name of sub-type
     *
     * @return sub-type of BrickBlock or null
     */
    public static BrickBlockMat getByEnumName(final String name)
    {
        return byName.get(name);
    }

    /**
     * Register new sub-type, may replace existing sub-types.
     * Should be used only if you know what are you doing, it will not create fully usable material.
     *
     * @param element sub-type to register
     */
    public static void register(final BrickBlockMat element)
    {
        byID.put((byte) element.getType(), element);
        byName.put(element.getTypeName(), element);
    }

    @Override
    public BrickBlockMat[] types()
    {
        return BrickBlockMat.brickBlockTypes();
    }

    /**
     * @return array that contains all sub-types of this block.
     */
    public static BrickBlockMat[] brickBlockTypes()
    {
        return byID.values(new BrickBlockMat[byID.size()]);
    }

    static
    {
        BrickBlockMat.register(BRICK_BLOCK);
    }
}
