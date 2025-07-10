package com.ilyaproject.smart_menu_server.email.generator.pdf;

import com.ilyaproject.smart_menu_server.exception.GenerationException;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PdfGenerator {

    public byte[] generateMergedPdfs(List<String> htmlPages) throws Exception{
        PDDocument target = new PDDocument();
        PDFMergerUtility merger = new PDFMergerUtility();
        List<byte[]> pdfsInBytes = generateListOfPdfsInByteArray(htmlPages);
        for (byte[] array: pdfsInBytes){
            try(PDDocument document = PDDocument.load(array)) {
                merger.appendDocument(target, document);
            }catch (Exception e){
                log.error("Failed to merged pdfs ", e);
                throw new GenerationException("Failed to merged pdfs ", e);
            }
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        target.save(baos);
        target.close();
        return baos.toByteArray();
    }


    private List<byte[]> generateListOfPdfsInByteArray(List<String> htmlPages) throws Exception{
        List<byte[]> listOfPdfs = new ArrayList<>();
        for (String page: htmlPages){
            try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                PdfRendererBuilder builder = new PdfRendererBuilder();
                builder.useFastMode();
                builder.withHtmlContent(page, null);
                builder.toStream(baos);
                builder.run();
                listOfPdfs.add(baos.toByteArray());
            }catch (Exception e){
                log.error("Failed to generate list of pdfs in byte arrays ", e);
                throw new GenerationException("Failed to generate list of pdfs in byte arrays ", e);
            }
        }
        return listOfPdfs;
    }
}
