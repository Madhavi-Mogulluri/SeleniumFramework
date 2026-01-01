package com.qa.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

    public class CsvUtil {

        private static final String CSV_PATH = "./src/test/resources/TestData/";

        private static List<String[]> rows;

        public static Object[][] readCsv(String fileName) {

            String file = CSV_PATH + fileName + ".csv";


            CSVReader reader;
            try {
                reader = new CSVReader(new FileReader(file));
                rows = reader.readAll();
                reader.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (CsvException e) {
                e.printStackTrace();
            }

            Object[][] data = new Object[rows.size()][];
            for (int i = 0; i < rows.size(); i++) {
                data[i] = rows.get(i);

            }

            return data;

        }
    }


