package program;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Scan {

	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		WebClient client = new WebClient();
		
		WebClientOptions options = client.getOptions();
		options.setJavaScriptEnabled(false);
		options.setCssEnabled(false);
		
		HtmlPage page = client.getPage("http://egi.utah.edu");
		
		String baseURL = page.getBaseURL().toString();
		
		List<HtmlAnchor> list = page.getAnchors();
		
		for(HtmlAnchor a : list){
			System.out.println(a.getHrefAttribute());
		}
		
	}

}
