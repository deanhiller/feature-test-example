package org.webpieces.app.example1.filters;

import java.util.concurrent.CompletableFuture;

import javax.inject.Singleton;

import org.webpieces.app.example1.exception.InternalServerException;
import org.webpieces.router.api.actions.Action;
import org.webpieces.router.api.dto.MethodMeta;
import org.webpieces.router.api.routing.RouteFilter;
import org.webpieces.util.filters.Service;

@Singleton
public class ExceptionTranslationFilter extends RouteFilter<Void> {
  @Override
  public void initialize(Void initialConfig) {

  }

  @Override
  public CompletableFuture<Action> filter(MethodMeta meta, Service<MethodMeta, Action> nextFilter) {
    try {
      return nextFilter.invoke(meta);
    } catch (Exception e) {
      // TODO Return appropriate json
      throw new InternalServerException(e);
    }
  }
}
