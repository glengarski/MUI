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

package ch.vorburger.vaadin.designer.tests;

import static org.junit.Assert.assertEquals;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ch.vorburger.beans.AbstractPropertyChangeNotifier;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.MethodProperty;

/**
 * Test for MethodProperty.
 * 
 * @author Michael Vorburger
 */
@SuppressWarnings("serial")
public class MethodPropertyTest {

	public static class TestBean extends AbstractPropertyChangeNotifier {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			firePropertyChange("name", this.name, this.name = name);
		}
	}

	private TestBean testBean;
	private MethodProperty<String> property;

	private String propertyName;
	private String oldValue;
	private String newValue;

	@Before
	public void setUp() {
		testBean = new TestBean();
		property = new MethodProperty<String /* TestBean? */>(testBean, "name");
		
		propertyName = null;
		oldValue = null;
		newValue = null;
	}
	
	@Test
	public void testSetPropertyReadBean() {
		property.setValue("Michael Vorburger");
		assertEquals("Michael Vorburger", testBean.getName());
	}
	
	@Test
	public void testSetBeanReadProperty() {
		testBean.setName("Michael Vorburger");
		assertEquals("Michael Vorburger", (String) property.getValue());
	}
	
	@Test
	public void testSetPropertyBeanChangeNotification() {
		property.setValue("Mickey Mouse");
		testBean.addPropertyChangeListener("name", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				propertyName = evt.getPropertyName();
				oldValue = (String) evt.getOldValue();
				newValue = (String) evt.getNewValue();
			}
		});
		property.setValue("Michael Vorburger");
		assertEquals("name", propertyName);
		assertEquals("Mickey Mouse", oldValue);
		assertEquals("Michael Vorburger", newValue);
	}
	
	@Test
	@Ignore // TODO Implement so that this works!!! First this, then BindingTest#testBinding
	public void testSetBeanPropertyChangeNotification() {
		testBean.setName("Mickey Mouse");
		property.addListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				newValue = (String) event.getProperty().getValue();
			}
		});
		testBean.setName("Michael Vorburger");
		assertEquals("Michael Vorburger", newValue);
	}
}
