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
package jp.azkfw.servlet.jsp.tag.biz;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.jsp.JspException;

import jp.azkfw.servlet.jsp.tag.AbstractPrintTag;

import org.azkfw.util.StringUtility;

/**
 * このクラスは、URLをレンダリングするタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public final class PrintDatetimeTag extends AbstractPrintTag {

	/**
	 * コンストラクタ
	 */
	public PrintDatetimeTag() {
		super(PrintDatetimeTag.class);
	}

	@Override
	protected String doPrint(final Object aValue) throws JspException {
		Calendar cln = null;
		if (null == aValue) {

		} else if (aValue instanceof Date) {
			Date dt = (Date) aValue;
			cln = Calendar.getInstance();
			cln.setTimeInMillis(dt.getTime());
		} else if (aValue instanceof java.sql.Date) {
			java.sql.Date dt = (java.sql.Date) aValue;
			cln = Calendar.getInstance();
			cln.setTimeInMillis(dt.getTime());
		} else if (aValue instanceof Timestamp) {
			Timestamp ts = (Timestamp) aValue;
			cln = Calendar.getInstance();
			cln.setTimeInMillis(ts.getTime());
		}

		if (null != cln) {
			String str = String.format("%04d/%02d/%02d %02d:%02d:%02d", cln.get(Calendar.YEAR), cln.get(Calendar.MONTH) + 1,
					cln.get(Calendar.DAY_OF_MONTH), cln.get(Calendar.HOUR_OF_DAY), cln.get(Calendar.MINUTE), cln.get(Calendar.SECOND));
			return str;
		} else {
			return StringUtility.EMPTY;
		}
	}
}
