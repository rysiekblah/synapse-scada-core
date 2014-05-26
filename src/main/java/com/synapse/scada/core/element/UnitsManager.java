/********************************************************************************
 * 
 *  Copyright 2012 Synapse SCADA team
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.synapse.scada.core.element;

import javax.management.JMX;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;

import org.apache.log4j.Logger;

import com.synapse.scada.core.jmx.JmxHelper;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving element events. The class that is
 * interested in processing a element event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addElementListener<code> method. When
 * the element event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see ElementEvent
 */
public class UnitsManager implements NotificationListener {

	/** The LOG. */
	static Logger LOG = Logger.getLogger(UnitsManager.class);

	/** The element obj name. */
	private ObjectName elementObjName;

	/**
	 * Gets the element obj name.
	 * 
	 * @return the element obj name
	 */
	public ObjectName getElementObjName() {
		return elementObjName;
	}

	/**
	 * Sets the element obj name.
	 * 
	 * @param elementObjName
	 *            the new element obj name
	 */
	public void setElementObjName(ObjectName elementObjName) {
		this.elementObjName = elementObjName;
		LOG.debug("Listener of: " + this.elementObjName.getCanonicalName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.management.NotificationListener#handleNotification(javax.management
	 * .Notification, java.lang.Object)
	 */
	@Override
	public void handleNotification(Notification notification, Object handback) {
		LOG.debug("Nofitication received: " + notification.getType() + ",  "
				+ elementObjName.getCanonicalName());

		try {
			JMX.newMBeanProxy(JmxHelper.Instance().getMBServer(),
					elementObjName, ElementMBean.class).queryAllUnits();
		} catch (Exception ex) {
			LOG.error("Handling failed.", ex);
		}
	}
}
