package org.diorite.material.items.block;

import java.util.Map;

import org.diorite.material.ItemMaterialData;
import org.diorite.material.PlaceableMat;
import org.diorite.utils.collections.maps.CaseInsensitiveMap;

import gnu.trove.map.TShortObjectMap;
import gnu.trove.map.hash.TShortObjectHashMap;

@SuppressWarnings("MagicNumber")
public class BedMat extends ItemMaterialData implements PlaceableMat
{
    /**
     * Sub-ids used by diorite/minecraft by default
     */
    public static final int USED_DATA_VALUES = 1;

    public static final BedMat BED = new BedMat();

    private static final Map<String, BedMat>     byName = new CaseInsensitiveMap<>(USED_DATA_VALUES, SMALL_LOAD_FACTOR);
    private static final TShortObjectMap<BedMat> byID   = new TShortObjectHashMap<>(USED_DATA_VALUES, SMALL_LOAD_FACTOR, Short.MIN_VALUE);

    protected BedMat()
    {
        super("BED", 355, "minecraft:bed", "BED", (short) 0x00);
    }

    protected BedMat(final String enumName, final int id, final String minecraftId, final String typeName, final short type)
    {
        super(enumName, id, minecraftId, typeName, type);
    }

    protected BedMat(final String enumName, final int id, final String minecraftId, final int maxStack, final String typeName, final short type)
    {
        super(enumName, id, minecraftId, maxStack, typeName, type);
    }

    @Override
    public BedMat getType(final int type)
    {
        return getByID(type);
    }

    @Override
    public BedMat getType(final String type)
    {
        return getByEnumName(type);
    }

    /**
     * Returns one of Bed sub-type based on sub-id, may return null
     *
     * @param id sub-type id
     *
     * @return sub-type of Bed or null
     */
    public static BedMat getByID(final int id)
    {
        return byID.get((short) id);
    }

    /**
     * Returns one of Bed sub-type based on name (selected by diorite team), may return null
     * If block contains only one type, sub-name of it will be this same as name of material.
     *
     * @param name name of sub-type
     *
     * @return sub-type of Bed or null
     */
    public static BedMat getByEnumName(final String name)
    {
        return byName.get(name);
    }

    /**
     * Register new sub-type, may replace existing sub-types.
     * Should be used only if you know what are you doing, it will not create fully usable material.
     *
     * @param element sub-type to register
     */
    public static void register(final BedMat element)
    {
        byID.put(element.getType(), element);
        byName.put(element.getTypeName(), element);
    }

    @Override
    public BedMat[] types()
    {
        return BedMat.bedTypes();
    }

    /**
     * @return array that contains all sub-types of this block.
     */
    public static BedMat[] bedTypes()
    {
        return byID.values(new BedMat[byID.size()]);
    }

    static
    {
        BedMat.register(BED);
    }
}

