package org.webpieces.app.example1;

import java.util.List;

import com.google.inject.Binder;
import com.google.inject.Module;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.webpieces.app.Server;
import org.webpieces.app.ServerConfig;
import org.webpieces.app.example1.remoteclients.HydratorService;
import org.webpieces.app.example1.remoteclients.TweetSearchService;
import org.webpieces.app.example1.remoteclients.UserSearchService;
import org.webpieces.app.mock.MockHydratorService;
import org.webpieces.app.mock.MockRemoteSystem;
import org.webpieces.app.mock.MockTweetSearchService;
import org.webpieces.app.mock.MockUserSearchService;
import org.webpieces.data.api.DataWrapperGenerator;
import org.webpieces.data.api.DataWrapperGeneratorFactory;
import org.webpieces.httpcommon.api.RequestId;
import org.webpieces.httpcommon.api.RequestListener;
import org.webpieces.httpparser.api.common.Header;
import org.webpieces.httpparser.api.common.KnownHeaderName;
import org.webpieces.httpparser.api.dto.HttpRequest;
import org.webpieces.httpparser.api.dto.HttpRequestLine;
import org.webpieces.httpparser.api.dto.HttpUri;
import org.webpieces.httpparser.api.dto.KnownHttpMethod;
import org.webpieces.httpparser.api.dto.KnownStatusCode;
import org.webpieces.plugins.hibernate.HibernatePlugin;
import org.webpieces.util.logging.Logger;
import org.webpieces.util.logging.LoggerFactory;
import org.webpieces.webserver.test.AbstractWebpiecesTest;
import org.webpieces.webserver.test.Asserts;
import org.webpieces.webserver.test.FullResponse;
import org.webpieces.webserver.test.Http11Socket;
import org.webpieces.webserver.test.PlatformOverridesForTest;

/**
 * These are working examples of tests that sometimes are better done with the BasicSeleniumTest example but are here for completeness
 * so you can test the way you would like to test
 *
 * @author dhiller
 */
public class TestSearchRequestResponse extends AbstractWebpiecesTest {

  private final static Logger log = LoggerFactory.getLogger(TestSearchRequestResponse.class);

  //see below comments in AppOverrideModule
  private MockRemoteSystem mockRemote = new MockRemoteSystem(); //our your favorite mock library

  private static String pUnit = HibernatePlugin.PERSISTENCE_TEST_UNIT;

  private static final DataWrapperGenerator gen = DataWrapperGeneratorFactory.createDataWrapperGenerator();
  private Http11Socket http11Socket;

  @Before
  public void setUp() throws InterruptedException, ClassNotFoundException {
    log.info("Setting up test");
    Asserts.assertWasCompiledWithParamNames("test");

    //you may want to create this server ONCE in a static method BUT if you do, also remember to clear out all your
    //mocks after every test AND you can no longer run single threaded(tradeoffs, tradeoffs)
    //This is however pretty fast to do in many systems...
    Server webserver = new Server(
        platformOverrides, new AppOverridesModule(), new ServerConfig(pUnit));
    webserver.start();
    http11Socket = http11Simulator.openHttp();
  }

  /**
   * Testing a synchronous controller may be easier especially if there is no remote communication.
   */
  @Test
  public void testBasicSearch() {
    HttpRequest req = createJsonRequest("/json/search", "{ `query`: `asdf`, `maxResults`: 4 }"
        .replace('`', '\"'));

    http11Socket.send(req);

    FullResponse httpPayload = fetchSingleResponse();

    httpPayload.assertStatusCode(KnownStatusCode.HTTP_200_OK);
    httpPayload.assertContentType("application/json");

    System.out.println(httpPayload);
  }

  private FullResponse fetchSingleResponse() {
    List<FullResponse> responses = http11Socket.getResponses();
    Assert.assertEquals(1, responses.size());

    FullResponse httpPayload = responses.get(0);
    responses.clear();
    return httpPayload;
  }

  static HttpRequest createRequest(String uri) {
    HttpRequestLine requestLine = new HttpRequestLine();
    requestLine.setMethod(KnownHttpMethod.GET);
    requestLine.setUri(new HttpUri(uri));
    HttpRequest req = new HttpRequest();
    req.setRequestLine(requestLine);
    req.addHeader(new Header(KnownHeaderName.HOST, "yourdomain.com"));
    return req;
  }

  static HttpRequest createJsonRequest(String uri, String body) {
    HttpRequest request = createRequest(uri);
    request.setBody(gen.wrapByteArray(body.getBytes()));
    request.addHeader(new Header(KnownHeaderName.ACCEPT,"application/json"));

    return request;
  }

  private class AppOverridesModule implements Module {
    @Override
    public void configure(Binder binder) {
      //Add overrides here generally using mocks from fields in the test class

      binder.bind(TweetSearchService.class).to(MockTweetSearchService.class);
      binder.bind(HydratorService.class).to(MockHydratorService.class);
      binder.bind(UserSearchService.class).to(MockUserSearchService.class);
    }
  }
}
