package org.workingonbits.transformer.internal.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.core.internal.registry.osgi.Activator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.workingonbits.transformer.api.TransformerService;

/**
 * Servlet which catch http request and print the test message by using
 * a transformer
 * @author Eduardo Fernández <eduardo@workingonbits.com>
 *
 */
public class Servlet extends HttpServlet {
	
	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 1L;	
	
	/**
	 * Retrieves the transformer service that matches with the filter
	 * passed by parameter
	 * @param type
	 * @return TransformerService
	 */
	private TransformerService lookupService(String type) {
		BundleContext context = Activator.getContext();
		ServiceReference[] references;
		String filter = "(type=" + type + ")";
		try {
			references = context.getAllServiceReferences(TransformerService.class.getName(), filter);
		} catch (InvalidSyntaxException e) {
			return null;
		}
		
		if(references != null){
			return (TransformerService) context.getService(references[0]);
		} else {
			return null;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			
		PrintWriter out = resp.getWriter();
		String type = req.getParameter("to");
		
		TransformerService service = lookupService(type);
		if(service != null){
			out.append(service.transform("Testing message"));
		} else {
			out.append("Sorry, but there isn't any services to transform to " + type);
		}
	}

}
