package it.univa.disim.focus.evaluation.tika.evaluation.tika;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.langdetect.OptimaizeLangDetector;
import org.apache.tika.language.detect.LanguageDetector;
import org.apache.tika.language.detect.LanguageResult;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 * Hello world!
 *
 */
public class App {
	final static Logger logger = LoggerFactory.getLogger(App.class);

	public static String parseToStringExample(String path) throws IOException, SAXException, TikaException {
		Tika tika = new Tika();
		try (InputStream stream = Files.newInputStream(Paths.get(path), StandardOpenOption.READ)) {
			return tika.parseToString(stream);
		}
	}

	public static String detectType(String path) throws IOException {
		Tika tika = new Tika();
		try (InputStream stream = Files.newInputStream(Paths.get(path), StandardOpenOption.READ)) {
			String filetype = tika.detect(stream);
			return filetype;
		}
	}

	public static String autoParse(String path) throws IOException, SAXException, TikaException {
		try (InputStream inputstream = Files.newInputStream(Paths.get(path), StandardOpenOption.READ)) {
			Parser parser = new AutoDetectParser();
			BodyContentHandler handler = new BodyContentHandler();
			Metadata metadata = new Metadata();
			ParseContext context = new ParseContext();
			parser.parse(inputstream, handler, metadata, context);
			return handler.toString();
		}
	}

	public static String extractMetadata(String path) throws IOException, TikaException, SAXException {
		try (InputStream inputstream = Files.newInputStream(Paths.get(path), StandardOpenOption.READ)) {
			Parser parser = new AutoDetectParser();
			BodyContentHandler handler = new BodyContentHandler(-1);
			Metadata metadata = new Metadata();
			ParseContext context = new ParseContext();
			parser.parse(inputstream, handler, metadata, context);
			String[] metadataNames = metadata.names();
			StringBuilder sb = new StringBuilder();
			for (String name : metadataNames) {
				sb.append(name + " " + metadata.get(name) + "\n");
			}
			return sb.toString();
		}
	}

	public static String languageIdentifier(String text) throws IOException, SAXException, TikaException {
		LanguageDetector identifier = new OptimaizeLangDetector().loadModels();
		LanguageResult language = identifier.detect(text);
		return language.getLanguage();
	}
}
