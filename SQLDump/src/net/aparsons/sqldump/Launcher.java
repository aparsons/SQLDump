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
	 * Retrieves available command line options
	 * 
	 * @return Available command line options
	 */
	public static Options getOptions() {
		final Options options = new Options();
		
		// Required 
		final Option urlOption = new Option("url", true, "A database url of the form jdbc:subprotocol:subname");
		final Option userOption = new Option("user", "username" , true, "The database user on whose behalf the connection is being made");
		final Option passOption = new Option("pass", "password" , true, "The user's password");
		final Option sqlOption = new Option("sql", "query" , true, "Any SQL statement");
		
		// Optional
		final Option fileOption = new Option("f", "file", true, "File path of the report");
		final Option headersOption = new Option("headers","include-headers", false, "Include column headers in generated file");
		//final Option verboseOption = new Option("v", "verbose", true, "Be extra verbose");
		
		final OptionGroup urlGroup = new OptionGroup();
		urlGroup.setRequired(true);
		urlGroup.addOption(urlOption);
		options.addOptionGroup(urlGroup);
		
		final OptionGroup userGroup = new OptionGroup();
		userGroup.setRequired(true);
		userGroup.addOption(userOption);
		options.addOptionGroup(userGroup);
		
		final OptionGroup passGroup = new OptionGroup();
		passGroup.setRequired(true);
		passGroup.addOption(passOption);
		options.addOptionGroup(passGroup);
		
		final OptionGroup sqlGroup = new OptionGroup();
		sqlGroup.setRequired(true);
		sqlGroup.addOption(sqlOption);
		options.addOptionGroup(sqlGroup);

		options.addOption(fileOption);
		options.addOption(headersOption);
		// TODO Add verbose option
		
		return options;
	}
	
	/**
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		try {
			CommandLineParser parser = new GnuParser();
			try {
				CommandLine cmdLine = parser.parse(getOptions(), args);
				
				if (cmdLine.hasOption("url") && cmdLine.hasOption("username") && cmdLine.hasOption("password") && cmdLine.hasOption("sql")) {
					String url = cmdLine.getOptionValue("url");					
					String username = cmdLine.getOptionValue("username");
					String password = cmdLine.getOptionValue("password");
					String sql = cmdLine.getOptionValue("sql");
					
					String[] urlArray = url.split(":");
					SQLDump dump = new SQLDump(Connector.valueOf(urlArray[1].toUpperCase()) ,url, username, password, sql);
					
					if (cmdLine.hasOption("file")) {
						dump.setFile(new File(cmdLine.getOptionValue("file")));
					}
					
					if (cmdLine.hasOption("include-headers")) {
						dump.setHeaders(true);
					}
					
					dump.run();
				}
			} catch (ParseException pe) {
				Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, "Invalid command-line parameter", pe);
				printUsage();
			}
		} catch (Throwable t) {
			Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, "Error", t);
		}

	}
	
	/**
	 * Prints the command line options to the console
	 */
	public static void printUsage() {
		new HelpFormatter().printHelp("java -jar SQLDump-" + SQLDump.VERSION + ".jar -url [arg] -user [arg] -pass [arg] -sql [arg]", getOptions());
	}

}
