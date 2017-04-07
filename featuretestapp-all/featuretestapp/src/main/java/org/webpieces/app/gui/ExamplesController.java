package org.webpieces.app.gui;

import javax.inject.Singleton;

import org.webpieces.router.api.actions.Action;
import org.webpieces.router.api.actions.Actions;

@Singleton
public class ExamplesController {
	
	public Action index() {
		//renderThis renders index.html in the same package as this controller class
		return Actions.renderThis(); 
	}

	public Action notFound() {
		return Actions.renderThis();
	}
	
	public Action internalError() {
		return Actions.renderThis();
	}
}
