package nl.thehyve.streammanipulator.algorithm;


import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.Test;

class EfficientCompressorTest {

	@Test
	void testFindPatternSimple() {
		assertArrayEquals(new Integer[]{1}, EfficientCompressor.findBytePattern(new Byte[] {62, 61, 62, 61}, 0, 3));
	}
	
	@Test
	void testFindPatternComplex() {
		assertArrayEquals(new Integer[]{1}, EfficientCompressor.findBytePattern(new Byte[] {62, 61, 62, 61}, 1, 3));
	}


}
