package org.webpieces.app.example1;

import java.util.concurrent.CompletableFuture;
import javax.inject.Singleton;

import org.webpieces.router.api.actions.Action;
import org.webpieces.router.api.dto.MethodMeta;
import org.webpieces.router.api.routing.RouteFilter;
import org.webpieces.util.filters.Service;

/**
 * Created by pstover on 4/6/17.
 */
@Singleton
public class JsonAuthFilter extends RouteFilter<Void> {
  @Override
  public void initialize(Void initialConfig) {

  }

  @Override
  public CompletableFuture<Action> filter(MethodMeta meta, Service<MethodMeta, Action> nextFilter) {
    return nextFilter.invoke(meta);
  }
}
