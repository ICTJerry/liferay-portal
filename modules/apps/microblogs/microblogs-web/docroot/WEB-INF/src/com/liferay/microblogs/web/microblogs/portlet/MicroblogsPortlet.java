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

package com.liferay.microblogs.web.microblogs.portlet;

import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.microblogs.service.MicroblogsEntryLocalServiceUtil;
import com.liferay.microblogs.service.MicroblogsEntryServiceUtil;
import com.liferay.microblogs.util.MicroblogsUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Jonathan Lee
 */
public class MicroblogsPortlet extends MVCPortlet {

	public void deleteMicroblogsEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long microblogsEntryId = ParamUtil.getLong(
			actionRequest, "microblogsEntryId");

		MicroblogsEntryServiceUtil.deleteMicroblogsEntry(microblogsEntryId);
	}

	public void updateMicroblogsEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long microblogsEntryId = ParamUtil.getLong(
			actionRequest, "microblogsEntryId");

		String content = ParamUtil.getString(actionRequest, "content");
		int type = ParamUtil.getInteger(actionRequest, "type");
		long parentMicroblogsEntryId = ParamUtil.getLong(
			actionRequest, "parentMicroblogsEntryId");
		int socialRelationType = ParamUtil.getInteger(
			actionRequest, "socialRelationType");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			MicroblogsEntry.class.getName(), actionRequest);

		String[] assetTagNames = getAssetTagNames(content);

		serviceContext.setAssetTagNames(assetTagNames);

		if (microblogsEntryId > 0) {
			MicroblogsEntryServiceUtil.updateMicroblogsEntry(
				microblogsEntryId, content, socialRelationType, serviceContext);
		}
		else {
			MicroblogsEntryServiceUtil.addMicroblogsEntry(
				themeDisplay.getUserId(), content, type,
				parentMicroblogsEntryId, socialRelationType, serviceContext);
		}
	}

	public void updateMicroblogsEntryViewCount(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long microblogsEntryId = ParamUtil.getLong(
			actionRequest, "microblogsEntryId");

		MicroblogsEntry microblogsEntry =
			MicroblogsEntryLocalServiceUtil.fetchMicroblogsEntry(
				microblogsEntryId);

		if (microblogsEntry == null) {
			return;
		}

		AssetEntryLocalServiceUtil.incrementViewCounter(
			0, MicroblogsEntry.class.getName(), microblogsEntryId, 1);
	}

	protected String[] getAssetTagNames(String content) {
		List<String> assetTagNames = new ArrayList<>();

		assetTagNames.addAll(MicroblogsUtil.getHashtags(content));

		assetTagNames.addAll(MicroblogsUtil.getScreenNames(content));

		return assetTagNames.toArray(new String[assetTagNames.size()]);
	}

}