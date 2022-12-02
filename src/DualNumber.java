public class DualNumber {

    private double a = 0;
    private double b = 0;

    /**
     * constructor with a real number
     * @param a real part of the dual number
     */
    public DualNumber(double a) {
        this.a = a;
    }

    /**
     * constructor with real and 𝛆 part
     * @param a real number
     * @param b 𝛆 number / multiplicator
     */
    public DualNumber(double a, double b) {
        this.a = a;
        this.b = b;
    }

    /**
     * adds number to this dual number and returns this number
     * @param number the dual number which should be added
     * @return this dual number
     */
    public DualNumber add(DualNumber number) {
        this.a += number.a;
        this.b += number.b;
        return this;
    }

    /**
     * subtracts number from this dual number and returns this number
     * @param number the dual number which should be subtracted
     * @return this dual number
     */
    public DualNumber subtract(DualNumber number) {
        this.a -= number.a;
        this.b -= number.b;
        return this;
    }

    /**
     * multiplies number to this dual number and returns this number
     * @param number the dual number which should be multiplied with
     * @return this dual number
     */
    public DualNumber multiply(DualNumber number) {
        double a = this.a;
        double b = this.b;

        // ac
        this.a = a * number.a;
        // ad + bc
        this.b = a * number.b + b * number.a;

        return this;
    }

    /**
     * divides this dual number / number (argument) and returns this number
     * @param number the dual number which should be the divisor
     * @return this dual number
     */
    public DualNumber divide(DualNumber number) {
        if (number.a == 0)
            throw new ArithmeticException("Division by zero; a can't be zero");

        double a = this.a;
        double b = this.b;

        // a/c
        this.a = a / number.a;
        // (bc-ad)/(c*c)
        this.b = (b * number.a - a * number.b)/(number.a * number.a);

        return this;
    }

    /**
     * takes this number to the power of pot and returns this
     * @param pot the power
     * @return this dual number
     */
    public DualNumber pow(int pot) {
        if (pot == 0) {
            this.a = 1;
            this.b = 0;
            return this;
        }

        int n = (pot < 0)? -pot-1: pot-1;

        DualNumber temp = new DualNumber(this.a, this.b);
        for (int i = 0; i < n; i++) {
            multiply(temp);
        }

        if (pot < 0) {
            temp = new DualNumber(this.a, this.b);
            this.a = 1;
            this.b = 0;
            divide(temp);
        }

        return this;
    }

    /**
     * returns this dual number as a string
     * @return this dual number as a string
     */
    @Override
    public String toString() {
        String sign = (b < 0)? " " : " + ";
        return "dual number = " + a + sign + b + " \uD835\uDEC6";
    }

    public static void main(String[] args) {
        DualNumber n1 = new DualNumber(4, 3);
        DualNumber n2 = new DualNumber(3, 1);

        System.out.println(n1);
        System.out.println(n2);
        n1.pow(-2);
        System.out.println(n1);
    }

}