package com.example.demo.controller;

import com.example.demo.entity.Pessoa;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class TesteController {

    @Autowired
    ResourceLoader resourceLoader;

    @RequestMapping(value="/testePdf")
    public void testePdf(ModelAndView model, HttpServletResponse response) throws Exception {
        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"testePdf.pdf\""));

        JasperPrint jasperPrint = this.exportPdfFile();
        OutputStream out = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);
    }


    private JasperPrint exportPdfFile() throws Exception {
        String path = resourceLoader.getResource("classpath:reports\\teste-pdf.jrxml").getURI().getPath();
        JasperReport jasperReport = JasperCompileManager.compileReport(path);

        // Parameters for report
        Map<String, Object> parameters = new HashedMap();

        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(new Pessoa(1l, "Ygor", 27));
        pessoas.add(new Pessoa(2l, "Fulano", 33));
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pessoas);

        return JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
    }

}
