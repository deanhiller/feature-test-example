package org.webpieces.app.example1.filters;

import org.webpieces.router.api.routing.AbstractRoutes;
import org.webpieces.router.api.routing.PortType;

public class JsonFilterRoutes extends AbstractRoutes {
  @Override
  protected void configure() {
//    addNotFoundFilter("/json.*", );

    addFilter("/json.*", JsonAuthFilter.class, null, PortType.ALL_FILTER);
    addFilter("/json.*", ExceptionTranslationFilter.class, null, PortType.ALL_FILTER);
  }
}
