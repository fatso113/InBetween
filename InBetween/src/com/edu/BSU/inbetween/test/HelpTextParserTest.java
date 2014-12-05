package com.edu.BSU.inbetween.test;

import org.junit.Test;

import com.edu.BSU.inbetween.common.HelpTextParser;

import junit.framework.TestCase;

public class HelpTextParserTest extends TestCase {

	@Test
	public void test(){
		String filename="Rules.txt";
		String output=null;
		output=HelpTextParser.parseHelpFile(filename);
		assertNotNull(output);
		System.out.print(output);
	}
}
