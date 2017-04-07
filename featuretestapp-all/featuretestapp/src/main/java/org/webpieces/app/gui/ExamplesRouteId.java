package org.webpieces.app.gui;

import org.webpieces.router.api.routing.RouteId;

//You can have many RoutId files extending from RouteId so you can group RouteIds together like per package
public enum ExamplesRouteId implements RouteId {
	
	//all of these reference the same controller but with different example of controller definitions
	MAIN_ROUTE
	
	//using different tag examples
	
	
	//other examples are in CrudUserRouteId.java for doing CRUD on any entity
}
