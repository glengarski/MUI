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

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.runtime.IStatus;

/**
 * Utility for {@link Binding}.
 * 
 * @author Michael Vorburger
 */
public class Bindings {

	// TODO this doesn't actually really work yet - would have to dig deeper into databinding...
	
	/**
	 * Checks Binding and throws BindingValidationException if not OK.
	 * 
	 * @param binding a Binding
	 * @throws BindingValidationException if the binding's validation status is not OK 
	 */
	public static void check(Binding binding) throws BindingValidationException {
		IStatus status = getValidationStatusCast(binding);
		if (!status.isOK())
			throw new BindingValidationException(status);
	}
	
	private static IStatus getValidationStatusCast(Binding binding) {
		return (IStatus) binding.getValidationStatus().getValue();
	}
}
