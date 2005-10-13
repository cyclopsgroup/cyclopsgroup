/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 * 
 * Licensed under the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE
 * (CDDL) Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/cddl1.txt
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.waterview.ui.page;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.Module;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.utils.TypeUtils;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Module for date picker
 */
public class DatePicker implements Module
{
    private static final String[] DAY_NAMES = new String[] { "SU", "MO", "TU",
            "WE", "TH", "FR", "SA" };

    /**
     * Overwrite or implement method execute()
     *
     * @see com.cyclopsgroup.waterview.Module#execute(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.Context)
     */
    public void execute(RuntimeData data, Context context) throws Exception
    {
        Date day = data.getParams().getDate("day", new Date());
        context.put("currentDate", TypeUtils.toString(day));

        Calendar cal = new GregorianCalendar();
        cal.setTime(day);

        context.put("year", new Integer(cal.get(Calendar.YEAR)));
        context.put("month", new Integer(cal.get(Calendar.MONDAY)));
        context.put("dayOfMonth", new Integer(cal.get(Calendar.DAY_OF_MONTH)));
        context.put("dayOfWeek", new Integer(cal.get(Calendar.DAY_OF_WEEK)));
        context.put("weekOfYear", new Integer(cal.get(Calendar.WEEK_OF_YEAR)));
        context.put("dayNames", DAY_NAMES);
    }
}
