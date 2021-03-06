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


/**
 * このクラスは、値が異なる場合にボディが実行されるタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/23
 * @author Kawakicchi
 */
public final class IfNotEqualTag extends AbstractValueEqualTag {

	/**
	 * コンストラクタ
	 */
	public IfNotEqualTag() {
		super(IfNotEqualTag.class);
	}

	@Override
	protected boolean isEqual(final Object aSrc, final Object aDst) {
		if (null == aSrc && null == aDst) {
			return false;
		} else if (null != aSrc && null != aDst) {
			if (aSrc.getClass() == aDst.getClass()) {
				return !aSrc.equals(aDst);
			} else {
				return true;
			}
		} else {
			return true;
		}
	}
}
