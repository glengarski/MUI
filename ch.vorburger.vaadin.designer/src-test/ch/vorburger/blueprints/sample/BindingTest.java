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

package ch.vorburger.blueprints.sample;

import static org.junit.Assert.assertEquals;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFProperties;
import org.junit.Test;

import ch.vorburger.vaadin.databinding.PropertyProperties;
import ch.vorburger.vaadin.designer.samplescreen.SamplescreenFactory;
import ch.vorburger.vaadin.designer.samplescreen.SamplescreenPackage;
import ch.vorburger.vaadin.designer.samplescreen.Screen;

import com.vaadin.data.Property;
import com.vaadin.data.util.AbstractProperty;

/**
 * Testing Eclipse Databinding with Vaadin Property.
 *  
 * @author Michael Vorburger
 */
public class BindingTest {
	@Test
	public void testBinding() {
		
		// TODO use a dynamic on-the-fly ecore for test, don't depend on Samplescreen
		
		Screen screen = SamplescreenFactory.eINSTANCE.createScreen();
		screen.setTitle("This is the Title");
		
		
		Property p = new AbstractProperty() {
		
			private String value;
			
			@Override
			public void setValue(Object newValue) throws ReadOnlyException, ConversionException {
				value = (String) newValue;
				
			}
			
			@Override
			public Object getValue() {
				return value;
			}
			
			@Override
			public Class<?> getType() {
				return String.class;
			}
		};
		
		Realm realm = new DatabindingTestRealm();
		EMFDataBindingContext db = new EMFDataBindingContext(realm);
		
		db.bindValue(PropertyProperties.value(/* ? */).observe(p), 
				EMFProperties.value(SamplescreenPackage.Literals.SCREEN__TITLE).observe(screen));
		
		assertEquals(screen.getTitle(), p.getValue());
		
		p.setValue("reset, reset");
		assertEquals("reset, reset", p.getValue());
		assertEquals("reset, reset", screen.getTitle());

		db.dispose();
	}

}
