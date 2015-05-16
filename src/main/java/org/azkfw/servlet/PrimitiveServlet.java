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

import javax.servlet.http.HttpServlet;

import org.azkfw.log.Logger;
import org.azkfw.log.LoggerFactory;

/**
 * このクラスは、サーブレット機能を実装した基底クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/06/26
 * @author Kawakicchi
 */
public abstract class PrimitiveServlet extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2063808859381345201L;

	/**
	 * Default logger
	 */
	private static Logger LOGGER = LoggerFactory.create(PrimitiveServlet.class);

	/**
	 * logger
	 */
	private Logger logger;

	/**
	 * コンストラクタ
	 */
	public PrimitiveServlet() {
		logger = null;
	}

	/**
	 * コンストラクタ
	 * 
	 * @param name 名前
	 */
	public PrimitiveServlet(final String name) {
		logger = LoggerFactory.create(name);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param clazz クラス
	 */
	public PrimitiveServlet(final Class<?> clazz) {
		logger = LoggerFactory.create(clazz);
	}

	/**
	 * debugレベルのログを出力します。
	 * 
	 * @param message Message
	 */
	protected final void debug(final String message) {
		if (null != logger) {
			logger.debug(message);
		} else {
			LOGGER.debug(message);
		}
	}

	/**
	 * debugレベルのログを出力します。
	 * 
	 * @param throwable Throwable
	 */
	protected final void debug(final Throwable throwable) {
		if (null != logger) {
			logger.debug(throwable);
		} else {
			LOGGER.debug(throwable);
		}
	}

	/**
	 * debugレベルのログを出力します。
	 * 
	 * @param message Message
	 * @param throwable Throwable
	 */
	protected final void debug(final String message, final Throwable throwable) {
		if (null != logger) {
			logger.debug(message, throwable);
		} else {
			LOGGER.debug(message, throwable);
		}
	}

	/**
	 * infoレベルのログを出力します。
	 * 
	 * @param message Message
	 */
	protected final void info(final String message) {
		if (null != logger) {
			logger.info(message);
		} else {
			LOGGER.info(message);
		}
	}

	/**
	 * infoレベルのログを出力します。
	 * 
	 * @param throwable Throwable
	 */
	protected final void info(final Throwable throwable) {
		if (null != logger) {
			logger.info(throwable);
		} else {
			LOGGER.info(throwable);
		}
	}

	/**
	 * infoレベルのログを出力します。
	 * 
	 * @param message Message
	 * @param throwable Throwable
	 */
	protected final void info(final String message, final Throwable throwable) {
		if (null != logger) {
			logger.info(message, throwable);
		} else {
			LOGGER.info(message, throwable);
		}
	}

	/**
	 * warnレベルのログを出力します。
	 * 
	 * @param message Message
	 */
	protected final void warn(final String message) {
		if (null != logger) {
			logger.warn(message);
		} else {
			LOGGER.warn(message);
		}
	}

	/**
	 * warnレベルのログを出力します。
	 * 
	 * @param throwable Throwable
	 */
	protected final void warn(final Throwable throwable) {
		if (null != logger) {
			logger.warn(throwable);
		} else {
			LOGGER.warn(throwable);
		}
	}

	/**
	 * warnレベルのログを出力します。
	 * 
	 * @param message Message
	 * @param throwable Throwable
	 */
	protected final void warn(final String message, final Throwable throwable) {
		if (null != logger) {
			logger.warn(message, throwable);
		} else {
			LOGGER.warn(message, throwable);
		}
	}

	/**
	 * errorレベルのログを出力します。
	 * 
	 * @param message Message
	 */
	protected final void error(final String message) {
		if (null != logger) {
			logger.error(message);
		} else {
			LOGGER.error(message);
		}
	}

	/**
	 * errorレベルのログを出力します。
	 * 
	 * @param throwable Throwable
	 */
	protected final void error(final Throwable throwable) {
		if (null != logger) {
			logger.error(throwable);
		} else {
			LOGGER.error(throwable);
		}
	}

	/**
	 * errorレベルのログを出力します。
	 * 
	 * @param message Message
	 * @param throwable Throwable
	 */
	protected final void error(final String message, final Throwable throwable) {
		if (null != logger) {
			logger.error(message, throwable);
		} else {
			LOGGER.error(message, throwable);
		}
	}

	/**
	 * fatalレベルのログを出力します。
	 * 
	 * @param message Message
	 */
	protected final void fatal(final String message) {
		if (null != logger) {
			logger.fatal(message);
		} else {
			LOGGER.fatal(message);
		}
	}

	/**
	 * fatalレベルのログを出力します。
	 * 
	 * @param throwable Throwable
	 */
	protected final void fatal(final Throwable throwable) {
		if (null != logger) {
			logger.fatal(throwable);
		} else {
			LOGGER.fatal(throwable);
		}
	}

	/**
	 * fatalレベルのログを出力します。
	 * 
	 * @param message Message
	 * @param throwable Throwable
	 */
	protected final void fatal(final String message, final Throwable throwable) {
		if (null != logger) {
			logger.fatal(message, throwable);
		} else {
			LOGGER.fatal(message, throwable);
		}
	}
}
