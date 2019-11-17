


class WebURL
implements Comparable<WebURL>
{
	//Declarations
	//=============================
	String 			url;
	MailDomainList 	list;
	int 			depth;
	//=============================

	//==============================================================
	public WebURL(String url, int depth)
	{
		this.url = url;
		list = new MailDomainList();
	}
	//==============================================================


	//==============================================================
	public void add(MailDomain mailDomain)
	{
		list.add(mailDomain);
	}
	//==============================================================

	//Returns (MailDomain) that has the domain equal to the arguement passed down
	//==============================================================
	public MailDomain getMailDomain(String domain)
	{
		for (int k = 0; k < list.mailDomainList.size(); k++)
		{
			if (list.mailDomainList.get(k).domain.equals(domain))
				return list.mailDomainList.get(k);
		}

		return null;
	}
	//==============================================================

	//Display Method
	//==============================================================
	public void display()
	{
		if (list.mailDomainList.size() > 0)
		{
			System.out.println("----------------------------------------");
			System.out.println("Email Accounts Within This URL: " + url);
			System.out.println("----------------------------------------");
			list.display();
			System.out.println("\n");
		}
	}
	//==============================================================

	//Returns True if the email domain already exists in the mail domain list
	//==============================================================
	public boolean contains(String domain)
	{
		boolean contains = false;

		for (int k = 0; k < list.mailDomainList.size(); k++)
		{
			if (list.mailDomainList.get(k).domain.equalsIgnoreCase(domain))
				contains = true;
		}

		return contains;
	}
	//==============================================================

	//Sort Method
	//==============================================================
	public void sort()
	{
		list.sort();
	}
	//==============================================================

	//Implemented Comparable Method
	//==============================================================
	@Override
	public int compareTo(WebURL object)
	{
		return this.url.compareToIgnoreCase(object.url);
	}
	//==============================================================
}