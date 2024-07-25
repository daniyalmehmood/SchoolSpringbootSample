package com.tra.school.Services;

import com.tra.school.DTO.SchoolDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class ReportService {

    @Autowired
    private SchoolService schoolService;

    public void createSchoolReport() throws FileNotFoundException, JRException {
        List<SchoolDTO> schoolDTOList = schoolService.getSchools();
        UUID uuid = UUID.randomUUID();

        String pathToSave = "C:\\Users\\MuhammadDaniyal\\OneDrive - Rihal\\Desktop\\";

        File templateFile = ResourceUtils.getFile("C:\\Users\\MuhammadDaniyal\\OneDrive - Rihal\\Desktop\\sampleproject\\sampleproject\\src\\main\\resources\\school_report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(templateFile.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(schoolDTOList);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,new HashMap<>(),dataSource);
        String fileName = pathToSave + uuid+".pdf";
        JasperExportManager.exportReportToPdfFile(jasperPrint,fileName );
        System.out.println("Report is printed: "+ fileName);
    }
}
