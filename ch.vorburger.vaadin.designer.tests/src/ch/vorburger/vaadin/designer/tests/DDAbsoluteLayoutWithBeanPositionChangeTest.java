/**
 *  Copyright 2012 Michael Vorburger (http://www.vorburger.ch)
 *  
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/*******************************************************************************
 * Copyright (c) 2012 Michael Vorburger (http://www.vorburger.ch).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package ch.vorburger.vaadin.designer.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ch.vorburger.vaadin.beans.dragdroplayouts.AbsoluteNotifyingComponentPosition;
import ch.vorburger.vaadin.beans.dragdroplayouts.DDAbsoluteLayoutWithBeanPositionChange;

import com.vaadin.ui.AbsoluteLayout.ComponentPosition;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;

/**
 * Tests for DDAbsoluteLayoutWithBeanPositionChange.
 * 
 * @author Michael Vorburger
 */
public class DDAbsoluteLayoutWithBeanPositionChangeTest {

	@Test
	public void testAddComponentComponentAbsoluteNotifyingComponentPosition() {
		final Component c = new TextField("Star Trek");
		final DDAbsoluteLayoutWithBeanPositionChange layout = new DDAbsoluteLayoutWithBeanPositionChange();
		final AbsoluteNotifyingComponentPosition coolPos = new AbsoluteNotifyingComponentPosition();
		
		coolPos.setLeft(12);
		coolPos.setTop(34);
		assertEquals("top:34px;left:12px;", coolPos.getCSSString());
		
		layout.addComponent(c, coolPos);
		ComponentPosition classicPos = layout.getPosition(c);
		assertEquals("top:34.0px;left:12.0px;", classicPos.getCSSString());

		coolPos.setTop(78);
		assertEquals("top:78.0px;left:12.0px;", classicPos.getCSSString());

// This won't work - and doesn't have to, because in the Designer we capture such changes via the NotifyingDropHandler,
// and there is normally no use case where the classicPos gets modified by code directly outside of UI D&D. 
//		classicPos.setLeftValue(56f);
//		assertEquals(new Integer(56), coolPos.getLeft());
	}
}
