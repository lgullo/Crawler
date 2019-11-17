import java.util.*;


class MailDomain
implements Comparable<MailDomain>
{	
	//Declarations
	//================================
	String domain;
	ArrayList<String> accountNames;
	//================================


	//Constructor
	//==============================================================
	public MailDomain(String accountName, String domain)
	{
		this.domain = domain;
		accountNames = new ArrayList<String>();
		accountNames.add(accountName);
	}
	//==============================================================


	//Add Method
	//==============================================================
	public void add(String accountName)
	{
		if (!accountNames.contains(accountName))
		{
			accountNames.add(accountName);
		}
	}//==============================================================


	//Display Method
	//==============================================================
	public void display()
	{
		for (int k = 0; k < accountNames.size(); k++) 
		{
			System.out.println(accountNames.get(k) + "@" + domain + "\n");
		}
	}
	//==============================================================


	//Sort Method
	//==============================================================
	public void sort()
	{
		Collections.sort(accountNames,String.CASE_INSENSITIVE_ORDER);
	}
	//==============================================================


	//Implemented Comparable Method
	//==============================================================
	@Override
	public int compareTo(MailDomain object)
	{
		return this.domain.compareToIgnoreCase(object.domain);
	}
	//==============================================================

}