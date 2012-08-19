/*******************************************************************************
 * Copyright (c) 2012 Michael Vorburger (http://www.vorburger.ch).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

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

package ch.vorburger.emf;

import java.io.IOException;
import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;


/**
 * EMF I/O Utils.
 * Helpers for loading & saving EMF models.
 * 
 * @author Michael Vorburger
 */
public class EIO {

    protected ResourceSet resourceSet = new ResourceSetImpl(); // TODO later must be injected, not re-created, so that existing cache is used
    
    public Resource loadResource(URI uri) throws IOException, DiagnosticException {
    	Map<String, Object> map = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
    	if (!map.containsKey("xmi"))
    		map.put("xmi", new XMIResourceFactoryImpl());
    	
        // NOTE: For the XtextResourceSet classpath resolving URIs, must use getResource() instead of a createResource() followed by a resource.load(resource.getResourceSet().getLoadOptions()); 
        Resource resource = resourceSet.getResource(uri, true);
        if (resource == null)
            throw new IOException("EMF resourceSet.getResource() => null, probably no matching resource factory is registered, for URI: " + uri.toString());
        
        if (!resource.getErrors().isEmpty()) {
            Diagnostic diagnostic = EcoreUtil.computeDiagnostic(resource, false /* do not includeWarnings */);
            throw new DiagnosticExceptionWithURIAndToString(diagnostic, uri);
        }
        
        return resource;
    }

    @SuppressWarnings("unchecked")
    public <T extends EObject> T load(URI uri, Class<T> klazz) throws IOException, DiagnosticException {
        return (T) load(uri);
    }

    public EObject load(URI uri) throws IOException, DiagnosticException {
        Resource resource = loadResource(uri);
        if (resource != null && !resource.getContents().isEmpty())
            return resource.getContents().get(0);
        else
            throw new IllegalArgumentException("Loading failed, resource == null, or no contents: " + uri.toString());
    }
}