import java.util.*;


class MailDomainList
{
	//Declarations
	//======================================
	ArrayList<MailDomain> mailDomainList;
	//======================================

	//==============================================================
	public MailDomainList()
	{
		mailDomainList = new ArrayList<MailDomain>();
	}
	//==============================================================


	//==============================================================
	public MailDomainList(MailDomain mailDomain)
	{
		mailDomainList.add(mailDomain);
	}
	//==============================================================


	//==============================================================
	public void add(MailDomain mailDomain)
	{
		mailDomainList.add(mailDomain);
	}
	//==============================================================


	//==============================================================
	public void sort()
	{
		for (int k = 0; k < mailDomainList.size(); k++)
		{
			mailDomainList.get(k).sort();
		}

		Collections.sort(mailDomainList);
	}
	//==============================================================


	//==============================================================
	public void display()
	{
		for (int k = 0; k < mailDomainList.size(); k++)
		{
			mailDomainList.get(k).display();
		}
	}
	//==============================================================
}