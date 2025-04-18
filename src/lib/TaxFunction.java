package lib;

public class TaxFunction {

	private static final int BASIC_NONTAXABLE_INCOME = 54000000;
	private static final int MARRIED_ALLOWANCE = 4500000;
	private static final int CHILD_ALLOWANCE = 1500000;
	private static final int MAX_CHILDREN = 3;

	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking,
			int deductible, boolean isMarried, int numberOfChildren) {
		if (numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
		}

		int annualIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
		int totalDeduction = deductible + calculateNonTaxableIncome(isMarried, numberOfChildren);
		int taxableIncome = annualIncome - totalDeduction;

		return Math.max(0, (int) Math.round(0.05 * taxableIncome));
	}

	private static int calculateNonTaxableIncome(boolean isMarried, int numberOfChildren) {
		int childCount = Math.min(numberOfChildren, MAX_CHILDREN);
		int total = BASIC_NONTAXABLE_INCOME;

		if (isMarried) {
			total += MARRIED_ALLOWANCE;
		}

		total += childCount * CHILD_ALLOWANCE;

		return total;
	}
}
