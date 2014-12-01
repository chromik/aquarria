/*******************************************************************************
 * Copyright (c) 2014, Anton Gustafsson
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * 
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 * 
 * * Neither the name of Aquarria nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/
package com.github.antag99.aquarria.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.github.antag99.aquarria.AbstractType;

public class EntityType extends AbstractType {
	public static Array<EntityType> getEntityTypes() {
		return AbstractType.getTypes(EntityType.class);
	}

	public static EntityType forName(String internalName) {
		return AbstractType.forName(EntityType.class, internalName);
	}

	public static final EntityType player = new EntityType("entities/player.json");
	public static final EntityType item = new EntityType("entities/item.json");

	private String displayName;
	private boolean solid;
	private float weight;
	private int maxHealth;

	private float defaultWidth;
	private float defaultHeight;

	public EntityType(JsonValue properties) {
		super(properties.getString("internalName"));

		displayName = properties.getString("displayName", "");
		solid = properties.getBoolean("solid", true);
		weight = properties.getFloat("weight", 1f);
		maxHealth = properties.getInt("maxHealth", 0);

		defaultWidth = properties.getFloat("width");
		defaultHeight = properties.getFloat("height");
	}

	public EntityType(String path) {
		this(new JsonReader().parse(Gdx.files.internal(path)));
	}

	public String getDisplayName() {
		return displayName;
	}

	public boolean isSolid() {
		return solid;
	}

	public float getWeight() {
		return weight;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public float getDefaultWidth() {
		return defaultWidth;
	}

	public float getDefaultHeight() {
		return defaultHeight;
	}

	@Override
	protected Class<? extends AbstractType> getTypeClass() {
		return EntityType.class;
	}
}
