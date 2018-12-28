package dev.vianpyro.paperworld.entities.statics;

import dev.vianpyro.paperworld.Handler;
import dev.vianpyro.paperworld.entities.Entity;

public abstract class StaticEntity extends Entity {

	public StaticEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}
}
