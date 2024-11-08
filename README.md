# XML Expression Evaluator

## Description
This project evaluates arithmetic expressions represented in XML format using recursion. It includes two implementations:
1. Evaluation using `int` operands and Java's built-in arithmetic operators.
2. Evaluation using `NaturalNumber` operands and the `NaturalNumber` class methods.

The program also handles malformed expressions (e.g., division by zero) by reporting errors and terminating gracefully.

## Objectives
- Practice recursion to process nested arithmetic expressions.
- Work with `XMLTree` objects for hierarchical data processing.
- Leverage the `NaturalNumber` class for custom arithmetic operations.

## Input
An arithmetic expression in XML format.  
Example for `12 * (7 - 3) / 6 + 8`:
```xml
<expression>
  <plus>
    <divide>
      <times>
        <number value="12" />
        <minus>
          <number value="7" />
          <number value="3" />
        </minus>
      </times>
      <number value="6" />
    </divide>
    <number value="8" />
  </plus>
</expression>
