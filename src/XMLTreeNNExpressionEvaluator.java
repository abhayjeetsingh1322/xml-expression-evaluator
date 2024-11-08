
import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author A. Singh
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {

        //Initializing a NaturalNumber variable to hold the answer
        NaturalNumber answer = new NaturalNumber2();

        //Series of if-else if statements to check for tag name
        //For plus, minus, times, divide:
        //Declaring a NaturalNumber variable to hold the left subtree called recursively
        //Declaring a NaturalNumber variable to hold the right subtree called recursively
        //Applying the method to left w/ precondition check and answer transfers from left
        //For number:
        //Declaring a NaturalNumber variable to temporarily hold the actual number
        //Answer transfers temporary variable
        if (exp.label().equals("plus")) {

            NaturalNumber left = new NaturalNumber2(evaluate(exp.child(0)));
            NaturalNumber right = new NaturalNumber2(evaluate(exp.child(1)));

            left.add(right);
            answer.transferFrom(left);

        } else if (exp.label().equals("minus")) {

            NaturalNumber left = new NaturalNumber2(evaluate(exp.child(0)));
            NaturalNumber right = new NaturalNumber2(evaluate(exp.child(1)));

            //Checking if left is bigger than right
            int compare = left.compareTo(right);
            if (compare < 0) {

                //Reporting if left is not bigger than right
                Reporter.fatalErrorToConsole(
                        "Precondition violation of subtract method: this >= n."
                                + " This means that the subtraction is leading to"
                                + " a negative number.");
            }

            left.subtract(right);
            answer.transferFrom(left);

        } else if (exp.label().equals("times")) {

            NaturalNumber left = new NaturalNumber2(evaluate(exp.child(0)));
            NaturalNumber right = new NaturalNumber2(evaluate(exp.child(1)));

            left.multiply(right);
            answer.transferFrom(left);

        } else if (exp.label().equals("divide")) {

            NaturalNumber left = new NaturalNumber2(evaluate(exp.child(0)));
            NaturalNumber right = new NaturalNumber2(evaluate(exp.child(1)));

            //Checking if right is zero
            if (right.isZero()) {

                //Reporting if right is zero and there is division by zero
                Reporter.fatalErrorToConsole(
                        "Precondition violation of divide method: n > 0."
                                + " This means that the expression is leading to"
                                + " division by 0.");
            }

            left.divide(right);
            answer.transferFrom(left);

        } else if (exp.label().equals("number")) {

            NaturalNumber temp = new NaturalNumber2(
                    exp.attributeValue("value"));

            answer.transferFrom(temp);

        }

        //Returning answer
        return answer;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}