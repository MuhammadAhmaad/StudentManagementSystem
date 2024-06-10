package com.example.springjwt.utils;

import com.example.springjwt.model.APIResponseDTO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class StudentManagementSystemUtility {
    public static <S, D> List<D> convertToDtoList(List<S> sourceList, Class<D> destinationType) {

        ModelMapper mapper = new ModelMapper();
        return sourceList.stream().map(item -> {

            return mapper.map(item, destinationType);
        }).collect(Collectors.toList());

    }
    public static <T> T convertToDto(Object entity, Class<T> destinationType) {

        return new ModelMapper().map(entity, destinationType);

    }

    public static <T> T convertToEntity(Object dto, Class<T> destinationType) {
        return new ModelMapper().map(dto, destinationType);
    }
    public static APIResponseDTO prepareAPIResponse(Object obj) {
        APIResponseDTO apiResponse = new APIResponseDTO();
        apiResponse.setResponseCode("200");
        apiResponse.setResponseMessage("SUCCESS");
        apiResponse.setResponseDate(DateTimeUtils.createDateStringFromDate(new Date()));
        apiResponse.setData(obj);
        return apiResponse;

    }
    public static APIResponseDTO prepareAPIResponse(String message, Object obj, String code) {
        APIResponseDTO apiResponse = new APIResponseDTO();
        apiResponse.setResponseCode(code);
        apiResponse.setResponseDate(DateTimeUtils.createDateStringFromDate(new Date()));
        apiResponse.setResponseMessage(message);
        apiResponse.setData(obj);
        return apiResponse;

    }

    public static Date getDateWithoutTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date getLastSecondDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date getDateWithoutTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date getLastSecondDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static ByteArrayOutputStream createPDFTable(int columnsNo,List<String> headers,List<String> cells) throws DocumentException, FileNotFoundException {
        Document document = new Document();
        ByteArrayOutputStream file = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, file);

        document.open();

        PdfPTable table = new PdfPTable(columnsNo);
        addTableHeader(table,headers);
        addRows(table,cells);

        document.add(table);
        document.close();

        return file;
    }
    private static void addTableHeader(PdfPTable table,List<String> headers) {
        headers
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private static void addRows(PdfPTable table,List<String> cells) {
        cells.forEach(table::addCell);
    }


}
