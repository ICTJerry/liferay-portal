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

package com.liferay.portal.kernel.comment;

import com.liferay.portal.model.StagedModel;
import com.liferay.portlet.exportimport.lar.PortletDataContext;
import com.liferay.portlet.exportimport.lar.PortletDataException;

/**
 * @author Adolfo Pérez
 */
public interface DiscussionStagingHandler {

	public <T extends StagedModel> void exportReferenceDiscussions(
			PortletDataContext portletDataContext, T stagedModel)
		throws PortletDataException;

	public String getClassName();

	public <T extends StagedModel> void importReferenceDiscussions(
			PortletDataContext portletDataContext, T stagedModel)
		throws PortletDataException;

}