package ch.vorburger.vaadin.designer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.vorburger.vaadin.starter.DesignerServer;

public class Activator extends Plugin {
	public static final String PLUGIN_ID = Activator.class.getName();
	private final Logger logger = LoggerFactory.getLogger(Activator.class);
	
	private static Activator singleton;
	private DesignerServer server;

	public Activator() {
		super();
		singleton = this;
	}

	public static Activator getInstance() {
		return singleton;
	}

	public DesignerServer getRunningServer() {
		if (server == null) {
			server = new DesignerServer();
			try {
				server.start();
			} catch (Exception e) {
				String msg = "Start up of MUI embedded Jetty Server failed unexpectedly";
				IStatus status = new Status(IStatus.ERROR, PLUGIN_ID, msg , e);
				getLog().log(status);
				
				logger.error(msg, e);
			}
		}
		return server;
	}
	
	@Override
	public void start(BundleContext bundleContext) throws Exception {
		// For performance reasons we DO NOT want to start the Server here yet 
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		if (server != null) {
			server.stop();
			server = null;
		}
	}

}
