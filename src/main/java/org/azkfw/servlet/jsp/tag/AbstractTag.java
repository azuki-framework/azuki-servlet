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
package org.azkfw.servlet.jsp.tag;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import org.azkfw.lang.LoggingObject;
import org.azkfw.servlet.store.HttpSessionStore;
import org.azkfw.util.StringUtility;

/**
 * このクラスは、タグ機能を実装する基底クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public abstract class AbstractTag extends LoggingObject implements Tag {

	/**
	 * Parent tag
	 */
	private Tag parentTag;

	/**
	 * Page context
	 */
	private PageContext pageContext;

	/**
	 * コンストラクタ
	 */
	public AbstractTag() {
		super(AbstractTag.class);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractTag(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractTag(final Class<?> aClass) {
		super(aClass);
	}

	@Override
	public final void setPageContext(final PageContext aPageContext) {
		pageContext = aPageContext;
	}

	@Override
	public final Tag getParent() {
		return parentTag;
	}

	@Override
	public final void setParent(final Tag aParent) {
		parentTag = aParent;
	}

	@Override
	public void release() {
	}

	/**
	 * ページコンテキストを取得する。
	 * 
	 * @return ページコンテキスト
	 */
	protected final PageContext getPageContext() {
		return pageContext;
	}

	/**
	 * ページコンテキストに値を設定する。
	 * 
	 * @param aName 名前
	 * @param aValue 値
	 */
	protected final void setPageAttribute(final String aName, final Object aValue) {
		pageContext.setAttribute(aName, aValue);
	}

	/**
	 * ページコンテキストから値を取得する。
	 * 
	 * @param aName 名前
	 * @return 値
	 */
	protected final Object getPageAttribute(final String aName) {
		return pageContext.getAttribute(aName);
	}

	/**
	 * ページコンテキストから値を取得する。
	 * 
	 * @param aName 名前
	 * @param aKey キー
	 * @return 値
	 */
	@SuppressWarnings("unchecked")
	protected final Object getPageAttribute(final String aName, final String aKey) {
		Object value = null;
		Object o = getPageAttribute(aName);
		if (null != o) {
			if (o instanceof Map<?, ?>) {
				value = ((Map<String, Object>) o).get(aKey);
			}
		}
		return value;
	}

	/**
	 * セッションコンテキストから値を取得する。
	 * 
	 * @param aName 名前
	 * @return 値
	 */
	protected final Object getSessionAttribute(final String aName) {
		Object value = null;
		HttpSession session = pageContext.getSession();
		if (null != session) {
			@SuppressWarnings("unchecked")
			Map<String, Object> userArea = (Map<String, Object>) session.getAttribute(HttpSessionStore.SESSION_NAME_USER_AREA);
			if (null != userArea) {
				value = userArea.get(aName);
			}
		}
		return value;
	}

	/**
	 * セッションコンテキストから値を取得する。
	 * 
	 * @param aName 名前
	 * @param aKey キー
	 * @return 値
	 */
	@SuppressWarnings("unchecked")
	protected final Object getSessionAttribute(final String aName, final String aKey) {
		Object value = null;
		Object o = getSessionAttribute(aName);
		if (null != o) {
			if (o instanceof Map<?, ?>) {
				value = ((Map<String, Object>) o).get(aKey);
			}
		}
		return value;
	}

	/**
	 * リクエストコンテキストに値を設定する。
	 * 
	 * @param aName 名前
	 * @param aValue 値
	 */
	protected final void setRequestAttribute(final String aName, final Object aValue) {
		pageContext.getRequest().setAttribute(aName, aValue);
	}

	/**
	 * リクエストコンテキストから値を取得する。
	 * 
	 * @param aName 名前
	 * @return 値
	 */
	protected final Object getRequestAttribute(final String aName) {
		return pageContext.getRequest().getAttribute(aName);
	}

	/**
	 * リクエストコンテキストから値を取得する。
	 * 
	 * @param aName 名前
	 * @param aKey キー
	 * @return 値
	 */
	@SuppressWarnings("unchecked")
	protected final Object getRequestAttribute(final String aName, final String aKey) {
		Object value = null;
		Object o = getRequestAttribute(aName);
		if (null != o) {
			if (o instanceof Map<?, ?>) {
				value = ((Map<String, Object>) o).get(aKey);
			}
		}
		return value;
	}

	/**
	 * リクエストコンテキストに値を設定する。
	 * 
	 * @param aName 名前
	 * @param aValue 値
	 */
	protected final void setAttribute(final String aName, final Object aValue) {
		setAttribute(null, aName, aValue);
	}

	/**
	 * コンテキストに値を設定する。
	 * 
	 * @param aScope スコープ。<code>null</code>の場合リクエストコンテキストを参照する。
	 * @param aName 名前
	 * @param aValue 値
	 */
	protected final void setAttribute(final String aScope, final String aName, final Object aValue) {
		if (null != aScope && "page".equals(aScope.toLowerCase())) {
			setPageAttribute(aName, aValue);
		} else if (StringUtility.isEmpty(aScope) || "request".equals(aScope.toLowerCase())) {
			setRequestAttribute(aName, aValue);
		}
	}

	/**
	 * コンテキストから値を取得する。
	 * 
	 * @param aName 名前
	 * @return 値。値が見つからない場合は、<code>null</code>を返す。
	 */
	protected final Object getAttribute(final String aName) {
		return getAttribute(null, aName, null);
	}

	/**
	 * リクエストコンテキストから値を取得する。
	 * 
	 * @param aName 名前
	 * @param aKey キー
	 * @return 値。値が見つからない場合は、<code>null</code>を返す。
	 */
	protected final Object getAttribute(final String aName, final String aKey) {
		return getAttribute(null, aName, aKey);
	}

	/**
	 * コンテキストから値を取得する。
	 * 
	 * @param aScope スコープ。<code>null</code>の場合リクエストコンテキストを参照する。
	 * @param aName 名前
	 * @param aKey キー
	 * @return 値。値が見つからない場合は、<code>null</code>を返す。
	 */
	protected final Object getAttribute(final String aScope, final String aName, final String aKey) {
		Object value = null;
		String scp = aScope;
		if (StringUtility.isEmpty(scp) || "request".equals(scp.toLowerCase())) {
			if (StringUtility.isNotEmpty(aKey)) {
				value = getRequestAttribute(aName, aKey);
			} else {
				value = getRequestAttribute(aName);
			}
		} else if ("page".equals(scp.toLowerCase())) {
			if (StringUtility.isNotEmpty(aKey)) {
				value = getPageAttribute(aName, aKey);
			} else {
				value = getPageAttribute(aName);
			}
		} else if ("session".equals(scp.toLowerCase())) {
			if (StringUtility.isNotEmpty(aKey)) {
				value = getSessionAttribute(aName, aKey);
			} else {
				value = getSessionAttribute(aName);
			}
		}
		return value;
	}
}
