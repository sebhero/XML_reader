package com.xml;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XMLReader implements AntalRitobjekt
{

	private Path theFilepath = null;
	int ritobjekt, punkter, linjer, rektanglar, ovaler;

	/***
	 * Reads xml files
	 */
	public XMLReader()
	{
	}

	/***
	 * Reads a xml file with provided path
	 * 
	 * @param filepath
	 */
	public XMLReader(String filepath)
	{

		setTheFilepath(Paths.get(filepath));

	}

	/***
	 * Goes throw the document and finds all the rit elements
	 * 
	 * @param doc
	 */
	public void findElements(Document doc)
	{
		final NodeList nodeListRitobjekt = doc.getElementsByTagName("ritobjekt");
		final NodeList nodeListPunkt = doc.getElementsByTagName("punkt");
		final NodeList nodeListLinje = doc.getElementsByTagName("linje");
		final NodeList nodeListRektangel = doc.getElementsByTagName("rektangel");
		final NodeList nodeListOval = doc.getElementsByTagName("oval");

		ritobjekt = nodeListRitobjekt.getLength();
		punkter = nodeListPunkt.getLength();
		linjer = nodeListLinje.getLength();
		rektanglar = nodeListRektangel.getLength();
		ovaler = nodeListOval.getLength();

	}

	/***
	 * Loads the xml file and validates its dtd attributes throws exeption for
	 * file and problem validating the file.
	 * 
	 * @param theFilepath
	 *          the filepath
	 * @return the loaded xml document
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public Document validateXML(Path theFilepath) throws ParserConfigurationException, SAXException, IOException
	{
		final DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setValidating(true);
		DocumentBuilder builder;
		builder = domFactory.newDocumentBuilder();

		builder.setErrorHandler(new ErrorHandler()
		{

			@Override
			public void warning(SAXParseException arg0) throws SAXException
			{
				System.out.println("Sax WARNING");
				throw arg0;
				// arg0.printStackTrace();

			}

			@Override
			public void fatalError(SAXParseException arg0) throws SAXException
			{
				System.out.println("SAX FATAL ERROR");
				throw arg0;
				// arg0.printStackTrace();
			}

			@Override
			public void error(SAXParseException arg0) throws SAXException
			{
				System.out.println("SAX ERROR");
				throw arg0;
				// arg0.printStackTrace();
			}
		});

		final Document doc = builder.parse(theFilepath.toFile());
		return doc;
	}

	@Override
	public int antalRitobjekt()
	{
		return ritobjekt;
	}

	@Override
	public int antalPunkter()
	{
		return punkter;
	}

	@Override
	public int antalLinjer()
	{
		return linjer;
	}

	@Override
	public int antalRektanglar()
	{
		return rektanglar;
	}

	@Override
	public int antalOvaler()
	{
		return ovaler;
	}

	/***
	 * Validates the xml file with the dtd file. The filepath must be set before
	 * this is run
	 * 
	 * @return document from the xml file
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public Document validateXML() throws ParserConfigurationException, SAXException, IOException
	{
		if (getTheFilepath() != null)
		{
			return validateXML(getTheFilepath());
		}
		else
		{
			throw new IOException("File not found/set");
		}

	}

	/**
	 * @return the theFilepath
	 */
	public Path getTheFilepath()
	{
		return theFilepath;
	}

	/**
	 * @param theFilepath
	 *          the theFilepath to set
	 */
	public void setTheFilepath(Path theFilepath)
	{
		this.theFilepath = theFilepath;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return MessageFormat
				.format(
						"{0} contains total of {1} ritobjekt\nAntal punkter\t\t{2}\nAntal linjer\t\t{3}\nAntal rektanglar\t{4}\nAntal ovaler\t\t{5}\n",
						this.getTheFilepath().getFileName(), this.antalRitobjekt(), this.antalPunkter(), this.antalLinjer(),
						this.antalRektanglar(), this.antalOvaler());
	}

}
