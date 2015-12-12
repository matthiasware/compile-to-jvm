package logger;

import java.util.logging.ConsoleHandler;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LoggerBuilder
{

	public static Logger getLogger(String s)
	{
		Logger LOGGER;
		LOGGER = Logger.getLogger(s);
		LOGGER.setUseParentHandlers(false);

		Handler consoleHandler = new ConsoleHandler();
		consoleHandler.setFilter(new Filter()
		{
			@Override
			public boolean isLoggable(LogRecord record)
			{
				return record.getSourceClassName().equals(parser.Parser.class.getName());
			}
		});

		LogFormater formater = new LogFormater();
		consoleHandler.setFormatter(formater);

		consoleHandler.setLevel(Level.ALL);

		LOGGER.setLevel(Level.ALL);
		LOGGER.addHandler(consoleHandler);
		return LOGGER;
	}
}
