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

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URL;

import org.eclipse.emf.common.util.URI;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ch.vorburger.emf.EIO;
import ch.vorburger.vaadin.designer.samplescreen.SampleFixedScreenBinding;
import ch.vorburger.vaadin.designer.samplescreen.SampleFixedScreenComponent;
import ch.vorburger.vaadin.designer.samplescreen.SamplescreenPackage;
import ch.vorburger.vaadin.designer.samplescreen.Screen;
import ch.vorburger.vaadin.starter.DesignerServer;

/**
 * WebDriver Test for Designer.
 * 
 * @author Michael Vorburger
 */
public class DesignerWebDriverTest {

	private static WebDriver driver;
	private static WebDriverWait wait;
	private static DesignerServer server;
	private static Screen screen;
	private static Actions action;
	private static SampleFixedScreenComponent root;

	@Test
	public void testDraggingFieldInBrowserAssertModelChanged() throws Exception {
		WebElement fieldToDrag = driver.findElement(By.id("name"));
		action.clickAndHold(fieldToDrag).moveByOffset(100, 200).release().build().perform();
	
		// Because the WD affects a Jetty HTTP Worker thread, the change to
		// the EMF model won't be visible yet if this runs too fast, so:
		Thread.sleep(500);
		assertEquals(120, screen.getFields().get(0).getX());
		assertEquals(210, screen.getFields().get(0).getY());
	}
	
	@Test
	public void testChangeModelAssertFieldMovedInBrowser() throws Exception {
		screen.getFields().get(0).setX(44);
		screen.getFields().get(0).setY(56);
		
		WebElement fieldToDrag = driver.findElement(By.id("name"));
		WebElement parentDIV = fieldToDrag.findElement(By.xpath("..")); 
		assertEquals("44px", parentDIV.getCssValue("left"));
		assertEquals("56px", parentDIV.getCssValue("top"));
	}

	
	@BeforeClass
	public static void setUpClass() throws Exception {
		// This is needed (only) for stand-alone EMF initialization
		SamplescreenPackage.eINSTANCE.eClass();
		
		final String path = "/ch/vorburger/vaadin/designer/samplescreen/screen1.xmi";
		URL url = DesignerWebDriverTest.class.getResource(path);
		if (url == null)
			throw new IOException("Could not getResource() for " + path);
		screen = new EIO().load(URI.createURI(url.toString()), Screen.class);

		driver = newWebDriver();
		wait = new WebDriverWait(driver, 10);
		action = new Actions(driver);

		server = new DesignerServer();
		
		server.start();
		
		root = new SampleFixedScreenComponent();
		new SampleFixedScreenBinding().bind(root, screen);
		server.getDesignerApplication().setRootContent(root);

		driver.get(server.getURL());
		wait.until(ExpectedConditions.elementToBeClickable(By.id("name")));
	}
	
	@Before
	public void setUp() throws Exception {
		screen.getFields().get(0).setX(20);
		screen.getFields().get(0).setY(10);
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
		if (driver != null)
			driver.close();
		if (server != null)
			server.stop();
	}
	
	/**
	 * Returns a WebDriver suitable for this Platform.
	 * 
	 * On Windows we use IE, and else FF, because these are the two browser
	 * which the embedded SWT Browser widget which the Designer uses support.
	 * 
	 * The SWT Browser widget does not support Chrome; also ChromeDriver is
	 * needs a separate chromedriver.exe/... to be available, which we would
	 * have to package along and get from classpath and set
	 * webdriver.chrome.driver to as in https://gist.github.com/3463203 for this
	 * to work out-of-the-box for anybody. I've also had some chromedriver.exe
	 * left hanging; may be simply didn't close() properly.)
	 */
	private static WebDriver newWebDriver() {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("windows")) {
			return new InternetExplorerDriver();
		} else {
			return new FirefoxDriver();
		}
	}
}
