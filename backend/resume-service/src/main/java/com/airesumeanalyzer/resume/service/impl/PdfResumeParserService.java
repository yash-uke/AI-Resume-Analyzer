package com.airesumeanalyzer.resume.service.impl;

import com.airesumeanalyzer.resume.service.ResumeParserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class PdfResumeParserService implements ResumeParserService {

    @Override
    public String extractText(File file) {

        try (PDDocument document = Loader.loadPDF(file)) {

            PDFTextStripper pdfTextStripper = new PDFTextStripper();

            return pdfTextStripper.getText(document);

        } catch (IOException e) {

            log.error("Failed to parse PDF", e);

            throw new RuntimeException("Unable to extract text from PDF.");
        }
    }
}