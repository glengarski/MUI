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

package ch.vorburger.vaadin.beans.dragdroplayouts;

import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;

/**
 * DropHandler Delegate.
 * 
 * @author Michael Vorburger
 */
@SuppressWarnings("serial")
public class DelegatingDropHandler implements DropHandler {

	protected DropHandler delegateDropHandler;

	@Override
	public void drop(DragAndDropEvent event) {
		if (delegateDropHandler != null)
			delegateDropHandler.drop(event);
	}

	@Override
	public AcceptCriterion getAcceptCriterion() {
		if (delegateDropHandler != null)
			return delegateDropHandler.getAcceptCriterion();
		else
			return AcceptAll.get();
	}
	
}
