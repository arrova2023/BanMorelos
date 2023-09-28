package com.bankflow.restful;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.bind.annotation.*;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200") // Reemplaza "http://localhost:3000" con el origen que desees permitir
@RestController
@RequestMapping("/api/csv")
public class CsvRestController {

    @GetMapping("/datos")
    public List<DatosCSV> obtenerDatosCSV() {
        String csvFilePath = "src/main/resources/data.csv";
        List<DatosCSV> datosList = new ArrayList<>();

        try (Reader reader = new FileReader(csvFilePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {

            for (CSVRecord csvRecord : csvParser) {
                DatosCSV datos = new DatosCSV();
                datos.setNombre(csvRecord.get("Nombre"));
                datos.setApellido(csvRecord.get("Apellido"));
                datos.setNumeroCuenta(csvRecord.get("Número de Cuenta"));
                datos.setBanco(csvRecord.get("Banco"));
                datos.setEstado(csvRecord.get("Estado"));
                datosList.add(datos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datosList;
    }
}

