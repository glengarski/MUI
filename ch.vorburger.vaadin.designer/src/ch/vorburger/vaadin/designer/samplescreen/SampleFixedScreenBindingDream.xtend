/*******************************************************************************
 * Copyright (c) 2012 Michael Vorburger (http://www.vorburger.ch).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package ch.vorburger.vaadin.designer.samplescreen

import com.vaadin.ui.TextField
import ch.vorburger.vaadin.beans.dragdroplayouts.AbsoluteNotifyingComponentPosition

/**
 * Dream Version of how SampleFixedScreenBinding should ideally be written,
 * in a TBD Xtend-based DSL which directly supports bidi DataBinding,
 * via a proposed "<=>" operator.
 * 
 * @author Michael Vorburger
 */
class SampleFixedScreenBindingDream {
	
	def bind(SampleFixedScreenComponent ui, Screen model) {
		// TODO ui.screenTitle <=> model.title
		model.fields.forEach[field |
			// TODO probably be possible to write below even better / more compact, with some (builder? Extension Methods?) helpers?
			val widget = new TextField
			val position = new AbsoluteNotifyingComponentPosition()
			widget.caption = field.label // TODO widget.caption <=> field.label
			// TODO position.left <=> field.x
			//      position.top  <=> field.y
			ui.absoluteLayout.addComponent(widget, position)
		]
	}
	
}