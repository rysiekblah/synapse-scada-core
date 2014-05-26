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
package com.synapse.scada.client;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.Calendar;

import com.synapse.scada.config.SystemConfig;

// TODO: Auto-generated Javadoc
/**
 * The Class ChoseItem.
 * 
 * @author Tomek Kozlowski (rysiekblah)
 * @version 1.00 (Nov 4, 2012)
 */
public class ChoseItem extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2451336774925586208L;

	/** The client. */
	private Client client;

	private String err = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() {

		try {
			client = new Client();
			client.init(this.getServletContext());
			client.connect();
		} catch (SynapseClientException e) {
			err = "Servlet init failed. " + e.getMessage();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		Long timestamp = Calendar.getInstance().getTimeInMillis();

		// take configuration
		SystemConfig cfg = null;

		if(client.getConnectionId()==null) {
			try {
				client.connect();
				err = "ok";
			} catch (SynapseClientException e1) {
				err = "Reconnect failed. " + e1.getMessage();
			}
		}

		cfg = client.getConfig();

		request.setAttribute("config", cfg);
		request.setAttribute("tstamp", timestamp);
		request.setAttribute("error", err);
		response.setHeader("Refresh", "2");

		RequestDispatcher view = request.getRequestDispatcher("sympaView.jsp");
		view.forward(request, response);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doPost(request, response);
	}

}
