package org.webpieces.app.base;

import org.webpieces.app.example1.remoteclients.HydratorService;
import org.webpieces.app.example1.remoteclients.TweetSearchService;
import org.webpieces.app.example1.remoteclients.UserSearchService;
import org.webpieces.app.example1.remoteclients.impl.HydratorServiceImpl;
import org.webpieces.app.example1.remoteclients.impl.TweetSearchServiceImpl;
import org.webpieces.app.example1.remoteclients.impl.UserSearchServiceImpl;
import org.webpieces.router.api.Startable;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.multibindings.Multibinder;

public class GuiceModule implements Module {

  //This is where you would put the guice bindings you need though generally if done
  //right, you won't have much in this file.

  //If you need more Guice Modules as you want to scale, just modify FeatureTestAppMeta which returns
  //the list of all the Guice Modules in your application
  @Override
  public void configure(Binder binder) {
    //all modules have access to adding their own Startable objects to be run on server startup
    Multibinder<Startable> uriBinder = Multibinder.newSetBinder(binder, Startable.class);
    uriBinder.addBinding().to(PopulateDatabase.class);

    binder.bind(TweetSearchService.class).to(TweetSearchServiceImpl.class);

    binder.bind(HydratorService.class).to(HydratorServiceImpl.class);

    binder.bind(UserSearchService.class).to(UserSearchServiceImpl.class);
  }
}
