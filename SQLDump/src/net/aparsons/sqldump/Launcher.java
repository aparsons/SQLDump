package net.aparsons.sqldump;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.aparsons.sqldump.db.Connectors.Connector;
import net.aparsons.sqldump.db.SQLDump;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Launcher {

	/**
	 * Generate apache-cli options:
	 *  DB protocol (Mandatory), 
	 *  address (Mandatory),
	 *  user (Mandatory),
	 *  username (Mandatory),
	 *  password (Mandatory), 
	 *  query (Mandatory),
	 *  CSV file path
	 *  includeHeaders

	 * 
	 * @return Available command line options
	 */
	public static Options getOptions() {
		final Options options = new Options();
		
		 
		final Option urlOption = new Option("url", true, "A database url of the form jdbc:subprotocol:subname");
		
		final OptionGroup urlGroup = new OptionGroup();
		urlGroup.setRequired(true);
		urlGroup.addOption(urlOption);
		options.addOptionGroup(urlGroup);
		//Complete the option specification code 
		//HERE
		//

		return options;
	}

	/**
	 * Main method parse the command line parameters and call the businees logic
	 * @param args Command line arguments
	 * @throws Exception 
	 */
	public static void main(String[] args) {
		try {
			CommandLineParser parser = new GnuParser();
			CommandLine cmdLine = parser.parse(getOptions(), args);
			if(!cmdLine.hasOption("url")) throw new ParseException("No url is specifified");
			String url = cmdLine.getOptionValue("url");
			
			//Complete the parameter parser code 
			//HERE
			//
			
			String username = "";
			String password = "";
			String sql = "";
			String file = "";
			String protocol = "";
			boolean includeHeaders = false;
			businessLogic(protocol, url, username, password, sql, file, includeHeaders);
		} catch (ParseException pe) {
			printUsage();
		}
	}
	/**
	 * Invoke the business logic
	 */
	private static void businessLogic(String protocol, String url, String username, String password, String sql, String file, boolean includeHeaders) {
		SQLDump dump = new SQLDump(Connector.valueOf(protocol), url, username, password, sql);
		if (!file.isEmpty())
			dump.setFile(new File(file));
		if (includeHeaders) 
			dump.setHeaders(true);
		dump.run();
	}

	/**
	 * Prints the command line options to the console
	 */
	public static void printUsage() {
		HelpFormatter printer = new HelpFormatter();
		//Complete the helper code 
		//HERE
		//
	}

}
