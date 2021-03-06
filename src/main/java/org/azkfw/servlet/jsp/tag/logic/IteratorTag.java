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
package org.azkfw.servlet.jsp.tag.logic;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.IterationTag;
import javax.servlet.jsp.tagext.Tag;

import org.azkfw.util.StringUtility;

/**
 * このクラスは、繰り返し処理を行うタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/19
 * @author Kawakicchi
 */
public final class IteratorTag extends AbstractIteratorTag {

	/**
	 * 名前
	 */
	private String name;

	/**
	 * キー
	 */
	private String key;

	/**
	 * スコープ
	 */
	private String scope;

	/**
	 * put
	 */
	private String put;

	/**
	 * Iterator
	 */
	private Iterator<?> iterator;

	/**
	 * 名前を設定する。
	 * 
	 * @param aName 名前
	 */
	public final void setName(final String aName) {
		name = aName;
	}

	/**
	 * 名前を取得する。
	 * 
	 * @return 名前
	 */
	protected final String getName() {
		return name;
	}

	/**
	 * キーを設定する。
	 * 
	 * @param aKey キー
	 */
	public final void setKey(final String aKey) {
		key = aKey;
	}

	/**
	 * キーを取得する。
	 * 
	 * @return キー
	 */
	protected final String getKey() {
		return key;
	}

	/**
	 * スコープを設定する。
	 * 
	 * @param aScope スコープ
	 */
	public final void setScope(final String aScope) {
		scope = aScope;
	}

	/**
	 * スコープを取得する。
	 * 
	 * @return スコープ
	 */
	protected final String getScope() {
		return scope;
	}

	public final void setPut(final String aPut) {
		put = aPut;
	}

	protected final String getPut() {
		return put;
	}

	@Override
	public int doEndTag() throws JspException {
		return (Tag.EVAL_PAGE);
	}

	@Override
	public int doStartTag() throws JspException {
		Object obj = getAttribute(getScope(), getName(), getKey());
		if (null == obj) {
			return (Tag.SKIP_BODY);
		}

		if (obj instanceof Object[]) {
			Object[] array = (Object[]) obj;
			iterator = Arrays.asList(array).iterator();
		} else if (obj instanceof Collection<?>) {
			Collection<?> collection = (Collection<?>) obj;
			iterator = collection.iterator();
		} else if (obj instanceof Iterator<?>) {
			iterator = (Iterator<?>) obj;
		} else {
			throw new JspException("Invalid type[" + obj.getClass().getName() + "].");
		}

		if (iterator.hasNext()) {
			Object o = iterator.next();

			if (StringUtility.isNotEmpty(getPut())) {
				setAttribute(getPut(), o);
			}

			return (Tag.EVAL_BODY_INCLUDE);
		} else {
			return (Tag.SKIP_BODY);
		}
	}

	@Override
	public int doAfterBody() throws JspException {
		if (null == iterator) {
			return (Tag.SKIP_BODY);
		}

		if (iterator.hasNext()) {
			Object o = iterator.next();

			if (StringUtility.isNotEmpty(getPut())) {
				setAttribute(getPut(), o);
			}

			return (IterationTag.EVAL_BODY_AGAIN);
		} else {
			return (Tag.SKIP_BODY);
		}
	}
}
