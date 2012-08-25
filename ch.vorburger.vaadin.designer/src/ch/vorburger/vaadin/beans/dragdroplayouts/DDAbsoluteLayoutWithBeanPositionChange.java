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

import com.vaadin.event.dd.DropHandler;
import com.vaadin.ui.Component;

import fi.jasoft.dragdroplayouts.DDAbsoluteLayout;

/**
 * DDAbsoluteLayout with JavaBean-style position change event notification listener support.
 * 
 * @author Michael Vorburger
 */
@SuppressWarnings("serial")
public class DDAbsoluteLayoutWithBeanPositionChange extends DDAbsoluteLayout {

	protected final NotifyingDropHandler notifyingDropHandler = new NotifyingDropHandler();
	
    public DDAbsoluteLayoutWithBeanPositionChange() {
		super();
		super.setDropHandler(notifyingDropHandler);
	}

	/**
     * Adds a component to the layout. The component must be positioned by
     * providing a position object.  
     * 
     * The position object supports JavaBean-style property change notification,
     * as the Component position in the absolute layout changes.
     * 
     * @param c The component to add to the layout
     * @param position position object
     */
    public void addComponent(Component c, AbsoluteNotifyingComponentPosition position) {
		super.addComponent(c, position.getCSSString());
		ComponentPosition layoutPosition = this.getPosition(c);
		position.setLayoutComponentPosition(layoutPosition);
		notifyingDropHandler.componentToPosition.put(c, position);
    }
    
	@Override
	public void addComponent(Component c, String cssPosition) {
		throw new UnsupportedOperationException(getClass().getName() + " only supports addComponent(Component c, AbsoluteNotifyingComponentPosition position) for now");
	}

	@Override
	public void removeComponent(Component c) {
		super.removeComponent(c);
		notifyingDropHandler.componentToPosition.remove(c);
	}

	@Override
	public void setDropHandler(DropHandler dropHandler) {
        if (notifyingDropHandler.delegateDropHandler != dropHandler) {
        	notifyingDropHandler.delegateDropHandler = dropHandler;
            requestRepaint();
        }
	}
}
