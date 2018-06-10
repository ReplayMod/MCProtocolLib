package org.spacehq.mc.protocol.packet.ingame.server.entity;

import org.spacehq.mc.protocol.data.game.values.MagicValues;
import org.spacehq.mc.protocol.data.game.values.entity.Effect;
import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;

import java.io.IOException;

public class ServerEntityRemoveEffectPacket implements Packet {

    private int entityId;
    private Effect effect;
    private byte effectId;

    @SuppressWarnings("unused")
    private ServerEntityRemoveEffectPacket() {
    }

    public ServerEntityRemoveEffectPacket(int entityId, Effect effect) {
        this.entityId = entityId;
        this.effect = effect;
    }

    public ServerEntityRemoveEffectPacket(int entityId, byte effectId) {
        this.entityId = entityId;
        this.effectId = effectId;
    }

    public int getEntityId() {
        return this.entityId;
    }

    public Effect getEffect() {
        return this.effect;
    }

    public byte getEffectId() {
        return this.effect == null ? this.effectId : MagicValues.value(Integer.class, this.effect).byteValue();
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.effectId = in.readByte();
        try {
            this.effect = MagicValues.key(Effect.class, this.effectId);
        } catch (IllegalArgumentException e) {
            this.effect = null;
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        out.writeByte(this.getEffectId());
    }

    @Override
    public boolean isPriority() {
        return false;
    }

}
