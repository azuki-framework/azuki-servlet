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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.azkfw.context.AbstractContext;

/**
 * このクラスは、Web用のコンテキスト機能を実装するクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 12/06/07
 * @author Kawakicchi
 */
public class ServletContext extends AbstractContext {

	/**
	 * Servlet context
	 */
	private javax.servlet.ServletContext context;

	/**
	 * コンストラクタ
	 * 
	 * @param context コンテキスト
	 */
	public ServletContext(final javax.servlet.ServletContext context) {
		this.context = context;
	}

	@Override
	public String getAbstractPath(final String name) {
		return context.getRealPath(getFullPath(name));
	}

	@Override
	public InputStream getResourceAsStream(final String name) {
		InputStream stream = null;
		stream = context.getResourceAsStream(getFullPath(name));
		if (null == stream) {
			try {
				stream = new FileInputStream(name);
			} catch (FileNotFoundException ex) {
				;
			}
		}
		if (null == stream) {
			stream = this.getClass().getResourceAsStream(name);
		}
		if (null == stream) {
			stream = Class.class.getResourceAsStream(name);
		}
		return stream;
	}

	/**
	 * フルパスを取得する。
	 * 
	 * @param name Name
	 * @return パス
	 */
	private String getFullPath(final String name) {
		StringBuffer sb = new StringBuffer("/WEB-INF");
		if (!name.startsWith("/")) {
			sb.append("/");
		}
		sb.append(name);
		return sb.toString();
	}
}
