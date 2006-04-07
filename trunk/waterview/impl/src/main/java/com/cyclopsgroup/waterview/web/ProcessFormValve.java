/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.waterview.web;

import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.Parameters;
import com.cyclopsgroup.waterview.RunData;
import com.cyclopsgroup.waterview.spi.PipelineContext;
import com.cyclopsgroup.waterview.spi.Valve;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Handle possible form
 */
public class ProcessFormValve implements Valve
{
    private void fail(RunData data, PipelineContext pc) throws Exception
    {
        String url = data.getRefererUrl();
        if (url.indexOf("keep_form=true") == -1)
        {
            if (url.indexOf('?') == -1)
            {
                url += "?keep_form=true";
            }
            else
            {
                url += "&keep_form=true";
            }
        }
        data.setRedirectUrl(url);
    }

    /**
     * Overwrite or implement method invoke()
     *
     * @see com.cyclopsgroup.waterview.spi.Valve#invoke(com.cyclopsgroup.waterview.RunData, com.cyclopsgroup.waterview.spi.PipelineContext)
     */
    public void invoke(RunData data, PipelineContext pc) throws Exception
    {
        Parameters params = data.getParameters();
        String formId = params.getString("form_id");
        Form form = null;
        if (StringUtils.isNotEmpty(formId))
        {
            form = (Form) data.getSessionContext().get(formId);
        }

        if (form == null)
        {
            pc.invokeNextValve(data);
            return;
        }

        boolean hasError = false;
        Field[] fields = form.getFields();
        for (int i = 0; i < fields.length; i++)
        {
            Field field = fields[i];
            field.setValue(params.getString(field.getName()));
            field.validate();
            if (field.isPassword())
            {
                field.setValue(StringUtils.EMPTY);
            }
            if (!hasError && field.isInvalid())
            {
                hasError = true;
            }
        }
        if (hasError)
        {
            if (params.getBoolean("force_validation"))
            {
                fail(data, pc);
                return;
            }
        }
        pc.invokeNextValve(data);
        Boolean formInvalid = (Boolean) data.getRequestContext().get(
                "formInvalid");
        if (formInvalid != null && formInvalid.booleanValue())
        {
            Properties formErrors = (Properties) data.getRequestContext().get(
                    "formErrors");
            for (Iterator i = formErrors.keySet().iterator(); i.hasNext();)
            {
                String fieldName = (String) i.next();
                String errorMessage = formErrors.getProperty(fieldName);
                Field field = form.getField(fieldName);
                if (field != null)
                {
                    field.setInvalid(true);
                    if (StringUtils.isEmpty(errorMessage))
                    {
                        field.setErrorMessage("Invalid field value ");
                    }
                    else
                    {
                        field.setErrorMessage(errorMessage);
                    }
                }
            }
            if (params.getBoolean("force_validation"))
            {
                fail(data, pc);
            }
        }
    }
}
