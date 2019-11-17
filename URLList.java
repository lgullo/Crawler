import java.util.*;


class URLList
{	
	//Declarations
	//=============================
	ArrayList<WebURL> urlList;
	int k = 0;
	//=============================

	//Default Constructor
	//==============================================================
	public URLList()
	{
		urlList = new ArrayList<WebURL>();
	}
	//==============================================================


	//Constructor
	//==============================================================
	public URLList(WebURL webURL)
	{
		this.add(webURL);
	}
	//==============================================================


	//Returns The Next URL in the List
	//==============================================================
	public WebURL nextURL()
	{
		k++;
		return urlList.get(k-1);
	}
	//==============================================================


	//Boolean Contains Method
	//==============================================================
	public boolean contains(String url)
	{
		boolean containsURL = false;

		for (int i = 0; i < urlList.size(); i++)
		{
			if (urlList.get(i).url.equals(url))
			{
				containsURL = true;
			}
		}

		return containsURL;
	}
	//==============================================================


	//Add Method
	//==============================================================
	public void add(WebURL webURL)
	{	
		if (webURL != null)
		{
			urlList.add(webURL);
		}

		else
			System.out.println("Null ptr");	
	}
	//==============================================================


	//Sort Method
	//==============================================================
	public void sort()
	{
		for (int k = 0; k < urlList.size(); k++)
		{
			urlList.get(k).sort();
		}

		Collections.sort(urlList);		
	}
	//==============================================================


	//Display Method
	//==============================================================
	public void display()
	{
		for (int k = 0; k < urlList.size(); k++)
		{
			urlList.get(k).display();
		}
	}
	//==============================================================
}