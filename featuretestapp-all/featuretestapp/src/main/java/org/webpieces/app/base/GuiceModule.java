package org.webpieces.app.base;

import org.webpieces.app.client.RemoteTweetSearchService;
import org.webpieces.app.example1.Hydrator;
import org.webpieces.app.example1.HydratorImpl;
import org.webpieces.app.example1.TweetIdSearch;
import org.webpieces.app.example1.TweetIdSearchImpl;
import org.webpieces.app.example1.UserSearch;
import org.webpieces.app.example1.UserSearchImpl;
import org.webpieces.app.example1.client.RemoteHydratorService;
import org.webpieces.app.example1.client.RemoteUserSearchService;
import org.webpieces.app.example1.mock.MockHydratorService;
import org.webpieces.app.example1.mock.MockTweetSearchService;
import org.webpieces.app.example1.mock.MockUserSearchService;
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

    binder.bind(UserSearch.class).to(UserSearchImpl.class);
    binder.bind(TweetIdSearch.class).to(TweetIdSearchImpl.class);
    binder.bind(Hydrator.class).to(HydratorImpl.class);

    binder.bind(RemoteTweetSearchService.class).to(MockTweetSearchService.class);
    binder.bind(RemoteHydratorService.class).to(MockHydratorService.class);
    binder.bind(RemoteUserSearchService.class).to(MockUserSearchService.class);
  }
}
