package hu.iboard.coandco.datamagic.util;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamDecoder extends InputStream
{	
	private InputStream is;
	private Decoder decoder;
	
	public InputStreamDecoder(InputStream is, Decoder decoder)
	{
		this.is = is;
		this.decoder = decoder;
	}

	@Override
	public int read() throws IOException
	{
		int x = is.read();
		return (Integer) decoder.decode(x);
	}

}
