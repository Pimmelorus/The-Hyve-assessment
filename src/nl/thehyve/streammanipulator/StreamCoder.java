package nl.thehyve.streammanipulator;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import nl.thehyve.streammanipulator.algorithm.CompressorInterface;
import nl.thehyve.streammanipulator.algorithm.Decompressor;
import nl.thehyve.streammanipulator.algorithm.DecompressorInterface;
import nl.thehyve.streammanipulator.algorithm.EfficientCompressor;
import nl.thehyve.streammanipulator.algorithm.TrivialCompressor;

public class StreamCoder {
	
	
	private static DecompressorInterface decompressor = new Decompressor();
	private static CompressorInterface compressor = new EfficientCompressor();
	
	public static void run(InputStream in, OutputStream out) {
		
		if (useTrivialCompression())
			compressor = new TrivialCompressor();

		// wrap the inputstream to make it buffered for better efficiency
		in = new BufferedInputStream(in);

		// wrap the outputstream to make it buffered for better efficiency
		out = new BufferedOutputStream(out);

		try {
			
			int p, q;
			// read bytes from stdin until empty
			while ((p = in.read()) != -1) {
				// read the next byte when available
				if ((q = in.read()) != -1) {
					decompressor.put((byte)p, (byte)q);
				}
			}
			
			Byte[] compressedStream = compressor.encode(decompressor.getBuffer());
			
			// write decompressed bytestream to st out
			for (Byte b: decompressor.getBuffer()) {
				out.write(b);
			}
			
			// write compressed bytestream to std err.
			for (Byte b: compressedStream) {
				System.err.write(b);
			}

		}		
		catch (IOException e) {
			handleError(e);
		}
		finally {
			// close resources as the final step
			close(in);
			close(out);
		}

	}

	private static void close(Closeable c) {
		try {
			if (c != null)
				c.close();
		} catch (IOException e) {
			handleError(e);
		}
	}
	
	private static void handleError(Exception e) {
		// I am aware that there is output expected to the std err
		// I log errors to the std err anyway since this output is 
		// meaningless anyway when there is an error.
		e.printStackTrace();
	}
	
	private static boolean useTrivialCompression() {
		String value = System.getenv("USE_TRIVIAL_IMPLEMENTATION");
		if (value.equals("1"))
			return true;
		return false;
	}
}
