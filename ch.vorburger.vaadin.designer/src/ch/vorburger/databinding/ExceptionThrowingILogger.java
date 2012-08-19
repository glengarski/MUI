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

package ch.vorburger.databinding;

import org.eclipse.core.databinding.util.ILogger;
import org.eclipse.core.runtime.IStatus;

/**
 * Implementation of Eclipse Core DataBinding ILogger
 * which throws a RuntimeException if an Error or Warning is logged.
 * 
 * @author Michael Vorburger
 */
public class ExceptionThrowingILogger implements ILogger {

	// TODO this doesn't actually really work yet - would have to dig deeper into databinding...	

	private final ILogger delegate;

	public ExceptionThrowingILogger(ILogger delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public void log(IStatus status) {
		delegate.log(status);
		if (!status.isOK()) { // or better status.matches(ERROR | WARNING) ?
			throw new IStatusRuntimeException(status);
		}
	}

}
