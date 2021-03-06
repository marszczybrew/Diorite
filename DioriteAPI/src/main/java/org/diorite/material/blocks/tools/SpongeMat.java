package org.diorite.material.blocks.tools;

import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.diorite.material.BlockMaterialData;
import org.diorite.utils.collections.maps.CaseInsensitiveMap;

import gnu.trove.map.TByteObjectMap;
import gnu.trove.map.hash.TByteObjectHashMap;

/**
 * Class representing block "Sponge" and all its subtypes.
 */
public class SpongeMat extends BlockMaterialData
{
    /**
     * Sub-ids used by diorite/minecraft by default
     */
    public static final int USED_DATA_VALUES = 2;

    public static final SpongeMat SPONGE     = new SpongeMat();
    public static final SpongeMat SPONGE_WET = new SpongeMat(true);

    private static final Map<String, SpongeMat>    byName = new CaseInsensitiveMap<>(USED_DATA_VALUES, SMALL_LOAD_FACTOR);
    private static final TByteObjectMap<SpongeMat> byID   = new TByteObjectHashMap<>(USED_DATA_VALUES, SMALL_LOAD_FACTOR, Byte.MIN_VALUE);

    protected final boolean isWet;

    @SuppressWarnings("MagicNumber")
    protected SpongeMat()
    {
        super("SPONGE", 19, "minecraft:sponge", "DRY", (byte) 0x00, 0.6f, 3);
        this.isWet = false;
    }

    protected SpongeMat(final boolean isWet)
    {
        super(SPONGE.name(), SPONGE.ordinal(), SPONGE.getMinecraftId(), isWet ? "WEY" : "DRY", (byte) (isWet ? 1 : 0), SPONGE.getHardness(), SPONGE.getBlastResistance());
        this.isWet = isWet;
    }

    protected SpongeMat(final String enumName, final int id, final String minecraftId, final int maxStack, final String typeName, final byte type, final boolean isWet, final float hardness, final float blastResistance)
    {
        super(enumName, id, minecraftId, maxStack, typeName, type, hardness, blastResistance);
        this.isWet = isWet;
    }

    @Override
    public SpongeMat getType(final String name)
    {
        return getByEnumName(name);
    }

    @Override
    public SpongeMat getType(final int id)
    {
        return getByID(id);
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString()).append("isWet", this.isWet).toString();
    }

    public boolean isWet()
    {
        return this.isWet;
    }

    public SpongeMat getWet(final boolean wet)
    {
        return getByID(wet ? 1 : 0);
    }

    /**
     * Returns one of Sponge sub-type based on sub-id, may return null
     *
     * @param id sub-type id
     *
     * @return sub-type of Sponge or null
     */
    public static SpongeMat getByID(final int id)
    {
        return byID.get((byte) id);
    }

    /**
     * Returns one of Sponge sub-type based on name (selected by diorite team), may return null
     * If block contains only one type, sub-name of it will be this same as name of material.
     *
     * @param name name of sub-type
     *
     * @return sub-type of Sponge or null
     */
    public static SpongeMat getByEnumName(final String name)
    {
        return byName.get(name);
    }

    /**
     * Register new sub-type, may replace existing sub-types.
     * Should be used only if you know what are you doing, it will not create fully usable material.
     *
     * @param element sub-type to register
     */
    public static void register(final SpongeMat element)
    {
        byID.put((byte) element.getType(), element);
        byName.put(element.getTypeName(), element);
    }

    @Override
    public SpongeMat[] types()
    {
        return SpongeMat.spongeTypes();
    }

    /**
     * @return array that contains all sub-types of this block.
     */
    public static SpongeMat[] spongeTypes()
    {
        return byID.values(new SpongeMat[byID.size()]);
    }

    static
    {
        SpongeMat.register(SPONGE);
        SpongeMat.register(SPONGE_WET);
    }
}
