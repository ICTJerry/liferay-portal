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

package com.liferay.dynamic.data.mapping.io;

import com.liferay.dynamic.data.mapping.BaseDDMTestCase;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMForm;
import com.liferay.portlet.dynamicdatamapping.model.DDMFormField;
import com.liferay.portlet.dynamicdatamapping.model.DDMFormFieldOptions;
import com.liferay.portlet.dynamicdatamapping.model.LocalizedValue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pablo Carvalho
 */
public abstract class BaseDDMFormSerializerTestCase extends BaseDDMTestCase {

	protected DDMForm createDDMForm() {
		DDMForm ddmForm = new DDMForm();

		ddmForm.setAvailableLocales(
			createAvailableLocales(LocaleUtil.BRAZIL, LocaleUtil.US));
		ddmForm.setDDMFormFields(createDDMFormFields());
		ddmForm.setDefaultLocale(LocaleUtil.US);

		return ddmForm;
	}

	protected DDMFormFieldOptions createDDMFormFieldOptions() {
		DDMFormFieldOptions ddmFormFieldOptions = new DDMFormFieldOptions();

		ddmFormFieldOptions.addOption("Value 1");

		ddmFormFieldOptions.addOptionLabel(
			"Value 1", LocaleUtil.BRAZIL, "Opcao 1");
		ddmFormFieldOptions.addOptionLabel(
			"Value 1", LocaleUtil.US, "Option 1");

		ddmFormFieldOptions.addOption("Value 2");

		ddmFormFieldOptions.addOptionLabel(
			"Value 2", LocaleUtil.BRAZIL, "Opcao 2");
		ddmFormFieldOptions.addOptionLabel(
			"Value 2", LocaleUtil.US, "Option 2");

		return ddmFormFieldOptions;
	}

	protected List<DDMFormField> createDDMFormFields() {
		List<DDMFormField> ddmFormFields = new ArrayList<>();

		ddmFormFields.add(
			createNestedDDMFormFields("ParentField", "ChildField"));
		ddmFormFields.add(createRadioDDMFormField("BooleanField"));
		ddmFormFields.add(createSelectDDMFormField("SelectField"));
		ddmFormFields.add(createTextDDMFormField("TextField"));
		ddmFormFields.add(createHTMLDDMFormField("HTMLField"));

		return ddmFormFields;
	}

	protected DDMFormField createHTMLDDMFormField(String name) {
		DDMFormField ddmFormField = new DDMFormField(name, "ddm-text-html");

		ddmFormField.setDataType("html");
		ddmFormField.setFieldNamespace("ddm");
		ddmFormField.setIndexType("keyword");
		ddmFormField.setLabel(createHTMLDDMFormFieldLabel());
		ddmFormField.setLocalizable(true);
		ddmFormField.setPredefinedValue(
			createHTMLDDMFormFieldPredefinedValue());
		ddmFormField.setReadOnly(false);
		ddmFormField.setRepeatable(false);
		ddmFormField.setRequired(false);
		ddmFormField.setShowLabel(true);
		ddmFormField.setTip(createHTMLDDMFormFieldTip());

		return ddmFormField;
	}

	protected LocalizedValue createHTMLDDMFormFieldLabel() {
		LocalizedValue label = new LocalizedValue();

		label.addString(LocaleUtil.BRAZIL, "HTML");
		label.addString(LocaleUtil.US, "HTML");

		return label;
	}

	protected LocalizedValue createHTMLDDMFormFieldPredefinedValue() {
		LocalizedValue predefinedValue = new LocalizedValue();

		predefinedValue.addString(LocaleUtil.BRAZIL, "");
		predefinedValue.addString(LocaleUtil.US, "");

		return predefinedValue;
	}

	protected LocalizedValue createHTMLDDMFormFieldTip() {
		LocalizedValue predefinedValue = new LocalizedValue();

		predefinedValue.addString(LocaleUtil.BRAZIL, "Dica");
		predefinedValue.addString(LocaleUtil.US, "Tip");

		return predefinedValue;
	}

	protected DDMFormField createNestedDDMFormFields(
		String parentName, String childName) {

		DDMFormField parentDDMFormField = createTextDDMFormField(parentName);

		List<DDMFormField> nestedDDMFormFields = new ArrayList<>();

		nestedDDMFormFields.add(createSelectDDMFormField(childName));

		parentDDMFormField.setNestedDDMFormFields(nestedDDMFormFields);

		return parentDDMFormField;
	}

	protected DDMFormField createRadioDDMFormField(String name) {
		DDMFormField ddmFormField = new DDMFormField(name, "radio");

		ddmFormField.setDataType("string");
		ddmFormField.setDDMFormFieldOptions(createDDMFormFieldOptions());
		ddmFormField.setLocalizable(false);
		ddmFormField.setReadOnly(false);
		ddmFormField.setRepeatable(false);
		ddmFormField.setRequired(true);
		ddmFormField.setShowLabel(false);
		ddmFormField.setVisibilityExpression("false");

		return ddmFormField;
	}

	protected DDMFormField createSelectDDMFormField(String name) {
		DDMFormField ddmFormField = new DDMFormField(name, "select");

		ddmFormField.setDataType("string");
		ddmFormField.setLocalizable(false);
		ddmFormField.setIndexType("");
		ddmFormField.setMultiple(true);
		ddmFormField.setReadOnly(false);
		ddmFormField.setRepeatable(false);
		ddmFormField.setRequired(false);
		ddmFormField.setShowLabel(true);
		ddmFormField.setVisibilityExpression("true");

		DDMFormFieldOptions ddmFormFieldOptions = createDDMFormFieldOptions();

		ddmFormField.setDDMFormFieldOptions(ddmFormFieldOptions);

		return ddmFormField;
	}

	@Override
	protected DDMFormField createTextDDMFormField(String name) {
		DDMFormField ddmFormField = new DDMFormField(name, "text");

		ddmFormField.setDataType("string");
		ddmFormField.setIndexType("keyword");
		ddmFormField.setLabel(createTextDDMFormFieldLabel());
		ddmFormField.setLocalizable(false);
		ddmFormField.setPredefinedValue(
			createTextDDMFormFieldPredefinedValue());
		ddmFormField.setReadOnly(false);
		ddmFormField.setRepeatable(true);
		ddmFormField.setRequired(false);
		ddmFormField.setShowLabel(true);
		ddmFormField.setVisibilityExpression("true");

		return ddmFormField;
	}

	protected LocalizedValue createTextDDMFormFieldLabel() {
		LocalizedValue label = new LocalizedValue();

		label.addString(LocaleUtil.BRAZIL, "Texto");
		label.addString(LocaleUtil.US, "Text");

		return label;
	}

	protected LocalizedValue createTextDDMFormFieldPredefinedValue() {
		LocalizedValue predefinedValue = new LocalizedValue();

		predefinedValue.addString(LocaleUtil.BRAZIL, "Exemplo");
		predefinedValue.addString(LocaleUtil.US, "Example");

		return predefinedValue;
	}

}