package logger;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.Handler;;

public class LogFormater extends Formatter
{

	@Override
	public String format(LogRecord record)
	{
		StringBuilder sb = new StringBuilder();
		StringBuffer text = new StringBuffer();

		if (record.getSourceClassName() != null)
		{
			sb.append(record.getSourceClassName());
		}
		else
		{
			sb.append(record.getLoggerName());
		}
		sb.append(" - "); // lineSeparator
		sb.append(record.getMessage());
		sb.append("\n");

		return sb.toString();
	}
	public String getHead(Handler h) {
        return super.getHead(h);
    }
 
    public String getTail(Handler h) {
        return super.getTail(h);
    }

}
