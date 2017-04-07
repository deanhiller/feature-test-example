package org.webpieces.app.example1.routes;

import org.webpieces.router.api.routing.ScopedRoutes;

import static org.webpieces.ctx.api.HttpMethod.GET;

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
    // TODO Hitting "/json" caused:
//    java.lang.StringIndexOutOfBoundsException: String index out of range: -1
//    INFO: sending FULL RENDERHTML response. code=HttpResponseStatus [code=500, reason=Internal Server Error] for domain=localhost path=/json responseSender=ResponseOverrideSender [responseSender=ResponseSender[[http0][/0:0:0:0:0:0:0:1:51854] ]]
//    at java.lang.String.substring(String.java:1960)
//    at org.webpieces.router.impl.model.L3PrefixedRouting.fetchRoute(L3PrefixedRouting.java:67)
//    at org.webpieces.router.impl.model.L2DomainRoutes.fetchRoute(L2DomainRoutes.java:52)
//    at org.webpieces.router.impl.model.L1AllRouting.fetchRoute(L1AllRouting.java:49)
//    at org.webpieces.router.impl.RouteLoader.fetchRoute(RouteLoader.java:202)
//    at org.webpieces.devrouter.impl.DevRoutingService.incomingRequestImpl(DevRoutingService.java:82)
//    at org.webpieces.router.impl.AbstractRouterService.incomingCompleteRequest(AbstractRouterService.java:35)
//    at org.webpieces.webserver.impl.RequestReceiver.handleCompleteRequest(RequestReceiver.java:217)
//    at org.webpieces.webserver.impl.RequestReceiver.incomingRequest(RequestReceiver.java:229)
//    at org.webpieces.frontend.impl.TimedRequestListener.incomingRequest(TimedRequestListener.java:53)
//    at org.webpieces.frontend.impl.Http11Layer.deserialize(Http11Layer.java:107)
//    at org.webpieces.frontend.impl.Http11DataListener.incomingData(Http11DataListener.java:33)
//    at org.webpieces.frontend.impl.ServerDataListener.incomingData(ServerDataListener.java:63)
//    at org.webpieces.asyncserver.impl.ProxyDataListener.incomingData(ProxyDataListener.java:25)
//    at org.webpieces.nio.impl.threading.ThreadDataListener$1.run(ThreadDataListener.java:24)
//    at org.webpieces.util.threading.SessionExecutorImpl$RunnableWithKey.run(SessionExecutorImpl.java:73)
//    at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
//    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
//    at java.lang.Thread.run(Thread.java:745)

//    addRoute(GET, "", "JsonController.index", ROOT);
    addRoute(GET, "/", "JsonController.index", JsonRouteId.INDEX);
    addRoute(GET, "/search", "JsonController.search", JsonRouteId.SEARCH);
  }
}
