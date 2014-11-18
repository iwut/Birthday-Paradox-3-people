import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Random;

public class Main {

	private final int EQUALS = 3;
	private final double C = 5;

	public static void main(String[] args) {
		int N = 10000;

		new Main(N);
	}

	public Main(int N) {


		int alphaAmount = 0;
		double Alpha = 0;
		double alpha = 0;

		for (int n = 5; n < N; n++) {

			int i = 0;

			double prob;
			while (true) {
				i++;
				prob = probability(i, n);
				if (prob >= .5) {
					break;
				}
			}
			
			alpha = findAlpha(i, n, C);


			Alpha += alpha;
			alphaAmount++;

		}

		Alpha /= alphaAmount;

		System.out.println(Alpha);

	}

	private double findAlpha(int T, int N, double c) {
		double div = (double) c / (double) T;

		return Math.log(div) / Math.log(N);
	}

	private BigInteger binomial(int top, int bot) {

		BigInteger botFac = BigInteger.ONE;
		for (int i = 1; i <= bot; i++) {
			botFac = botFac.multiply(new BigInteger("" + i));
		}

		BigInteger topMinusBotFac = new BigInteger(botFac.toString());
		for (int i = bot + 1; i <= top - bot; i++) {
			topMinusBotFac = topMinusBotFac.multiply(new BigInteger("" + i));
		}

		BigInteger topFac = new BigInteger(topMinusBotFac.toString());

		for (int i = (top - bot) + 1; i <= top; i++) {
			topFac = topFac.multiply(new BigInteger("" + i));
		}

		BigInteger res = topFac.divide(botFac.multiply(topMinusBotFac));

		return res;
	}

	private double probability(int T, int N) {
		BigDecimal bigN = new BigDecimal("" + N);

		BigDecimal fac = new BigDecimal(binomial(T, EQUALS));

		double exponent = fac.divide(bigN.multiply(bigN), 2, RoundingMode.HALF_UP).doubleValue();

		double prob = 1 - Math.exp(-exponent);

		return prob;

	}

}
