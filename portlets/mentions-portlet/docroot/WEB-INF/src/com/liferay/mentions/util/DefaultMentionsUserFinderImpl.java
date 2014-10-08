/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.mentions.util;

import com.liferay.portal.kernel.dao.orm.WildcardMode;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.comparator.UserScreenNameComparator;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Sergio González
 */
public class DefaultMentionsUserFinderImpl implements MentionsUserFinder {

	@Override
	public List<User> getUsers(
			long companyId, long userId, String query)
		throws PortalException, SystemException {

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("wildcardMode", WildcardMode.TRAILING);

		return UserLocalServiceUtil.search(
			companyId, query, WorkflowConstants.STATUS_APPROVED, params, 0,
			_MAX_USERS, new UserScreenNameComparator());
	}

	private static int _MAX_USERS = 100;

}