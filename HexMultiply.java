import java.util.Scanner;
import java.util.Random;

/**
 *  Game for multiplying hexadecimal numbers
 */
public class HexMultiply {

	/**
	 * Generate a random number with certain number of base 16 digits
	 * @param digits number of hex digits
	 * @return random number
	 */
	public static int rand(int digits) {
		Random rand = new Random();
		int max = (int) Math.pow(16, digits);
		int r = rand.nextInt(max);
		return r;
	}

	/**
	 * Returns base 16 character
	 * @param n number to turn into a digit
	 * @return hexadecimal digit
	 */
	private static char hexChar(int n) {
		if (n < 10) {
			return (char) (n + 48);		//0 = 0x48, 1 = 0x49, ... 9 = 0x57
		} else if (n < 36){
			return (char) (n + 87);		//a = 0x97, b = 0x98, ... z = 0x122
		} else {
			return '?';
		}
	}

	/**
	 * Returns base 16 string
	 * @param n number to change to hexadecimal
	 * @return hexadecimal string
	 */
	public static String hexString(int n) {
		if (n < 16) {
			return String.valueOf(hexChar(n));
		} else {
			return hexString(n / 16) + hexChar(n % 16);
		}	
	}

	/**
	 * Method to prompt user for answer to base 16 multiplication
	 * @param difficulty number of digits for each factor
	 */
	public static void play(int difficulty) {
		Scanner input = new Scanner(System.in);
		int a;
		int b;
		int ans;
		String guess;
		int guessInt;
		boolean running = true;

		while (running) {
			a = rand(difficulty);
			b = rand(difficulty);
			ans = a*b;

			guessInt = -1;
			while (guessInt < 0) {
				System.out.println(hexString(a) + " x " + hexString(b) + " = ");
				System.out.print(">> ");
				guess = input.nextLine();
				guess = guess.trim().toLowerCase();

				try {
					guessInt = Integer.parseInt(guess, 16);
				} catch (Exception e) {
					if (guess.equals("exit") || guess.equals("quit")) {
						running = false;
						break;
					}

					System.out.println("error: invalid response");
					System.out.println();
				}
			}

			if (guessInt == ans) {
				System.out.println("correct");
			} else if (running) {
				System.out.println("WRONG: correct answer is '" + hexString(ans) + "'");
			}

			System.out.println();
		}

		System.out.println("Exiting...");
	}

	/**
	 * Method to run game
	 */
	public static void main(String[] args) {
		int difficulty = 1;

		if (args.length > 0) {
			try {
				difficulty = Integer.parseInt(args[0]);
			} catch (Exception e) {
				difficulty = 0;
			}
		}

		if (difficulty > 0) {
			play(difficulty);
		} else {
			System.out.println("usage: java HexMultiply <difficulty>");
		}
	}

}
