package program;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Scan {
	WebClient client;
	WebClientOptions options;
	
	public HashMap<HtmlAnchor, Boolean> pageMap;
	public String baseURL;
	
	public static void main(String[] args){
		try {
			Scan scan = new Scan("http://egi.utah.edu");
			
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public Scan(String url) throws FailingHttpStatusCodeException, MalformedURLException, IOException{
		
		clientSetup();
		
		HtmlPage page = client.getPage(url);
		baseURL = page.getBaseURL().toString();
		
		pageMap = new HashMap<>();
		
		addLinksToMap(page);
		checkLinks(pageMap);
	}
	
	private void checkLinks(HashMap<HtmlAnchor, Boolean> map) {
		for(Entry<HtmlAnchor, Boolean> entry : map.entrySet())
		{
			HtmlAnchor a = entry.getKey();
			boolean checked = entry.getValue();
			
			if(!checked){
				try {
					a.click();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	private void addLinksToMap(HtmlPage page) {
		
		List<HtmlAnchor> list = page.getAnchors();
		
		int count = 0;
		for(HtmlAnchor a : list){
			String href = a.getHrefAttribute();
			
			if(!pageMap.containsKey(href)){
				// False represents not being tested
				pageMap.put(a, false);
				count++;
			}
		}
		System.out.println(count);
	}

	private void clientSetup(){
		// Setup
		client = new WebClient();
		options = client.getOptions();
		options.setJavaScriptEnabled(false);
		options.setCssEnabled(false);
	}

}
