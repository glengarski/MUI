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

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.TargetDetails;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.AbsoluteLayout.ComponentPosition;
import com.vaadin.ui.Component;

import fi.jasoft.dragdroplayouts.DDAbsoluteLayout;
import fi.jasoft.dragdroplayouts.events.LayoutBoundTransferable;

/**
 * DropHandler which notifies other objects of new position.
 * 
 * @author Michael Vorburger
 */
@SuppressWarnings("serial")
public class NotifyingDropHandler extends DelegatingDropHandler {

	final static Logger logger = LoggerFactory.getLogger(NotifyingDropHandler.class);
	
	// package-local - this is managed by DDAbsoluteLayoutWithBeanPositionChange
    Map<Component, AbsoluteNotifyingComponentPosition> componentToPosition = new HashMap<Component, AbsoluteNotifyingComponentPosition>();
	
	@Override
	public void drop(DragAndDropEvent event) {
		super.drop(event);

		// @see DefaultAbsoluteLayoutDropHandler#handleComponentReordering
		TargetDetails details = event.getTargetDetails();
        DDAbsoluteLayout layout = (DDAbsoluteLayout) details.getTarget();
        LayoutBoundTransferable transferable = (LayoutBoundTransferable) event.getTransferable();
        Component component = transferable.getComponent();
        ComponentPosition position = layout.getPosition(component);
        
        assert(position.getLeftUnits() == Sizeable.UNITS_PIXELS);
        assert(position.getTopUnits() == Sizeable.UNITS_PIXELS);
        
        Float leftPixelPosition = position.getLeftValue();
        Float topPixelPosition = position.getTopValue();
        
        AbsoluteNotifyingComponentPosition absoluteComponentPosition = componentToPosition.get(component);
        if (absoluteComponentPosition != null) {
	        int leftPixelPositionAsInteger = leftPixelPosition.intValue();
	        int topPixelPositionAsInteger = topPixelPosition.intValue();
			absoluteComponentPosition.setLeft(leftPixelPositionAsInteger);
			absoluteComponentPosition.setTop(topPixelPositionAsInteger);
        } else {
        	logger.error("Something's wrong internally... how come this component is not in my componentToPositionMap?! " + component.toString());        	
        }
	}

}
