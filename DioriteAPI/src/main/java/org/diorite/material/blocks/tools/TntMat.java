package org.diorite.material.blocks.tools;

import java.util.Map;

import org.diorite.material.BlockMaterialData;
import org.diorite.utils.collections.maps.CaseInsensitiveMap;

import gnu.trove.map.TByteObjectMap;
import gnu.trove.map.hash.TByteObjectHashMap;

/**
 * Class representing block "Tnt" and all its subtypes.
 */
public class TntMat extends BlockMaterialData
{
    /**
     * Sub-ids used by diorite/minecraft by default
     */
    public static final int USED_DATA_VALUES = 1;

    public static final TntMat TNT = new TntMat();

    private static final Map<String, TntMat>    byName = new CaseInsensitiveMap<>(USED_DATA_VALUES, SMALL_LOAD_FACTOR);
    private static final TByteObjectMap<TntMat> byID   = new TByteObjectHashMap<>(USED_DATA_VALUES, SMALL_LOAD_FACTOR, Byte.MIN_VALUE);

    @SuppressWarnings("MagicNumber")
    protected TntMat()
    {
        super("TNT", 46, "minecraft:tnt", "TNT", (byte) 0x00, 0, 0);
    }

    protected TntMat(final String enumName, final int id, final String minecraftId, final int maxStack, final String typeName, final byte type, final float hardness, final float blastResistance)
    {
        super(enumName, id, minecraftId, maxStack, typeName, type, hardness, blastResistance);
    }

    @Override
    public TntMat getType(final String name)
    {
        return getByEnumName(name);
    }

    @Override
    public TntMat getType(final int id)
    {
        return getByID(id);
    }

    /**
     * Returns one of Tnt sub-type based on sub-id, may return null
     *
     * @param id sub-type id
     *
     * @return sub-type of Tnt or null
     */
    public static TntMat getByID(final int id)
    {
        return byID.get((byte) id);
    }

    /**
     * Returns one of Tnt sub-type based on name (selected by diorite team), may return null
     * If block contains only one type, sub-name of it will be this same as name of material.
     *
     * @param name name of sub-type
     *
     * @return sub-type of Tnt or null
     */
    public static TntMat getByEnumName(final String name)
    {
        return byName.get(name);
    }

    /**
     * Register new sub-type, may replace existing sub-types.
     * Should be used only if you know what are you doing, it will not create fully usable material.
     *
     * @param element sub-type to register
     */
    public static void register(final TntMat element)
    {
        byID.put((byte) element.getType(), element);
        byName.put(element.getTypeName(), element);
    }

    @Override
    public TntMat[] types()
    {
        return TntMat.tntTypes();
    }

    /**
     * @return array that contains all sub-types of this block.
     */
    public static TntMat[] tntTypes()
    {
        return byID.values(new TntMat[byID.size()]);
    }

    static
    {
        TntMat.register(TNT);
    }
}
