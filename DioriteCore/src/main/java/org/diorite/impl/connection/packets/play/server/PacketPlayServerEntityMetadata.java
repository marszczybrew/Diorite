package org.diorite.impl.connection.packets.play.server;

import java.io.IOException;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.diorite.impl.connection.EnumProtocol;
import org.diorite.impl.connection.EnumProtocolDirection;
import org.diorite.impl.connection.packets.PacketClass;
import org.diorite.impl.connection.packets.PacketDataSerializer;
import org.diorite.impl.connection.packets.play.PacketPlayServerListener;
import org.diorite.impl.entity.EntityImpl;
import org.diorite.impl.entity.meta.entry.EntityMetadataEntry;
import org.diorite.entity.Entity;

@PacketClass(id = 0x1C, protocol = EnumProtocol.PLAY, direction = EnumProtocolDirection.CLIENTBOUND, size = 140)
public class PacketPlayServerEntityMetadata extends PacketPlayServer
{
    private int                              entityId; // ~5 bytes
    private Iterable<EntityMetadataEntry<?>> metadata; // ~should be not more than 128

    public PacketPlayServerEntityMetadata()
    {
    }

    public PacketPlayServerEntityMetadata(final int entityId, final Iterable<EntityMetadataEntry<?>> metadata)
    {
        this.entityId = entityId;
        this.metadata = metadata;
    }

    public PacketPlayServerEntityMetadata(final EntityImpl entity)
    {
        this.entityId = entity.getId();
        this.metadata = entity.getMetadata().getOutdatedEntries();
    }

    public PacketPlayServerEntityMetadata(final Entity entity, final Iterable<EntityMetadataEntry<?>> metadata)
    {
        this.entityId = entity.getId();
        this.metadata = metadata;
    }

    public PacketPlayServerEntityMetadata(final EntityImpl entity, final boolean forceAll)
    {
        this.entityId = entity.getId();
        this.metadata = forceAll ? entity.getMetadata().getEntries() : entity.getMetadata().getOutdatedEntries();
    }

    @Override
    public void readPacket(final PacketDataSerializer data) throws IOException
    {
        this.entityId = data.readVarInt();
        this.metadata = data.readEntityMetadata();
    }

    @Override
    public void writeFields(final PacketDataSerializer data) throws IOException
    {
        data.writeVarInt(this.entityId);
        data.writeEntityMetadata(this.metadata);
    }

    @Override
    public void handle(final PacketPlayServerListener listener)
    {
        listener.handle(this);
    }

    public int getEntityId()
    {
        return this.entityId;
    }

    public void setEntityId(final int entityId)
    {
        this.entityId = entityId;
    }

    public Iterable<EntityMetadataEntry<?>> getMetadata()
    {
        return this.metadata;
    }

    public void setMetadata(final Iterable<EntityMetadataEntry<?>> metadata)
    {
        this.metadata = metadata;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString()).append("entityId", this.entityId).append("metadata", this.metadata).toString();
    }
}
