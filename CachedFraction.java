//Задание 1.1
public class CachedFraction extends Fraction {
    private Double cachedValue;
    private boolean cacheValid;

    public CachedFraction(int numerator, int denominator) {
        super(numerator, denominator);
        cacheValid = false;
    }

    public CachedFraction() {
        super();
        cacheValid = false;
    }

    @Override
    public double getDoubleValue() {
        if (!cacheValid || cachedValue == null) {
            cachedValue = super.getDoubleValue();
            cacheValid = true;
        }
        return cachedValue;
    }

    @Override
    public void setNumerator(int numerator) {
        super.setNumerator(numerator);
        invalidateCache();
    }

    @Override
    public void setDenominator(int denominator) {
        super.setDenominator(denominator);
        invalidateCache();
    }

    private void invalidateCache() {
        cacheValid = false;
        cachedValue = null;
    }

    public boolean isCacheValid() {
        return cacheValid;
    }
}