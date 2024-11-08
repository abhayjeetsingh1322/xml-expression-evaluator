
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author A. Singh
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
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
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        //Initializing a variable to hold the answer of the expression
        int answer = 0;

        //Series of if-else if statements to check for the tag name
        //Recursively calling the right subtree (child 0) and left subtree (child 1)
        //And applying the corresponding evaluation
        if (exp.label().equals("plus")) {
            answer = evaluate(exp.child(0)) + evaluate(exp.child(1));
        } else if (exp.label().equals("minus")) {
            answer = evaluate(exp.child(0)) - evaluate(exp.child(1));
        } else if (exp.label().equals("times")) {
            answer = evaluate(exp.child(0)) * evaluate(exp.child(1));
        } else if (exp.label().equals("divide")) {
            answer = evaluate(exp.child(0)) / evaluate(exp.child(1));
        } else if (exp.label().equals("number")) {
            answer = Integer.parseInt(exp.attributeValue("value"));
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