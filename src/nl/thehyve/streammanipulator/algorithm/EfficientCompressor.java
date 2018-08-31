package nl.thehyve.streammanipulator.algorithm;

import java.util.ArrayList;
import java.util.List;

public class EfficientCompressor implements CompressorInterface {

	List<Byte> outbuffer = new ArrayList<Byte>(); // holds the compressed byte stream

	@Override
	public Byte[] encode(Byte[] stream) {

		// Note: error byte as defined by the Compressor class can be part of the stream
		// the assumption here is that erroneous bytes should be written as a normal byte
		// and are propagated as list of error bytes in the encoded stream a.k.a there is 
		// no error handling

		// start at list
		int limitRight = stream.length-1; // is the rightmost position of the pattern to be matched
		int patternLeftOffset = 0; // this the offset to the left from the limitRight
		while (limitRight >= 0) {
			int numHitsPrevious = 0;
			int numHitsCurrent = 0;
			int deltaHits= 0;
			// increase the pattern size until there is a minimum number of hits
			do {
				
				// check byte array bounded by limitRight and limitLeft
				Integer[] matches = findBytePattern(stream, patternLeftOffset, limitRight);
			} while (deltaHits >= 0);
			
			limitRight -= patternLeftOffset+1;
		}
		return stream; // TODO change
		
	}
	
	// TODO untested
	protected static Integer[] findBytePattern(Byte[] stream, int patternLeftOffset, int streamRightLimit) {
		
		// if there are no elements return empty array
		if (streamRightLimit == 0)
			return new Integer[0];
		
		// find positions of first byte of the pattern
		List<Integer> seeds = new ArrayList<Integer>();
		int patternLeftLimit = streamRightLimit - patternLeftOffset;
		int patternLength = patternLeftOffset+1;
		byte seedByte = stream[patternLeftLimit];
		for (int i = 0; i < streamRightLimit; i++) {
			if (stream[i] == seedByte) {
				boolean match = true;
				// if the byte has been found check if the pattern matches
				for (int j = 0; j < patternLength; j++){
					if (stream[i+j] != stream[patternLeftLimit+j])
						match = false;
				}
				if (match)
					seeds.add(i);
			}
		}

		return seeds.toArray(new Integer[0]); 
	} 


}
