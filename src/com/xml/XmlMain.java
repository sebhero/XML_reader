package com.xml;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlMain
{

	public static void main(String[] filepaths)
	{
		// just for testing
		// filepaths = new String[1];
		// filepaths[0] = Paths.get("", "teckning.xml").toAbsolutePath().toString();
		// System.out.println(filepaths[0]);
		if (filepaths.length < 1)
		{
			System.out.println("Please provide a filepath for the xml file and only one");
		}
		else
		{

			final XMLReader xmlReader = new XMLReader(filepaths[0]);
			try
			{
				// validate the file
				final Document doc = xmlReader.validateXML();
				// find the elements
				xmlReader.findElements(doc);

				// display the count of found elements
				System.out.println(xmlReader);
			}
			catch (final ParserConfigurationException e)
			{
				System.out.println("Main handling exception");
				System.out.println("Problem parseing the xml file");
				// e.printStackTrace();
			}
			catch (final SAXException e)
			{
				System.out.println("Main handling exception");
				System.out.println("Problem with the DTD and XML formating\nplease check files");
				// e.printStackTrace();
			}
			catch (final IOException e)
			{
				System.out.println("Main handling exception");
				System.out.println("Problem with File, is the path correct!?\n is it a xml file!?");
				// e.printStackTrace();
			}
		}

	}

}
