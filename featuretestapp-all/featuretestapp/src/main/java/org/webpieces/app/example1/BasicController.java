package org.webpieces.app.example1;

import javax.inject.Singleton;

import org.webpieces.router.api.actions.Action;
import org.webpieces.router.api.actions.Actions;

@Singleton
public class BasicController {
  public Action index() {
    return Actions.renderThis();
  }

  public Action notFound() {
    return Actions.renderThis();
  }

  public Action internalError() {
    return Actions.renderThis();
  }
}
