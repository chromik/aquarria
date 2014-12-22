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
package com.github.antag99.aquarria.tile;

import com.github.antag99.aquarria.util.Direction;
import com.github.antag99.aquarria.world.World;

public class TreeFrameStyle implements FrameStyle {
	@Override
	public String findFrame(World world, int x, int y) {
		boolean treeNorth = y + 1 < world.getHeight() && world.getTileType(x, y + 1) == TileType.tree && world.isAttached(x, y + 1, Direction.SOUTH);
		boolean treeSouth = y > 1 && world.getTileType(x, y - 1) == TileType.tree && world.isAttached(x, y, Direction.SOUTH);
		boolean treeEast = x + 1 < world.getWidth() && world.getTileType(x + 1, y) == TileType.tree && world.isAttached(x, y, Direction.EAST);
		boolean treeWest = x > 1 && world.getTileType(x - 1, y) == TileType.tree && world.isAttached(x, y, Direction.WEST);
		boolean solidSouth = y == 0 || world.getTileType(x, y - 1).isSolid();

		if (treeNorth && !treeSouth) {
			// Stub
			if (treeEast && !treeWest) {
				// West stub
				return "leftStub";
			} else if (treeWest && !treeEast) {
				// East stub
				return "rightStub";
			} else {
				// Plain old vanilla stub
				return "stub";
			}
		} else if (treeNorth && treeSouth) {
			// Trunk
			return "trunk";
		} else if (!treeNorth && treeSouth) {
			// Top
			return "top";
		} else {
			// Branch, or foot
			if (treeEast) {
				// Left
				if (solidSouth) {
					// Left 'foot'
					return "leftFoot";
				} else {
					// Left branch
					return "leftBranch";
				}
			} else {
				// Right
				if (solidSouth) {
					// Right 'foot'
					return "rightFoot";
				} else {
					// Right branch
					return "rightBranch";
				}
			}
		}
	}
}
