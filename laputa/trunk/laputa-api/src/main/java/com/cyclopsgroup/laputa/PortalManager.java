package com.cyclopsgroup.laputa;

import java.util.List;

public interface PortalManager
{
    String BEAN_NAME = PortalManager.class.getName();

    List<WidgetType> getWidgetTypes();
}
