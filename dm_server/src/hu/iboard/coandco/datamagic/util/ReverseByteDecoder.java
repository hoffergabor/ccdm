package hu.iboard.coandco.datamagic.util;

import org.apache.log4j.Logger;


public class ReverseByteDecoder implements Decoder {
	protected Logger logger = Logger.getLogger(this.getClass());

	public ReverseByteDecoder() {

	}

	@Override
	public Object decode(Object arg) {
		Integer x = new Integer(arg.toString());
		if (x == -1) // EOF
		{
			return x;
		}
		if (x < 0)
			logger.error("ERROR: arg is negative " + x + " transfer mode is ascii?");
		return 255 - x;
	}

	@Override
	public Object encode(Object arg) {
		Integer x = new Integer(arg.toString());
		if (x == -1) // EOF
		{
			return x;
		}
		if (x < 0)
			logger.error("ERROR: arg is negative " + x + " transfer mode is ascii?");
		return 255 - x;
	}

}
