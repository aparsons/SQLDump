package it.univa.disim.focus.evaluation.tika.evaluation.tika;

import static org.junit.Assert.assertNotNull;
import java.io.IOException;
import org.apache.tika.exception.TikaException;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 * Unit test for simple App.
 */

public class AppTest {

	final static String _DocFile = "doc.docx";

	
	@Test
	public void testGenericFile() throws IOException, SAXException, TikaException {
		assertNotNull(App.parseToStringExample(_DocFile));
	}

	@Test
	public void testDectecType() throws IOException, SAXException, TikaException {
		assertNotNull(App.detectType(_DocFile));
	}

	@Test
	public void testAutoParse() throws IOException, SAXException, TikaException {
		assertNotNull(App.autoParse(_DocFile));
	}

	@Test
	public void testExtractMetadata() throws IOException, SAXException, TikaException {
		assertNotNull(App.extractMetadata(_DocFile));
	}

	@Test
	public void testLanguageIdentifier() throws IOException, SAXException, TikaException {
		String text = App.parseToStringExample(_DocFile);
		assertNotNull(App.languageIdentifier(text));
	}

}
