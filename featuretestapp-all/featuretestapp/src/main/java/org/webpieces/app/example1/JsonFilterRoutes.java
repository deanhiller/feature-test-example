package org.webpieces.app.example1;

import org.webpieces.router.api.routing.AbstractRouteModule;
import org.webpieces.router.api.routing.PortType;

/**
 * Created by pstover on 4/6/17.
 */
public class JsonFilterRoutes extends AbstractRouteModule {
  @Override
  protected void configure() {
    addFilter("/json.*", ExceptionTranslationFilter.class, null, PortType.ALL_FILTER);
    addFilter("/json.*", JsonAuthFilter.class, null, PortType.ALL_FILTER);
  }
}
