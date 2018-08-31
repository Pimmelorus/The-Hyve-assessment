package nl.thehyve.streammanipulator.algorithm;

import java.util.ArrayList;
import java.util.List;

public class TrivialCompressor implements CompressorInterface {
	
	List<Byte> outbuffer = new ArrayList<Byte>();

	@Override
	public Byte[] encode(Byte[] stream) {
		
		// Note: error byte as defined by the Compressor class can be part of the stream
		// the assumption here is that erroneous bytes should be written as a normal byte
		// and are propagated as list of error bytes in the encoded stream a.k.a there is 
		// no error handling
		for (byte b: stream) {
			outbuffer.add((byte)0);
			outbuffer.add(b);
		}
		
		return (Byte[]) outbuffer.toArray(new Byte[outbuffer.size()]);
	}

}
