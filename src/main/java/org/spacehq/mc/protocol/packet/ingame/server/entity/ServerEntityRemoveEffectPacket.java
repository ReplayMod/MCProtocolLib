package org.spacehq.mc.protocol.packet.ingame.server.entity;

import java.io.IOException;

import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;

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
		return this.effect == null ? this.effectId : (byte) (this.effect.ordinal() + 1);
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.entityId = in.readInt();
		this.effectId = in.readByte();
		if (this.effectId > 0 && this.effectId <= Effect.values().length) {
			this.effect = Effect.values()[this.effectId - 1];
		}
	}

	@Override
	public void write(NetOutput out) throws IOException {
		out.writeInt(this.entityId);
		out.writeByte(this.getEffectId());
	}
	
	@Override
	public boolean isPriority() {
		return false;
	}
	
	public static enum Effect {
		SPEED,
		SLOWNESS,
		DIG_SPEED,
		DIG_SLOWNESS,
		DAMAGE_BOOST,
		HEAL,
		DAMAGE,
		ENHANCED_JUMP,
		CONFUSION,
		REGENERATION,
		RESISTANCE,
		FIRE_RESISTANCE,
		WATER_BREATHING,
		INVISIBILITY,
		BLINDNESS,
		NIGHT_VISION,
		HUNGER,
		WEAKNESS,
		POISON,
		WITHER_EFFECT,
		HEALTH_BOOST,
		ABSORPTION,
		SATURATION;
	}

}
