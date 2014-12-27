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
package com.github.antag99.aquarria.tests;

import org.junit.Assert;
import org.junit.Test;
import org.luaj.vm2.LuaValue;

import com.github.antag99.aquarria.event.Event;
import com.github.antag99.aquarria.event.EventListeners;
import com.github.antag99.aquarria.event.EventManager;
import com.github.antag99.aquarria.event.EventReceiver;

public class SimpleEventTests extends Assert {
	/**
	 * This test verifies that simple event dispatching works properly
	 */
	@Test
	public void testSimpleEvent() {
		EventManager eventManager = new EventManager();
		eventManager.registerListeners(new SimpleEventHandler());
		SimpleEvent simpleEvent = new SimpleEvent();
		eventManager.fire(simpleEvent);
		assertEquals("SimpleEvent not handled properly", 1, simpleEvent.counter);
		eventManager.fire(simpleEvent);
		assertEquals("SimpleEvent not handled properly", 2, simpleEvent.counter);
		eventManager.fire(simpleEvent);
		eventManager.fire(simpleEvent);
		assertEquals("SimpleEvent not handled properly", 4, simpleEvent.counter);
	}

	public static class SimpleEvent extends Event {
		public int counter;

		@Override
		public LuaValue[] pack() {
			return new LuaValue[0];
		}
	}

	public static class SimpleEventHandler implements EventListeners {
		@EventReceiver
		public void onSimpleEvent(SimpleEvent event) {
			event.counter++;
		}
	}
}
