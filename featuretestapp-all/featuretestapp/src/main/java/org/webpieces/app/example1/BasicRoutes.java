package org.webpieces.app.example1;

import org.webpieces.router.api.routing.AbstractRouteModule;

import static org.webpieces.app.example1.BasicRouteId.INDEX;
import static org.webpieces.ctx.api.HttpMethod.GET;

public class BasicRoutes extends AbstractRouteModule {
  @Override
  protected void configure() {
    addRoute(GET, "/", "BasicController.index", INDEX);
    setPageNotFoundRoute("BasicController.notFound");
    setInternalErrorRoute("BasicController.internalError");
  }
}
