package org.webpieces.app.example1.routes;

import static org.webpieces.ctx.api.HttpMethod.GET;

import org.webpieces.router.api.routing.ScopedRoutes;

public class JsonRoutes extends ScopedRoutes {
  @Override
  protected String getScope() {
    return "/json";
  }

  @Override
  protected boolean isHttpsOnlyRoutes() {
    return false;
  }

  @Override
  protected void configure() {
//    addRoute(GET, "", "JsonController.index", ROOT);
    addContentRoute(GET, "/", "JsonController.index");
    addContentRoute(GET, "/search", "JsonController.search");
  }
}
