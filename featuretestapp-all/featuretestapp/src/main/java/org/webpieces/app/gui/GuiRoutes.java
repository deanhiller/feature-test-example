package org.webpieces.app.gui;

import static org.webpieces.app.gui.ExamplesRouteId.MAIN_ROUTE;
import static org.webpieces.ctx.api.HttpMethod.GET;

import org.webpieces.router.api.routing.AbstractRoutes;

public class GuiRoutes extends AbstractRoutes {

	@Override
	public void configure() {
		//You can always do a platform override on ControllerResolver.java as well to resolve the controller String in some other
		//way.  The controller string is the 3rd parameter in the addRoute calls
		
		//The path parameter(2nd param) is a semi-regular expression that we match on.  We convert {...} to a 
		//   regex a capture group for you BUT leave the rest untouched so you can whatever regex you like
		//   ORDER matters so the order of modules is important and the order of routes
		//
		//The controller.method is a relative or absolute path with ClassName.method at the end
		//RouteIds are used to redirect in the webapp itself and must be unique
		addRoute(GET, "/",              "ExamplesController.index", MAIN_ROUTE);

		//Add where all the html files exist
		String workingDir = System.getProperty("user.dir");
		addStaticDir("/assets/", workingDir+"/public/", false);
		//Add a single file by itself(not really needed)
		addStaticFile("/favicon.ico", "public/favicon.ico", false);
		addStaticFile("/test.css", "public/crud/fonts.css", false);

		setPageNotFoundRoute("ExamplesController.notFound");
		setInternalErrorRoute("ExamplesController.internalError");
	}

}
