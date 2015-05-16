/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.azkfw.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;

import org.azkfw.configuration.ConfigurationFormatException;
import org.azkfw.log.LoggerFactory;
import org.azkfw.log.StdoutLoggerFactory;
import org.azkfw.plugin.PluginManager;
import org.azkfw.plugin.PluginServiceException;
import org.azkfw.util.StringUtility;

/**
 * このクラスは、プラグイン機能をロードするサーブレットクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/08/29
 * @author Kawakicchi
 */
public final class AzukiPluginServlet extends AbstractServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5612928065309901778L;

	/**
	 * プラグインコンフィグ
	 */
	private String plugin;

	/**
	 * コンストラクタ
	 */
	public AzukiPluginServlet() {
		super(AzukiPluginServlet.class);
	}

	@Override
	protected void doInitialize(final ServletConfig config) {
		String logClass = config.getInitParameter("logger-class");
		String logConfig = config.getInitParameter("logger-config");
		if (StringUtility.isEmpty(logClass)) {
			logClass = StdoutLoggerFactory.class.getName();
		}
		LoggerFactory.load(logClass, logConfig, getContext());

		plugin = config.getInitParameter("plugin-config");
		PluginManager.getInstance().initialize();
		doLoad();
	}

	@Override
	protected void doDestroy() {
		PluginManager.getInstance().destroy();
	}

	/**
	 * プラグインのロードを行います。
	 */
	private void doLoad() {
		if (StringUtility.isNotEmpty(plugin)) {
			info("Load plugin config file.[" + plugin + "]");
			try {
				PluginManager.getInstance().load(plugin, getContext());
			} catch (PluginServiceException ex) {
				error(ex);
			} catch (ConfigurationFormatException ex) {
				error(ex);
			} catch (IOException ex) {
				error(ex);
			}
		} else {
			warn("Not setting plugin-config.[web.xml]");
		}
	}

}
