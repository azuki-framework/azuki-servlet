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

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.azkfw.context.Context;

/**
 * このクラスは、サーブレット機能の実装を行うための基底クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/19
 * @author Kawakicchi
 */
public abstract class AbstractServlet extends PrimitiveServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4013523062769751635L;

	/**
	 * コンテキスト
	 */
	private Context context;

	/**
	 * コンストラクタ
	 */
	public AbstractServlet() {
		super();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param name 名前
	 */
	public AbstractServlet(final String name) {
		super(name);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param clazz クラス
	 */
	public AbstractServlet(final Class<?> clazz) {
		super(clazz);
	}

	@Override
	public final void init(final ServletConfig config) throws ServletException {
		super.init(config);
		context = new ServletContext(config.getServletContext());
		doInitialize(config);
	}

	@Override
	public final void destroy() {
		doDestroy();
	}

	/**
	 * 初期化処理を行う。
	 * 
	 * @param config コンフィグ情報
	 * @throws ServletException サーブレット機能に起因する問題が発生した場合
	 */
	protected abstract void doInitialize(final ServletConfig config) throws ServletException;

	/**
	 * 解放処理を行う。
	 */
	protected abstract void doDestroy();

	/**
	 * コンテキストを取得する。
	 * 
	 * @return コンテキスト
	 */
	protected final Context getContext() {
		return context;
	}

}
