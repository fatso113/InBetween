package com.edu.BSU.inbetween.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.BSU.inbetween.activities.SettingsActivity;
import android.content.res.AssetManager;
import android.util.Log;

public class CardXMLParser extends SettingsActivity
{
	private static ArrayList<Card> deck = new ArrayList<Card>();
	private static AssetManager assetFiles;

	public CardXMLParser(AssetManager assetManager)
	{
		assetFiles = assetManager;
	}

	public static void setAssetFiles(AssetManager manager)
	{
		assetFiles = manager;
	}
	
	public static ArrayList<Card> generateFiftyTwoCardDeck()
	{
		loadStandardDeckXMLFile();
		return deck;
	}
	
	private static void loadStandardDeckXMLFile()
	{
		InputStream is = null;
		try {
			is = assetFiles.open("xml/standard.xml");
		} catch (IOException e) {
			System.out.println(e);
		}
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		
		try
		{
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(is);
			
			NodeList listOfCardNodes = doc.getElementsByTagName("Card");
			
			for(int i = 0; i < listOfCardNodes.getLength(); i++)
		{
				Node tempNode = listOfCardNodes.item(i);
				
				Card createdCard = new Card();
				Element cardElement = (Element) tempNode;
				
				createdCard.setSuit(determineSuitFromString(cardElement.getElementsByTagName("Suit").item(0).getTextContent()));
				createdCard.setValue(Integer.valueOf(cardElement.getElementsByTagName("Value").item(0).getTextContent()));
				
				deck.add(createdCard);
			}
		}
		
		catch(Exception e)
		{
			Log.i("XMLParser","XMLParser error");
			//log to a text file at least
		}
	}

	private static Suit determineSuitFromString(String suitString)
	{
		if(suitString.equals("Hearts"))
		{
			return Suit.HEARTS;
		}
		
		else if(suitString.equals("Diamonds"))
		{
			return Suit.DIAMONDS;
		}
		
		else if(suitString.equals("Clubs"))
		{
			return Suit.CLUBS;
		}
		
		else
		{
			return Suit.SPADES;
		}
	}
}