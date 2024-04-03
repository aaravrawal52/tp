package parser;


import command.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParserTest {

    private Parser parser;

    @BeforeEach
    public void setup() {
        this.parser = new Parser();
    }

    @Test
    public void parse_emptyInput_returnsIncorrect() {
        final String[] emptyInputs = { "", "  ", "\n  \n" };
        parseAndAssertEmpty(emptyInputs);
    }

    public void parseAndAssertEmpty(String[] inputs) {
        for (String input : inputs) {
            final Command result = parser.parseCommand(input);
            //assertEquals(result, new ErrorCommand());
        }
    }
}
