package org.webpieces.app.example1.filters;

import org.apache.commons.lang3.StringEscapeUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.webpieces.app.example1.errors.JsonError;
import org.webpieces.plugins.json.JacksonCatchAllFilter;
import org.webpieces.router.api.exceptions.ClientDataError;

import com.google.inject.Inject;

public class JsonCatchAllFilter extends JacksonCatchAllFilter {

	private ObjectMapper mapper;

	@Inject
	public JsonCatchAllFilter(ObjectMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	protected byte[] translateClientError(ClientDataError t) {
		String escapeJson = StringEscapeUtils.escapeJson(t.getMessage());
		JsonError error = new JsonError("400 bad request: "+escapeJson, 0);
		
		return translateJson(mapper, error);
	}

	@Override
	protected byte[] createNotFoundJsonResponse() {
		JsonError error = new JsonError("404 This url does not exist.  try another url", 0);
		return translateJson(mapper, error);
	}

	@Override
	protected byte[] translateServerError(Throwable t) {
		JsonError error = new JsonError("Server ran into a bug, please report", 0);
		return translateJson(mapper, error);
	}
	
}

