package org.diorite.impl.world.tick;

import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.diorite.impl.world.chunk.ChunkImpl;
import org.diorite.impl.world.chunk.ChunkManagerImpl;
import org.diorite.utils.math.pack.IntsToLong;
import org.diorite.world.World;

import gnu.trove.iterator.TLongIterator;

public class ChunksTickGroup implements TickGroupImpl
{
    private final Set<ChunkGroup> chunks;

    public ChunksTickGroup(final Set<ChunkGroup> chunks)
    {
        this.chunks = chunks;
    }

    @Override
    public void doTick(final int tps)
    {
        this.chunks.stream().filter(ChunkGroup::isLoaded).forEach(chunks -> {
            final ChunkManagerImpl cm = chunks.getWorld().getChunkManager();
            for (final TLongIterator it = chunks.getChunks().iterator(); it.hasNext(); )
            {
                final long key = it.next();
                final int x = IntsToLong.getA(key);
                final int z = IntsToLong.getB(key);
                final ChunkImpl chunk = cm.getChunk(x, z);
                if ((chunk == null) || ! chunk.isLoaded())
                {
                    return;
                }
                this.tickChunk(chunk, tps);
            }
        });
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString()).append("chunks", this.chunks).toString();
    }

    @Override
    public boolean removeWorld(final World world)
    {
        boolean result = false;
        for (final Iterator<ChunkGroup> it = this.chunks.iterator(); it.hasNext(); )
        {
            final ChunkGroup group = it.next();
            if (world.equals(group.getWorld()))
            {
                group.clear();
                it.remove();
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean isEmpty()
    {
        return this.chunks.isEmpty();
    }
}

