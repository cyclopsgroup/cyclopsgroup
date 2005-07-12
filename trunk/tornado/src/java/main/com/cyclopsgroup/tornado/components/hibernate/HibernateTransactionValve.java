/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 * 
 * Licensed under the Open Software License, Version 2.1 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://opensource.org/licenses/osl-2.1.php
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.tornado.components.hibernate;

import org.hibernate.Transaction;

import com.cyclopsgroup.tornado.core.HibernateFactory;
import com.cyclopsgroup.waterview.PageRuntime;
import com.cyclopsgroup.waterview.PipelineContext;
import com.cyclopsgroup.waterview.Valve;

public class HibernateTransactionValve implements Valve
{
    public void invoke(PageRuntime runtime, PipelineContext context)
            throws Exception
    {
        HibernateFactory hf = (HibernateFactory) runtime.getServiceManager()
                .lookup(HibernateFactory.ROLE);
        Transaction transaction = hf.getCurrentSession().beginTransaction();
        try
        {
            context.invokeNextValve(runtime);
            transaction.commit();
        }
        catch (Exception e)
        {
            transaction.commit();
            throw e;
        }
        finally
        {
            hf.closeCurrentSession();
        }
    }
}
