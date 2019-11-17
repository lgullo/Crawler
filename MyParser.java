import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;
import java.util.regex.*;


class MyParser extends HTMLEditorKit.ParserCallback
{
	//Declarations
	//==============================================================
	URLList 			list;
	WebURL				webURL;
	MailDomain 			mailDomain;

	boolean 			expansionTimerExpired;
	Pattern 			p;
	Matcher 			m;
	//==============================================================

	//Constructor
	//==========================================================================
	public MyParser(WebURL webUrl, URLList list, boolean expansionTimerExpired)
	{
		this.list = list;
		this.expansionTimerExpired = expansionTimerExpired;
		this.webURL = webUrl;
	}
	//==========================================================================


	//Email Parser
	//==========================================================================
	public void handleText (char[] data, int pos)
	{

		String html = new String(data);
		p = Pattern.compile("[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})");
		m = p.matcher(html);

		
		while (m.find())
		{
			System.out.println(m.group());
			String[] accountAndDomain;
			accountAndDomain = m.group().split("@");

			if (!webURL.contains(accountAndDomain[1]))
			{
				mailDomain = new MailDomain(accountAndDomain[0], accountAndDomain[1]);
				webURL.add(mailDomain);
			}

			else
			{
				webURL.getMailDomain(accountAndDomain[1]).add(accountAndDomain[0]);
			}
		}
		
		if (!list.urlList.contains(webURL))
		{
			list.add(webURL);
		}
	}
	//==========================================================================


	//Link Parser
	//==========================================================================
	public void handleStartTag (HTML.Tag tag, MutableAttributeSet attSet, int pos)
	{
		Object 			attribute;
		Enumeration<?> 	attributeEnum;

		if (tag == HTML.Tag.A && !expansionTimerExpired) 
		{
			attribute = attSet.getAttribute(HTML.Attribute.HREF);

			if (attribute != null)
			{

				if ( (attribute.toString().toUpperCase().contains("HTTP") || attribute.toString().toUpperCase().contains("WWW")) && !list.contains(attribute.toString()))
				{
					list.add(new WebURL(attribute.toString(), webURL.depth+1));
				}
				
				
				else 
				{
					if (!list.contains(webURL.url + attribute.toString()))
					{
						list.add(new WebURL(webURL.url + attribute.toString(), webURL.depth+1));
					}
				}
			
			}

		}
	}
	//==========================================================================
}