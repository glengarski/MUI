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

import org.atmosphere.cpr.AtmosphereHandler;
import org.atmosphere.cpr.AtmosphereServlet;
import org.eclipse.jetty.servlet.ServletHolder;
import org.vaadin.dontpush.server.AtmosphereDontPushHandler;
import org.vaadin.dontpush.server.DontPushOzoneServlet;

import ch.vorburger.vaadin.designer.VaadinDesignerApplication;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;

/**
 * Starter for web-based Vaadin Designer.
 * 
 * This is SINGLE USER - it has (to have) this hard-coded into it.
 * 
 * @see com.vaadin.visualdesigner.server.EditorApplicationServer
 * 
 * @author Michael Vorburger
 */
public class DesignerServer extends VaadinServer {

	/**
	 * Singleton Application.
	 * Multi-window, or multi-user, Designer would not be be possible.
	 * It would be possible to support that (creating different Windows in Vaadin),
	 * but currently this Designer isn't written like that, nor does it have to be,
	 *  so this is not an issue.
	 */
	private VaadinDesignerApplication theDesignerApplication = new VaadinDesignerApplication(); 

	public VaadinDesignerApplication getDesignerApplication() {
		return theDesignerApplication;
	}
	
	@Override
	protected String getWidgetSetName() {
		return "ch.vorburger.vaadin.designer.VaadinDesignerWidgetset";
	}

	@Override
	protected Class<? extends Application> getVaadinApplicationClass() {
		return VaadinDesignerApplication.class;
	}

	@Override
	public void start() throws Exception {
        ServletHolder servletHolder = new ServletHolder(new AtmosphereServlet());
        // To "prevent deadlocks", according to http://code.google.com/p/dontpush/wiki/DontpushOzoneLayerAddOn
        servletHolder.setInitParameter("org.atmosphere.disableOnStateEvent", Boolean.TRUE.toString());
        
        // I had some trouble with getting the atmosphere.xml loaded from META-INF/ so instead doing it like this:
        servletHolder.setInitParameter(AtmosphereHandler.class.getName(), AtmosphereDontPushHandler.class.getName());
        servletHolder.setInitParameter(AtmosphereHandler.class.getName() + ".contextRoot", "/UIDL");

        servletHolder.setAsyncSupported(true); // == <async-supported>true</async-supported>
        context.addServlet(servletHolder, "/UIDL/*");

//	TODO LOW make this work... the API changed from Jetty v6 to v8... how to adapt this?
//		// THIS IS COPY/PASTED FROM com.vaadin.visualdesigner.server.EditorApplicationServer
//        // Change the name of the session cookie to support multiple servers on
//        // same host, using different ports (#6548). We simply append the port
//        // to the cookie name:
//        SessionManager sm = context.getSessionHandler().getSessionManager();
//        sm.setSessionCookie(sm.getSessionCookie() + getHttpPort());

        super.start();
	}
	
	protected AbstractApplicationServlet newVaadinApplicationServlet() {
		return new OSGiDontPushOzoneServlet();
	}
	
	// The following mick-mack is because of http://dev.vaadin.com/ticket/9397
	
	@Override
	protected void configureVaadinApplicationServlet(ServletHolder servletHolder) {
		super.configureVaadinApplicationServlet(servletHolder);
		servletHolder.setInitParameter("application", DummyApplication.class.getName());
	}

	public static class DummyApplication extends Application {
		@Override
		public void init() {
		}
	}
	
	/**
	 * This Vaadin ApplicationServlet combines the DontPushOzoneServlet and the class-loading in an OSGi context stuff from the VaadinServer parent class.
	 */
	@SuppressWarnings("serial")
	private class OSGiDontPushOzoneServlet extends DontPushOzoneServlet {

		@Override
		protected Application getNewApplication(HttpServletRequest request) throws ServletException {
			return theDesignerApplication;
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

}
