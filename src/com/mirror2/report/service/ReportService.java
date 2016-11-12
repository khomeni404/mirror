package com.mirror2.report.service;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ayatullah213574 on 29/07/2015.
 */
public interface ReportService {
    public List<Map<String, Object>> getCollectionDataAllYear();
    public List getSalesByYear(Date from, Date to);
    public List getSalesByMonth(Date from, Date to);
    public List getSalesBySalesPersonType(Date from, Date to);
    public List getSalesBySalesPerson(Date from, Date to, String type, int limit);
    public List<Map<String, String>> getSalesDataSalesPersonWise(String type, Date from, Date to);
    public List getSalesDataDetailsSalesPersonWise(String type, Date from, Date to);
    public void getSalesBySalesPersonExcel(Date from, Date to, String type, int limit);
    public List getSalesByBOD(Date from, Date to);


    public void createReport(HttpServletResponse response,
                             Map<String, Object> params,
                             String fileName,
                             JRDataSource dataSource)  throws JRException, IOException;

    public String getRealPath(String path);
}
