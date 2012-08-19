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

package ch.vorburger.vaadin.designer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFProperties;

import ch.vorburger.blueprints.sample.DatabindingTestRealm;
import ch.vorburger.databinding.BindingValidationException;
import ch.vorburger.databinding.Bindings;
import ch.vorburger.emf.EIO;
import ch.vorburger.vaadin.beans.dragdroplayouts.AbsoluteNotifyingComponentPosition;
import ch.vorburger.vaadin.beans.dragdroplayouts.DDAbsoluteLayoutWithBeanPositionChange;
import ch.vorburger.vaadin.designer.samplescreen.Field;
import ch.vorburger.vaadin.designer.samplescreen.SampleFixedScreenComponent;
import ch.vorburger.vaadin.designer.samplescreen.SamplescreenPackage;
import ch.vorburger.vaadin.designer.samplescreen.Screen;

import com.vaadin.Application;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class VaadinDesignerApplication extends Application {
	@Override
	public void init() {
		Window mainWindow = new Window("Vaadin UI Designer Application");
		
		SampleFixedScreenComponent root = new SampleFixedScreenComponent();
		DDAbsoluteLayoutWithBeanPositionChange layout = root.getAbsoluteLayout();
		
//		Policy.setLog(new ExceptionThrowingILogger(Policy.getLog()));
		
		// TODO Don't use DatabindingTestRealm but real SWT Realm.. once I integrate this with UI
		EMFDataBindingContext db = new EMFDataBindingContext(new DatabindingTestRealm());
		// TODO who will call db.dispose() when?! shouldn't forget this, may leak memory.
		// TODO Use EditingDomain variants... so that undo/redo can work...
		
		// TODO Later, this shouldn't happen here during init(), but there should be a... method to load & reload UI model resources (no need for a command url, as in same JVM)
		SamplescreenPackage.eINSTANCE.eClass(); // This is needed for standalone EMF initialization
		final Screen screen;
		try {
			screen = new EIO().load(URI.createFileURI("../ch.vorburger.vaadin.designer.samplescreen/src/ch/vorburger/vaadin/designer/samplescreen/screen1.xmi"), Screen.class);
		} catch (Exception e) {
			// TODO real error handling instead of ugly hack 
			throw new RuntimeException(e);
		}
		
		root.getScreenTitle().setValue(screen.getTitle());
		// TODO real binding, db.bindValue(null, // TODO binding to root.getScreenTitle() @see BindingTest
		//						EMFProperties.value(SamplescreenPackage.Literals.SCREEN__TITLE).observe(screen));
		
		for (Field field : screen.getFields()) {
			TextField textField = new TextField();
			textField.setCaption(field.getLabel()); // TODO real binding!!!
			
			AbsoluteNotifyingComponentPosition position = new AbsoluteNotifyingComponentPosition();
			try {
				Bindings.check(db.bindValue(BeanProperties.value(AbsoluteNotifyingComponentPosition.class, AbsoluteNotifyingComponentPosition.LEFT_VALUE_PROPERTY_NAME).observe(position),
							 EMFProperties.value(SamplescreenPackage.Literals.FIELD__X).observe(field)));
				
				Bindings.check(db.bindValue(BeanProperties.value(AbsoluteNotifyingComponentPosition.class, AbsoluteNotifyingComponentPosition.TOP_VALUE_PROPERTY_NAME).observe(position),
						 EMFProperties.value(SamplescreenPackage.Literals.FIELD__Y).observe(field)));

			} catch (BindingValidationException e) {
				// TODO real error handling instead of ugly hack 
				throw new RuntimeException(e);
			}

			layout.addComponent(textField, position);
			
			position.addPropertyChangeListener(new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					try {
						System.out.println(evt.getPropertyName() + " : " + evt.getOldValue() + " -> " + evt.getNewValue());
						screen.eResource().save(null);
					} catch (IOException e) {
						// TODO real error handling instead of ugly hack 
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
			});
		}
		
		mainWindow.setContent(root);
		setMainWindow(mainWindow);
	}

}
