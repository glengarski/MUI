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

import java.io.IOException;
import java.net.ServerSocket;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Starter utility for Jetty v8 (eclipse.org) local embedded server.
 * 
 * This has support for Servlets, but no "Web Application Context" (web.xml).
 * 
 * @see http://wiki.eclipse.org/Jetty/Tutorial/Embedding_Jetty
 * 
 * @author Michael Vorburger
 */
public class JettyServer {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final int httpPort;
	protected final Server server;
	protected final ServletContextHandler context;
	
	public JettyServer() {
		this(findFreePort());
	}
	
	public JettyServer(int httpPort) {
		super();
		this.httpPort = httpPort;
		
		server = new Server(httpPort);
		server.setStopAtShutdown(true);
		
		context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);
	}

	public String getURL() {
		return "http://localhost:" + getHttpPort(); // there is never any "context"
	}
	
	public void start() throws Exception {
		server.start();
        if (!context.isAvailable() || !context.isStarted() || context.isFailed()  
           || server.isFailed()    || !server.isRunning()  || !server.isStarted()) {
        	
            // We must (try to) STOP the server, otherwise the forked background
            // thread keeps running, and the JVM won't exit (e.g. in JUnit Tests)
    		server.stop();
            
            throw new IllegalStateException("Embedded Jetty Server does not seem to have started up; CHECK THE LOG! (NO chained exception)");
        }
        
        final String msg = "Started Embedded Jetty Server at " + getURL();
		logger.info(msg);
        System.out.println(msg); // TODO REMOVE once logging works properly!
	}

	public void stop() throws Exception {
		context.stop();
		server.stop();
        final String msg = "Stopped Embedded Jetty Server at " + getURL();
        logger.info(msg);
        System.out.println(msg); // TODO REMOVE once logging works properly!
	}

	protected int getHttpPort() {
		return httpPort;
	}
	
	/**
	 * Returns a free port number on localhost.
	 * 
	 * Inspired from org.eclipse.jdt.launching.SocketUtil;
	 * but slightly improved with close() missing in JDT, added setReuseAddress, and changed to throw an exception instead of returning -1.
	 * 
	 * @return a free port number on localhost
	 * @throws IllegalStateException if unable to find a free port
	 */
	private static int findFreePort() {
		ServerSocket socket = null;
		try {
			socket = new ServerSocket(0);
			socket.setReuseAddress(true);
			int port = socket.getLocalPort();
			try {
				socket.close();
			} catch (IOException e) {
				// Ignore IOException on close()
			}
			return port;
		} catch (IOException e) { 
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
		}
		throw new IllegalStateException("Could not find a free TCP/IP port to start embedded Jetty HTTP Server on");
	}

}
