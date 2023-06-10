import com.voitovich.yura.modsen.exception.*;
import com.voitovich.yura.modsen.parser.Operand;
import com.voitovich.yura.modsen.parser.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParserTest {

    private Parser parser;

    @BeforeEach
    public void setup() {
        parser = new Parser();
    }

    @Test
    public void testParse_ValidExpression_ReturnsCorrectResult() {
        String expression = "toRubles(($3 + $2) + toDollars(737р + toRubles($85.4 + $2) + 65р))";
        String expected = "6808.00р";

        Operand result = parser.parse(expression);

        Assertions.assertEquals(expected, result.toString());
    }


    @Test
    public void testParse_InvalidExpression_WrongDollarsInput_ThrowsException() {
        String expression = "toRubles(($3 + 2$) + toDollars(737р + toRubles($85.4 + $2) + 65р))";

        Assertions.assertThrows(WrongExpressionException.class, () -> {
            parser.parse(expression);
        });
    }

    @Test
    public void testParse_InvalidExpression_WrongBinaryOperator_ThrowsException() {
        String expression = "$3 + ";

        Assertions.assertThrows(BinaryOperatorException.class, () -> {
            parser.parse(expression);
        });
    }

    @Test
    public void testParse_InvalidExpression_WrongUnaryOperator_ThrowsException() {
        String expression = "toDollars()";

        Assertions.assertThrows(UnaryOperationException.class, () -> {
            parser.parse(expression);
        });
    }

    @Test
    public void testParse_ConversionToDollars_ReturnsCorrectResult() {
        String expression = "toDollars(65р)";
        String expected = "$1.00";

        Operand result = parser.parse(expression);

        Assertions.assertEquals(expected, result.toString());
    }

    @Test
    public void testParse_InvalidToDollarsArguments_ThrowsException() {
        String expression = "toDollars($65)";

        Assertions.assertThrows(DollarsFunctionException.class, () -> {
            parser.parse(expression);
        });
    }



    @Test
    public void testParse_InvalidToRublesArguments_ThrowsException() {
        String expression = "toRubles(65р)";

        Assertions.assertThrows(RublesFunctionException.class, () -> {
            parser.parse(expression);
        });
    }

    @Test
    public void testParse_InvalidAdditionArguments_ThrowsException() {
        String expression = "$65 + 65р";

        Assertions.assertThrows(MoneyOperationException.class, () -> {
            parser.parse(expression);
        });
    }

    @Test
    public void testParse_ConversionToRubles_ReturnsCorrectResult() {
        String expression = "toRubles($1)";
        String expected = "65.00р";

        Operand result = parser.parse(expression);

        Assertions.assertEquals(expected, result.toString());
    }

    @Test
    public void testParse_InvalidBrackets_ThrowsException() {
        String expression = "toRubles(($3 + $2)";

        Assertions.assertThrows(BracketsException.class, () -> {
            parser.parse(expression);
        });
    }


}