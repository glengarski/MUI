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

package ch.vorburger.vaadin.dragdroplayouts.event;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.vaadin.tools.ReflectTools;
import com.vaadin.ui.Component;

import fi.jasoft.dragdroplayouts.DDAbsoluteLayout;

/**
 * DDAbsoluteLayout with Vaadin-style position change event notification listener support.
 * 
 * @author Michael Vorburger
 */
public class DDAbsoluteLayoutWithPositionChange extends DDAbsoluteLayout {

	// TODO either remove or finish this initial idea... but I don't actually need it, now using DDAbsoluteLayoutWithBeanPositionChangeImpossibleImplementation instead
	
	// TODO instead of manually maintaining this inverse Map of AbsoluteLayout's private componentToCoordinates, it would be better if Vaadin had used a BidiMap..
    private Map<ComponentPosition, Component> coordinatesToComponent = new HashMap<ComponentPosition, Component>();
	protected Component getComponent(ComponentPosition component) {
		return coordinatesToComponent.get(component);
	}
		
	@Override
	public void addComponent(Component c, String cssPosition) {
		// TODO rewrite so it uses new AbsoluteNotifyingComponentPosition() instead of new ComponentPosition(), and maintains componentToCoordinates
		super.addComponent(c, cssPosition);
	}

	@Override
	public ComponentPosition getPosition(Component component) {
		// TODO rewrite so it uses new AbsoluteNotifyingComponentPosition() instead of new ComponentPosition(), and maintains componentToCoordinates
		return super.getPosition(component);
	}

	public static class PositionChangeEvent extends Component.Event {
		// TODO same for rightValue / bottomValue... although I don't really need it
		public static enum ComponentPositionProperty { LeftValue, TopValue }

		private static final String POSITION_CHANGE_EVENT_ID = "positionChanged";
		private static final Method METHOD = ReflectTools.findMethod(PositionChangeListener.class, "positionChanged", PositionChangeEvent.class);;

		private final ComponentPositionProperty property;
		private final Float oldValue;
		private final Float newValue;;
    
		public PositionChangeEvent(Component source, ComponentPositionProperty property, Float oldValue, Float newValue) {
			super(source);
			this.property = property;
			this.oldValue = oldValue;
			this.newValue = newValue;
		}

		public ComponentPositionProperty getProperty() {
			return property;
		}

		public Float getOldValue() {
			return oldValue;
		}

		public Float getNewValue() {
			return newValue;
		}
	}

	/**
	 * The listener interface for receiving <code>PositionChangeEvent</code> objects.
	 */
	public interface PositionChangeListener extends Serializable {
		/**
	     * Notifies this listener that one of the component's position properties has changed.
	     * 
	     * @param event change event object
	     */
	    public void positionChanged(PositionChangeEvent event);
	}
	
	public void addListener(PositionChangeListener listener) {
		addListener(PositionChangeEvent.POSITION_CHANGE_EVENT_ID, PositionChangeEvent.class, listener, PositionChangeEvent.METHOD);
	}
	// TODO support addListener just for one specific Property, like Bean's addPropertyChangeListener(String propertyName, PropertyChangeListener listener)

	public void removeListener(PositionChangeListener listener) {
		removeListener(PositionChangeEvent.POSITION_CHANGE_EVENT_ID, PositionChangeEvent.class, listener);
	}
	
	public class ComponentPositionWithNotifier extends ComponentPosition {
		
		@Override
		public void setLeftValue(Float leftValue) {
			// TODO final Float oldLeftValue = getLeftValue(); 
			super.setLeftValue(leftValue);
			// TODO fireEvent(new PositionChangeEvent(this, PositionChangeEvent.ComponentPositionProperty.LeftValue, oldLeftValue, leftValue));
		}

//		@Override
//		public void setTopValue(Float topValue) {
//			Float oldTopValue = getTopValue();
//			super.setTopValue(topValue);
//			// TODO fireEvent(...)
//		}
//
//		@Override
//		public void setLeft(Float leftValue, int leftUnits) {
//			final Float oldLeftValue = getLeftValue(); 
//			super.setLeft(leftValue, leftUnits);
//			// TODO fireEvent(...)
//		}
//
//		@Override
//		public void setTop(Float topValue, int topUnits) {
//			final Float oldTopValue = getTopValue();
//			super.setTop(topValue, topUnits);
//			// TODO fireEvent(...)
//		}
	}
	
}
