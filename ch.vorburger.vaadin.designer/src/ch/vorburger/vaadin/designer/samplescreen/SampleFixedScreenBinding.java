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

package ch.vorburger.vaadin.designer.samplescreen;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFProperties;

import ch.vorburger.databinding.BindingValidationException;
import ch.vorburger.databinding.Bindings;
import ch.vorburger.databinding.DatabindingTestRealm;
import ch.vorburger.vaadin.beans.dragdroplayouts.AbsoluteNotifyingComponentPosition;

import com.vaadin.ui.TextField;

/**
 * Sets-up Data Binding between a SampleFixedScreenComponent and a Screen
 * 
 * @author Michael Vorburger
 */
public class SampleFixedScreenBinding {

	// TODO Once Test is created, refactor to adapt this in a more performant way, so that bindings are created once only, and attached to (re)observe.  How to deal with Fields though - possible?!
	
	// TODO Classes like this should be generate-able from a DSL - I'm dreaming of an "xtend-with-binding-support" @see SampleFixedScreenBindingDream.xtend
	
	public void bind(final SampleFixedScreenComponent ui, final Screen model) throws BindingValidationException {
		
		// TODO Later probably @Inject the EMFDataBindingContext ?
		// TODO Don't use DatabindingTestRealm but real SWT Realm.. once I integrate this with UI
		EMFDataBindingContext db = new EMFDataBindingContext(new DatabindingTestRealm());
		// TODO who will call db.dispose() when?! shouldn't forget this, may leak memory.
		// TODO Use EditingDomain variants... so that undo/redo can work...
		
		// TODO Further tests if Bindings.check can work - else remove 
		// Policy.setLog(new ExceptionThrowingILogger(Policy.getLog()));
		
		ui.getScreenTitle().setValue(model.getTitle());
		// TODO real binding, db.bindValue(null, // TODO binding to root.getScreenTitle() @see BindingTest
		//						EMFProperties.value(SamplescreenPackage.Literals.SCREEN__TITLE).observe(screen));
		
		for (Field field : model.getFields()) {
			TextField textField = new TextField();
			textField.setDebugId(field.getName()); // This is just & only for the WebDriver test
			textField.setCaption(field.getLabel()); // TODO real binding!!!
			
			AbsoluteNotifyingComponentPosition position = new AbsoluteNotifyingComponentPosition();
			Bindings.check(db.bindValue(BeanProperties.value(AbsoluteNotifyingComponentPosition.class, AbsoluteNotifyingComponentPosition.LEFT_VALUE_PROPERTY_NAME).observe(position),
						 EMFProperties.value(SamplescreenPackage.Literals.FIELD__X).observe(field)));
			
			Bindings.check(db.bindValue(BeanProperties.value(AbsoluteNotifyingComponentPosition.class, AbsoluteNotifyingComponentPosition.TOP_VALUE_PROPERTY_NAME).observe(position),
					 EMFProperties.value(SamplescreenPackage.Literals.FIELD__Y).observe(field)));

			ui.getAbsoluteLayout().addComponent(textField, position);
			
			// TODO remove this!!! it was just a super temporary ugly hack to see first result..
			position.addPropertyChangeListener(new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					System.out.println(evt.getPropertyName() + " : " + evt.getOldValue() + " -> " + evt.getNewValue());
//					try {
//						model.eResource().save(null);
//					} catch (IOException e) {
//						// TODO real error handling instead of ugly hack 
//						e.printStackTrace();
//						throw new RuntimeException(e);
//					}
				}
			});
		}

	}
}
