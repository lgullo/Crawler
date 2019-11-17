import java.io.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.net.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;



public class Crawler
{
	public static void main (String[] x)
	{
		MainFrame prog;
		prog = new MainFrame();
	}
}



class MainFrame
implements ActionListener
{
	//Declarations
	//=====================================================================================================
	//Constants--------------------------------------------
	final String URL = "http://www.fermanchevy.com";
	final int MAX_RADIUS = 20;
	final int MAX_EXPANSION_TIME = 600;
	final int MAX_RUNTIME = 300;
	//-----------------------------------------------------
	//=====================================================================================================

	Timer 				expansionTimer;
	Timer 				runtimeTimer;

	boolean 			runtimeTimerExpired;
	boolean 			expansionTimerExpired;

	URL 				url;
	URLConnection 		urlConnection;
	InputStreamReader	isr;
	MyParser			tagHandler;

	URLList				list;
	WebURL 				webURL;
	//=====================================================================================================


	public MainFrame()
	{
		//Initializing Variables
		//=====================================================================================================
		expansionTimer = new Timer (1000 * MAX_EXPANSION_TIME, this);
		expansionTimer.setRepeats(false);
		expansionTimer.start();

		runtimeTimer = new Timer (1000 * MAX_RUNTIME, this);
		runtimeTimer.setRepeats(false);
		runtimeTimer.start();

		//URL List
		list = new URLList();

		//Starting WebURL Object
		webURL = new WebURL(URL,0);
		list.add(webURL);

		//Url's Processed Counter (k)
		int k = 0;
		//=====================================================================================================

		
		//Crawling Loop
		//=====================================================================================================
		while (k < list.urlList.size() && !runtimeTimerExpired && webURL.depth <= MAX_RADIUS)
		{
			
			try
			{
				System.out.println("----------------------------------------------");
				System.out.println("\nURL's To Be Processed: " + list.urlList.size());
				System.out.println("URL's DONE Processing: " + k + "\n");
				System.out.println("----------------------------------------------");
				k++;
				url = new URL(webURL.url);
				urlConnection = url.openConnection();

				urlConnection.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
				urlConnection.setRequestProperty("Accept","*/*");

				isr = new InputStreamReader (urlConnection.getInputStream());

				tagHandler = new MyParser(webURL,list,expansionTimerExpired);

				new ParserDelegator().parse(isr, tagHandler, true);
			}



			catch (MalformedURLException mue)
			{
				System.out.println("Bad URL");
				mue.printStackTrace();
			}

			catch (IOException ioe)
			{
				System.out.println("Some IO Exception...");
				ioe.printStackTrace();
			}

			webURL = list.nextURL();
		}
		//=====================================================================================================


		//Display List
		//=====================================================================================================
		list.sort();

		if (runtimeTimerExpired)
		{
			System.out.println("\nMax Runtime Completed  |  Total URL List Size: "+ list.urlList.size() + "  |  URL's Processed: " + k);
			System.out.println("==================================================================");
		}

		else if (webURL.depth >= MAX_RADIUS)
		{
			System.out.println("\nMax Radius Met  |  Total URL List Size: "+ list.urlList.size() + "  |  URL's Processed: " + k);
			System.out.println("==================================================================");
		}

		else if (k >= list.urlList.size() && runtimeTimerExpired)
		{
			System.out.println("\nExpansion Timer Expired  |  Total URL List Size: "+ list.urlList.size() + "  | URL's Processed: " + k);
			System.out.println("==================================================================");
		}

		else if (k >= list.urlList.size())
		{
			System.out.println("\nAll URL's Crawled & Processed | Total URL List Size: "+ list.urlList.size() + " | URL's Processed: " + k);
			System.out.println("==================================================================");
		}

		else
		{
			System.out.println("\nTotal URL List Size: "+ list.urlList.size() + "  | URL's Processed: " + k);
			System.out.println("==================================================================");
		}

		list.display();
		
		//=====================================================================================================
	}


	//Action Performed
	//=====================================================================================================
	public void actionPerformed (ActionEvent ae)
	{
		if (ae.getSource() == runtimeTimer)
		{
			runtimeTimerExpired = true;
		}

		else if (ae.getSource() == expansionTimer)
		{
			expansionTimerExpired = true;
		}
	}
	//=====================================================================================================

}



