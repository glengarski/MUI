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

package ch.vorburger.vaadin.designer.tests.web;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;

import ch.vorburger.vaadin.starter.VaadinServer;

import com.vaadin.Application;
import com.vaadin.ui.Button;
import com.vaadin.ui.Window;

/**
 * Test for VaadinServer.
 * 
 * @author Michael Vorburger
 */
@SuppressWarnings("serial")
public class VaadinServerTest {

	public static final String HTML_ID = "abutton";

	@Test
	@Ignore // TODO reactivate when http://dev.vaadin.com/ticket/9386 is fixed?
	public void testStartVaadinServer() throws Exception {
		VaadinServer server = new VaadinServer() {
			@Override
			protected Class<? extends Application> getVaadinApplicationClass() {
				return VaadinTestApplication.class;
			}
		};

		try {
			server.start();
			// Check if "Saluton!" button shows up:
			WebDriver driver = new InternetExplorerDriver();
			driver.get(server.getURL());
			WebElement button = driver.findElement(By.id(HTML_ID));
			String text = button.getText();
			Assert.assertEquals("Saluton!", text);
			driver.quit();
			
		} finally {
			server.stop();
		}
	}

	public static class VaadinTestApplication extends Application {
		@Override
		public void init() {
			Window mainWindow = new Window("Vaadin Test Application");

			Button root = new Button("Saluton!");
			root.setDebugId(HTML_ID);

			mainWindow.addComponent(root);
			setMainWindow(mainWindow);
		}
	}
}
