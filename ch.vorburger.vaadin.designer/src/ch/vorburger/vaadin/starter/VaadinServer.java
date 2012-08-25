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

package ch.vorburger.vaadin.starter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;

/**
 * Starter for Vaadin Web Application Main Servlet.
 * 
 * @author Michael Vorburger
 */
public abstract class VaadinServer extends JettyServer {

	// TODO Is there ends up not really being ANY use case for a VaadinServer outside of the DesignerServer, then these two classes could be merged together again for simplicity 
	
	private final Logger logger = LoggerFactory.getLogger(VaadinServer.class);
	private boolean productionMode = false;

	@Override
	public void start() throws Exception {
		context.setInitParameter("productionMode", Boolean.toString(productionMode));
		
        ServletHolder servletHolder = new ServletHolder(newVaadinApplicationServlet());
        configureVaadinApplicationServlet(servletHolder);
        context.addServlet(servletHolder, "/*");
        
        super.start();
	}

	protected void configureVaadinApplicationServlet(ServletHolder servletHolder) {
		servletHolder.setInitParameter("widgetset", getWidgetSetName());
	}

	protected AbstractApplicationServlet newVaadinApplicationServlet() {
		return new OSGiApplicationServlet();
	}
	
	protected Application VaadinApplicationServlet_getNewApplication(HttpServletRequest request) throws ServletException {
		try {
			return getVaadinApplicationClass().newInstance();
		} catch (Exception e) {
			final String msg = "VaadinServer.OSGiApplicationServlet.getApplicationClass().newInstance() failed";
			logger.error(msg, e);
			throw new ServletException(msg, e);
		}
	}
	
	/**
	 * This is needed so that VAADIN/themes/reindeer/* & Widget Set loading
	 * from the class path works, because the original Vaadin implementation
	 * in the parent class uses the Class Loader of the
	 * AbstractApplicationServlet, which when running under OSGI will not be
	 * the one where all Themes & the Widget Set can be found on - but the
	 * Class Loader of the Application class will (should...) have access to
	 * all the Theme/WidgetSet/CSS/JS resources we need to serve.
	 */
	protected ClassLoader VaadinApplicationServlet_getClassLoader() throws ServletException {
		return getVaadinApplicationClass().getClassLoader();
	}
	
	// TODO Following changes made in DesignerServer, this VaadinApplicationServlet_* stuff isn't needed anymore, can revert and inline as it was?
	// TODO Once that's done, could make it a STATIC inner class?
	
	/**
	 * This Vaadin ApplicationServlet handles class-loading in an OSGi context correctly.
	 */
	@SuppressWarnings("serial")
	protected class OSGiApplicationServlet extends AbstractApplicationServlet {

		@Override
		protected Application getNewApplication(HttpServletRequest request) throws ServletException {
			return VaadinApplicationServlet_getNewApplication(request);
		}

		@Override
		protected Class<? extends Application> getApplicationClass() throws ClassNotFoundException {
			return getVaadinApplicationClass();
		}

		@Override
		protected ClassLoader getClassLoader() throws ServletException {
			return VaadinApplicationServlet_getClassLoader();
		}
	}
	
	protected String getWidgetSetName() {
		return "com.vaadin.terminal.gwt.DefaultWidgetSet";
	}

	protected abstract Class<? extends Application> getVaadinApplicationClass();
	
	public boolean isProductionMode() {
		return productionMode;
	}

	public void setProductionMode(boolean productionMode) {
		this.productionMode = productionMode;
	}
	
}
