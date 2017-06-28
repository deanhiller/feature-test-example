package org.webpieces.app.example1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

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
import org.webpieces.data.api.DataWrapper;
import org.webpieces.data.api.DataWrapperGenerator;
import org.webpieces.data.api.DataWrapperGeneratorFactory;
import org.webpieces.httpclient11.api.HttpFullRequest;
import org.webpieces.httpclient11.api.HttpFullResponse;
import org.webpieces.httpclient11.api.HttpSocket;
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
import org.webpieces.webserver.test.ResponseExtract;
import org.webpieces.webserver.test.ResponseWrapper;

import com.google.inject.Binder;
import com.google.inject.Module;

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
  private HttpSocket http11Socket;

  @Before
  public void setUp() throws InterruptedException, ClassNotFoundException, ExecutionException, TimeoutException {
    log.info("Setting up test");
    Asserts.assertWasCompiledWithParamNames("test");

    //you may want to create this server ONCE in a static method BUT if you do, also remember to clear out all your
    //mocks after every test AND you can no longer run single threaded(tradeoffs, tradeoffs)
    //This is however pretty fast to do in many systems...
    Server webserver = new Server(
        getOverrides(false), new AppOverridesModule(), new ServerConfig(pUnit));
    webserver.start();
    http11Socket = connectHttp(false, null);
  }

  /**
   * Testing a synchronous controller may be easier especially if there is no remote communication.
   */
  @Test
  public void testBasicSearch() {
    HttpFullRequest req = createJsonRequest("/json/search", "{ `query`: `asdf`, `maxResults`: 4 }"
        .replace('`', '\"'));

    CompletableFuture<HttpFullResponse> respFuture = http11Socket.send(req);

    ResponseWrapper resp = ResponseExtract.waitResponseAndWrap(respFuture);

    resp.assertStatusCode(KnownStatusCode.HTTP_200_OK);
    resp.assertContentType("application/json");

    System.out.println(resp);
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

  static HttpFullRequest createJsonRequest(String uri, String body) {
	DataWrapper dataWrapper = gen.wrapByteArray(body.getBytes());
    HttpRequest request = createRequest(uri);
    request.addHeader(new Header(KnownHeaderName.ACCEPT,"application/json"));
    request.addHeader(new Header(KnownHeaderName.CONTENT_LENGTH, dataWrapper.getReadableSize()+""));

    HttpFullRequest fullReq = new HttpFullRequest(request, dataWrapper);
    return fullReq;
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
