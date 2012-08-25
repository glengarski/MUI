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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import ch.vorburger.beans.PropertyChangeNotifier;

import com.vaadin.ui.Component;

import fi.jasoft.dragdroplayouts.DDAbsoluteLayout;

/**
 * DDAbsoluteLayout with JavaBean-style position change event notification listener support.
 * 
 * @author Michael Vorburger
 */
@SuppressWarnings("serial")
public class DDAbsoluteLayoutWithBeanPositionChangeImpossibleImplementation extends DDAbsoluteLayout {

	// TODO either remove or finish this initial idea... but I don't actually need it, now using DDAbsoluteLayoutWithBeanPositionChangeImpossibleImplementation instead

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
    public void addComponent(Component c, ComponentPositionWithPropertyChangeNotifier position) {
        // TODO impossible, because componentToCoordinates is private.. :-( : componentToCoordinates.put(c, position);
        try {
            addComponent(c);
        } catch (IllegalArgumentException e) {
            // Remove component coordinates if adding fails
        	// TODO impossible, because componentToCoordinates is private.. :-( : componentToCoordinates.remove(c);
            throw e;
        }
    }
    
	public class ComponentPositionWithPropertyChangeNotifier extends ComponentPosition implements PropertyChangeNotifier {
    	private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    	
    	public static final String LEFT_VALUE_PROPERTY_NAME = "leftValue";
    	public static final String TOP_VALUE_PROPERTY_NAME = "topValue";

    	public ComponentPositionWithPropertyChangeNotifier(Float leftValueInPixels, Float topValueInPixels) {
    		super();
    		setLeft(leftValueInPixels, DDAbsoluteLayoutWithBeanPositionChangeImpossibleImplementation.UNITS_PIXELS);
    		setTop(topValueInPixels, DDAbsoluteLayoutWithBeanPositionChangeImpossibleImplementation.UNITS_PIXELS);
    	}
    	
    	@Override
    	public void setLeftValue(Float leftValue) {
    		final Float oldLeftValue = getLeftValue(); 
    		super.setLeftValue(leftValue);
    		changeSupport.firePropertyChange(LEFT_VALUE_PROPERTY_NAME, oldLeftValue, leftValue);
    	}

    	@Override
    	public void setTopValue(Float topValue) {
    		Float oldTopValue = getTopValue();
    		super.setTopValue(topValue);
    		changeSupport.firePropertyChange(TOP_VALUE_PROPERTY_NAME, oldTopValue, topValue);
    	}

    	@Override
    	public void setLeft(Float leftValue, int leftUnits) {
    		final Float oldLeftValue = getLeftValue(); 
    		super.setLeft(leftValue, leftUnits);
    		changeSupport.firePropertyChange(LEFT_VALUE_PROPERTY_NAME, oldLeftValue, leftValue);
    	}

    	@Override
    	public void setTop(Float topValue, int topUnits) {
    		final Float oldTopValue = getTopValue();
    		super.setTop(topValue, topUnits);
    		changeSupport.firePropertyChange(TOP_VALUE_PROPERTY_NAME, oldTopValue, topValue);
    	}

    	// ---
    	
    	@Override
    	public void addPropertyChangeListener(PropertyChangeListener listener) {
    		changeSupport.addPropertyChangeListener(listener);
    	}

    	@Override
    	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
    		changeSupport.addPropertyChangeListener(propertyName, listener);
    	}

    	@Override
    	public void removePropertyChangeListener(PropertyChangeListener listener) {
    		changeSupport.removePropertyChangeListener(listener);
    	}

    	@Override
    	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
    		changeSupport.removePropertyChangeListener(propertyName, listener);
    	}
    }
}
