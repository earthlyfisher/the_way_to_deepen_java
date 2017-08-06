package com.earthlyfish.encrypt;

public class CodecUtils {

    static final byte[] CHUNK_SEPARATOR = "\r\n".getBytes();
    private static final byte[] lookUpBase64Alphabet = new byte[64];

    /**
     * Base64编码，将原来的每三个字节扩展为四个字节.
     * 
     * @param binaryData
     * @param isChunked
     * @return
     */
    public static byte[] encodeBase64(byte[] binaryData, boolean isChunked) {
        long binaryDataLength = (long) binaryData.length;
        long lengthDataBits = binaryDataLength * 8L;
        long fewerThan24bits = lengthDataBits % 24L;
        long tripletCount = lengthDataBits / 24L;
        int chunckCount = 0;
        long encodedDataLengthLong;
        if (fewerThan24bits != 0L) {
            encodedDataLengthLong = (tripletCount + 1L) * 4L;
        } else {
            encodedDataLengthLong = tripletCount * 4L;
        }

        if (isChunked) {
            chunckCount = CHUNK_SEPARATOR.length == 0 ? 0
                    : (int) Math.ceil((double) ((float) encodedDataLengthLong / 76.0F));
            encodedDataLengthLong += (long) (chunckCount * CHUNK_SEPARATOR.length);
        }

        if (encodedDataLengthLong > 2147483647L) {
            throw new IllegalArgumentException(
                    "Input array too big, output array would be bigger than Integer.MAX_VALUE=2147483647");
        } else {
            int encodedDataLength = (int) encodedDataLengthLong;
            byte[] encodedData = new byte[encodedDataLength];
            int encodedIndex = 0;
            int nextSeparatorIndex = 76;
            int chunksSoFar = 0;

            byte k;
            byte l;
            byte b1;
            byte b2;
            int dataIndex;
            int i;
            byte val1;
            byte val2;
            for (i = 0; (long) i < tripletCount; ++i) {
                dataIndex = i * 3;
                b1 = binaryData[dataIndex];
                b2 = binaryData[dataIndex + 1];
                byte b3 = binaryData[dataIndex + 2];
                l = (byte) (b2 & 15);
                k = (byte) (b1 & 3);
                val1 = (b1 & -128) == 0 ? (byte) (b1 >> 2) : (byte) (b1 >> 2 ^ 192);
                val2 = (b2 & -128) == 0 ? (byte) (b2 >> 4) : (byte) (b2 >> 4 ^ 240);
                byte val3 = (b3 & -128) == 0 ? (byte) (b3 >> 6) : (byte) (b3 >> 6 ^ 252);
                encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
                encodedData[encodedIndex + 1] = lookUpBase64Alphabet[val2 | k << 4];
                encodedData[encodedIndex + 2] = lookUpBase64Alphabet[l << 2 | val3];
                encodedData[encodedIndex + 3] = lookUpBase64Alphabet[b3 & 63];
                encodedIndex += 4;
                if (isChunked && encodedIndex == nextSeparatorIndex) {
                    System.arraycopy(CHUNK_SEPARATOR, 0, encodedData, encodedIndex, CHUNK_SEPARATOR.length);
                    ++chunksSoFar;
                    nextSeparatorIndex = 76 * (chunksSoFar + 1) + chunksSoFar * CHUNK_SEPARATOR.length;
                    encodedIndex += CHUNK_SEPARATOR.length;
                }
            }

            dataIndex = i * 3;
            if (fewerThan24bits == 8L) {
                b1 = binaryData[dataIndex];
                k = (byte) (b1 & 3);
                val1 = (b1 & -128) == 0 ? (byte) (b1 >> 2) : (byte) (b1 >> 2 ^ 192);
                encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
                encodedData[encodedIndex + 1] = lookUpBase64Alphabet[k << 4];
                encodedData[encodedIndex + 2] = 61;
                encodedData[encodedIndex + 3] = 61;
            } else if (fewerThan24bits == 16L) {
                b1 = binaryData[dataIndex];
                b2 = binaryData[dataIndex + 1];
                l = (byte) (b2 & 15);
                k = (byte) (b1 & 3);
                val1 = (b1 & -128) == 0 ? (byte) (b1 >> 2) : (byte) (b1 >> 2 ^ 192);
                val2 = (b2 & -128) == 0 ? (byte) (b2 >> 4) : (byte) (b2 >> 4 ^ 240);
                encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
                encodedData[encodedIndex + 1] = lookUpBase64Alphabet[val2 | k << 4];
                encodedData[encodedIndex + 2] = lookUpBase64Alphabet[l << 2];
                encodedData[encodedIndex + 3] = 61;
            }

            if (isChunked && chunksSoFar < chunckCount) {
                System.arraycopy(CHUNK_SEPARATOR, 0, encodedData, encodedDataLength - CHUNK_SEPARATOR.length,
                        CHUNK_SEPARATOR.length);
            }

            return encodedData;
        }
    }

    static {
        int i;

        for (i = 0; i <= 25; ++i) {
            lookUpBase64Alphabet[i] = (byte) (65 + i);
        }

        i = 26;

        int j;
        for (j = 0; i <= 51; ++j) {
            lookUpBase64Alphabet[i] = (byte) (97 + j);
            ++i;
        }

        i = 52;

        for (j = 0; i <= 61; ++j) {
            lookUpBase64Alphabet[i] = (byte) (48 + j);
            ++i;
        }

        lookUpBase64Alphabet[62] = 43;
        lookUpBase64Alphabet[63] = 47;
    }
}
