package ru.idea.test.core.file.apache;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Supplier;

public class CsvConverter {

    private final CSVFormat inFormat;
    private final CSVFormat outFormat;
    private final Supplier<CsvLineTask<ConvertContext>> converterSupplier;

    public CsvConverter(CSVFormat inFormat, CSVFormat outFormat, Supplier<CsvLineTask<ConvertContext>> converterSupplier) {
        this.inFormat = inFormat;
        this.outFormat = outFormat;
        this.converterSupplier = converterSupplier;
    }

    public void convert(String in, String out) {
        convert(new File(in), new File(out));
    }

    @SuppressWarnings("ConfusingArgumentToVarargsMethod")
    public void convert(File in, File out) {
        CsvLineTask<ConvertContext> converter = converterSupplier.get();
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(out), outFormat)) {
            for (CSVRecord record : inFormat.parse(new FileReader(in))) {
                ConvertContext convertContext = converter.apply(record);
                if (convertContext != null && convertContext.isReady()){
                    printer.printRecord(convertContext.getCsvLine().get());
                }
            }
            printer.printRecord(converter.result().getCsvLine().get());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
