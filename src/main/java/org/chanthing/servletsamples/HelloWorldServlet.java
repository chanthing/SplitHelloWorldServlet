package org.chanthing.servletsamples;;

import java.io.IOException;
import java.net.URISyntaxException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.split.client.SplitClient;
import io.split.client.SplitClientConfig;
import io.split.client.SplitFactory;
import io.split.client.SplitFactoryBuilder;

public class HelloWorldServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;
	private static SplitClientConfig config;
	private static SplitClient client;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession ssn = request.getSession();
		String treatment = "neitheroffnoron";

		if (ssn != null) {
		    treatment = client.getTreatment(ssn.getId(),"HelloSpanish");
		}

		if (treatment.equals("on")) {
		    // insert code here to show on treatment
		    response.getWriter().println("On: Hola Mundo!");
		} else if (treatment.equals("off")) {
		    // insert code here to show off treatment 
		    response.getWriter().println("Off: Hello World!");
		} else {
		    // insert your control treatment code here
		    response.getWriter().println("Control: WTF?");
		}
	}

	@Override
	public void init() throws ServletException {
		SplitFactory splitFactory;

		try {
		    config = SplitClientConfig.builder().setBlockUntilReadyTimeout(10000).build();
		    splitFactory = SplitFactoryBuilder.build("sijq9h05fmubqciocodvp37022ok0ffq15c9", config);
		    client = splitFactory.client();
		} catch (IOException | URISyntaxException ex) {
		    System.err.println(ex + " In Split Code");
		}
		System.out.println("Servlet " + this.getServletName() + " has started");
	}

	@Override
	public void destroy() {
		System.out.println("Servlet " + this.getServletName() + " has stopped");
	}
}
