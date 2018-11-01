package model;

public class LCSResult {

    private static final double ALLOWED_SIMILARITY_GAP_RATE = 1.0 / 3;
    private static final int SIMILARITY_WORDS_COUNT = 4;

    private static final double ALLOWED_IDENTITY_GAP_RATE = 0.3;

    public enum MODE {
        MIN_LENGTH, AVG_LENGTH, WORDS_COUNT
    }

    private int lengthA;
    private int lengthB;
    private double commonLength;

    public LCSResult(int lengthA, int lengthB, double commonLength) {
        this.lengthA = lengthA;
        this.lengthB = lengthB;
        this.commonLength = commonLength;
    }

    private int getMinSampleLength() {
        return Math.min(lengthA, lengthB);
    }

    private double getGapRateInLength() {
        return Math.abs(lengthA - lengthB) * 1.0
                / Math.max(lengthA, lengthB);
    }

    private double getAvgSampleLength() {
        return (lengthA + lengthB) * 1.0 / 2;
    }

    private double getCommonLength() {
        return commonLength;
    }

    private double calculateResult() {
        double length = getMinSampleLength();

        if (getGapRateInLength() > ALLOWED_SIMILARITY_GAP_RATE) {
            length = getAvgSampleLength();
        }
        return getCommonLength() / length;
    }

    public double calculateResult(MODE mode) {

        if (mode.equals(MODE.AVG_LENGTH)) {
            return getCommonLength() / getAvgSampleLength();
        } else if (mode.equals(MODE.MIN_LENGTH)) {
            return getCommonLength() / getMinSampleLength();
        } else if (mode.equals(MODE.WORDS_COUNT)) {
            return getCommonLength() >= SIMILARITY_WORDS_COUNT ? 1 : 0;
        } else {
            return calculateResult();
        }

    }

    public double calculateIdentity() {
        double length = getMinSampleLength();

        // If lengths is gap much than allowed rate, then calculate AVG instead of MIN
        if (getGapRateInLength() > ALLOWED_IDENTITY_GAP_RATE) {
            length = getAvgSampleLength();
        }
        return getCommonLength() / length;
    }

    public double calculateSimilarity(int...similarWordCount) {
        int wordCount = SIMILARITY_WORDS_COUNT;

        if (similarWordCount.length > 0) {
            wordCount = similarWordCount[0];
        }

        if (getCommonLength() >= wordCount) {
            return 1;
        }

        double length = getMinSampleLength();

        // If lengths is gap much than allowed rate, then calculate AVG instead of MIN
        if (getGapRateInLength() > ALLOWED_SIMILARITY_GAP_RATE) {
            length = getAvgSampleLength();
        }
        return getCommonLength() / length;
    }

    public LCSResult printOutInfo() {
        System.out.println("lengthA=" + lengthA + "; lengthB=" + lengthB + "; commonLength=" + commonLength + "; gapRate=" + getGapRateInLength());
        return this;
    }
}
