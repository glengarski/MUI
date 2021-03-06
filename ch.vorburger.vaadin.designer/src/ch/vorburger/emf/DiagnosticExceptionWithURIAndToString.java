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

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.URI;

/**
 * EMF DiagnosticException with a proper toString().
 * 
 * This is very useful (indispensable!) in Unit Tests.
 * 
 * @author Michael Vorburger
 */
@SuppressWarnings("serial")
public class DiagnosticExceptionWithURIAndToString extends DiagnosticException {

    private final URI uri;

    public DiagnosticExceptionWithURIAndToString(Diagnostic diagnostic, URI uri) {
        super(diagnostic);
        this.uri = uri;
    }

    @Override
    public String toString() {
        Diagnostic diagnostic = getDiagnostic();
        return uri.toString() + ", " + super.toString() + ": " + diagnostic.toString();
    }

}