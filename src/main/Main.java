package main;

import checker.Checker;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import common.Constants;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {
        new File("output").mkdirs();
        for (int i = 1; i <= Constants.TESTS_NUMBER; i++) {
            ObjectMapper objectMapper = new ObjectMapper();
            Parser parser = objectMapper.readValue(new File("tests/test" + i + ".json"), Parser.class);
            Flow flow = new Flow(parser);
            flow.roundZero();
            ArrayList<AnnualChanges> years = new ArrayList<>(parser.getAnnualChanges());
            flow.annualRounds();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File(Constants.OUTPUT_PATH + i
                    + Constants.FILE_EXTENSION), flow.getOutData());
        }
        Checker.calculateScore();
    }
}
