package com.twu.refactoring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class CustomerTest {


    private static final String GOLD_PATH = "data/";

    private Customer dinsdale = new Customer("Dinsdale Pirhana");

    private List<Movie> movies = new ArrayList<>(Arrays.asList(new Movie("Monty Python and the Holy Grail", Movie.REGULAR),
            new Movie("Ran", Movie.REGULAR),
            new Movie("LA Confidential", Movie.NEW_RELEASE),
            new Movie("Star Trek 13.2", Movie.NEW_RELEASE),
            new Movie("Wallace and Gromit", Movie.CHILDRENS)));

    @BeforeEach
    public void setUpData() {
        dinsdale.addRental(new Rental(movies.get(0), 3));
        dinsdale.addRental(new Rental(movies.get(1), 1));
        dinsdale.addRental(new Rental(movies.get(2), 2));
        dinsdale.addRental(new Rental(movies.get(3), 1));
        dinsdale.addRental(new Rental(movies.get(4), 6));
    }

    @Test
    public void shouldOutputEmptyStatement() throws Exception {
        Customer customer = new Customer("Golden Shark");
        verifyOutput(customer.statement(), "outputEmpty");
    }

    @Test
    public void shouldOutputCustomerStatement() throws Exception {
        verifyOutput(dinsdale.statement(), "output1");
    }

    @Test
    public void shouldOutputChangedStatement() throws Exception {
        movies.get(2).setPriceCode(Movie.REGULAR);
        verifyOutput(dinsdale.statement(), "outputChange");
    }

    protected void verifyOutput(String actualValue, String fileName) throws IOException {
        String filePath = getClass().getClassLoader().getResource(GOLD_PATH + fileName).getPath();
        BufferedReader file = new BufferedReader(new FileReader(filePath));
        BufferedReader actualStream = new BufferedReader(new StringReader(actualValue));
        String thisFileLine;
        while ((thisFileLine = file.readLine()) != null) {
            assertThat("in file: " + fileName, actualStream.readLine(), equalTo(thisFileLine));
        }
    }

}
