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

package ch.vorburger.vaadin.beans.dragdroplayouts;

import static com.vaadin.terminal.Sizeable.UNITS_PIXELS;
import static com.vaadin.terminal.Sizeable.UNIT_SYMBOLS;

import java.io.Serializable;

import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.AbsoluteLayout.ComponentPosition;

import ch.vorburger.beans.AbstractPropertyChangeNotifier;

@SuppressWarnings("serial")
public class AbsoluteNotifyingComponentPosition extends AbstractPropertyChangeNotifier implements Serializable {
	public static final String LEFT_VALUE_PROPERTY_NAME = "left";
	public static final String TOP_VALUE_PROPERTY_NAME = "top";
	
	private Integer left;
	private Integer top;
	
	private ComponentPosition layoutPosition;

	// Intentionally package local
	void setLayoutComponentPosition(ComponentPosition newLayoutPosition) {
		if (this.layoutPosition != null)
			throw new IllegalStateException(getClass().getName() + " is already attached to a previous ComponentPosition: " + this.layoutPosition);
		layoutPosition = newLayoutPosition;
	}
	
	public void setLeft(Integer leftValueInPixels) {
		firePropertyChange(LEFT_VALUE_PROPERTY_NAME, this.left, this.left = leftValueInPixels);
		if (layoutPosition != null)
			layoutPosition.setLeft((float) leftValueInPixels, Sizeable.UNITS_PIXELS);
	}

	public void setTop(Integer topValueInPixels) {
		firePropertyChange(TOP_VALUE_PROPERTY_NAME, this.top, this.top = topValueInPixels);
		if (layoutPosition != null)
			layoutPosition.setTop((float) topValueInPixels, Sizeable.UNITS_PIXELS);
	}
	
	public Integer getLeft() {
		return left;
	}

	public Integer getTop() {
		return top;
	}

	public String getCSSString() {
		return "top:" + top + UNIT_SYMBOLS[UNITS_PIXELS] + ";left:" + left + UNIT_SYMBOLS[UNITS_PIXELS] + ";";
	}

}