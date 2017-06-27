package org.webpieces.app.example1.filters;

import java.io.IOException;

import com.google.inject.Inject;

import org.apache.commons.lang3.StringEscapeUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.webpieces.app.example1.errors.JsonError;
import org.webpieces.plugins.json.JacksonCatchAllFilter;
import org.webpieces.router.api.exceptions.ClientDataError;


public class JsonCatchAllFilter extends JacksonCatchAllFilter {
  private ObjectMapper mapper;

  @Inject
  public JsonCatchAllFilter(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  protected byte[] translateClientError(ClientDataError t) {
    String escapeJson = StringEscapeUtils.escapeJson(t.getMessage());
    JsonError error = new JsonError(escapeJson, 0);

    try {
      return mapper.writeValueAsBytes(error);
    } catch (JsonGenerationException e) {
      throw new RuntimeException(e);
    } catch (JsonMappingException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  @Override
  protected byte[] createNotFoundJsonResponse() {
    JsonError error = new JsonError("This url has no api.  try another url", 0);
    try {
      byte[] data = mapper.writeValueAsBytes(error);
      return data;
    } catch (JsonGenerationException e) {
      throw new RuntimeException(e);
    } catch (JsonMappingException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  @Override
  protected byte[] translateServerError(Throwable t) {
    JsonError error = new JsonError("Server ran into a bug, please report", 0);
    try {
      byte[] data = mapper.writeValueAsBytes(error);
      return data;
    } catch (JsonGenerationException e) {
      throw new RuntimeException(e);
    } catch (JsonMappingException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}