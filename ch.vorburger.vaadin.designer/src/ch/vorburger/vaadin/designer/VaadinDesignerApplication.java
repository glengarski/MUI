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

import com.vaadin.Application;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class VaadinDesignerApplication extends Application {
	
	private Window mainWindow = new Window("MUI, the Vaadin/EMF/Xtext/WB-based UI Designer");

	@Override
	public void init() {
		setMainWindow(mainWindow);
	}

	// TODO later don't expose this directly, but gave a method to which a Resource is passed, with look-up...
	public void setRootContent(ComponentContainer root) {
		mainWindow.setContent(root);
	}
	
}
