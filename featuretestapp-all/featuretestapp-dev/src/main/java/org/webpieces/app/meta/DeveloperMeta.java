package org.webpieces.app.meta;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.inject.Module;

import org.webpieces.app.base.FeatureTestAppMeta;
import org.webpieces.plugins.hsqldb.H2DbPlugin;
import org.webpieces.router.api.routing.Plugin;
import org.webpieces.router.api.routing.Routes;
import org.webpieces.router.api.routing.WebAppMeta;

public class DeveloperMeta implements WebAppMeta {

	private FeatureTestAppMeta prodMeta = new FeatureTestAppMeta();
	
	@Override
	public void initialize(Map<String, String> props) {
		prodMeta.initialize(props);
	}
	
	@Override
	public List<Module> getGuiceModules() {
		return prodMeta.getGuiceModules();
	}

	@Override
	public List<Routes> getRouteModules() {
		return prodMeta.getRouteModules();
	}

	@Override
	public List<Plugin> getPlugins() {
		List<Plugin> prodPlugins = prodMeta.getPlugins();
		List<Plugin> devPlugins = Lists.newArrayList(
				//This is only for the development server to expose a GUI to use http://localhost:9000/@db
				new H2DbPlugin()
				);

		prodPlugins.addAll(devPlugins);
		
		return prodPlugins;
	}

}
