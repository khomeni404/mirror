package com.mirror2.report.service;

import com.mirror2.csd.dao.CustomerDAO;
import com.mirror2.csd.model.MID;
import com.mirror2.report.dao.ReportDAO;
import com.mirror2.util.MirrorUtil;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import net.sf.jasperreports.engine.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by ayatullah213574 on 29/07/2015.
 */
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportDAO reportDAO;
    @Autowired
    private CustomerDAO customerDAO;


    @Override
    public List getSalesByYear(Date from, Date to) {
        return reportDAO.getSalesByYear(from, to);
    }

    @Override
    public List getSalesByMonth(Date from, Date to) {
        return reportDAO.getSalesByMonth(from, to);
    }

    @Override
    public List getSalesBySalesPersonType(Date from, Date to) {
        return reportDAO.getSalesBySalesPersonType(from, to);
    }

    @Override
    public List getSalesBySalesPerson(Date from, Date to, String type, int limit) {
        return reportDAO.getSalesBySalesPerson(from, to, type, limit);
    }

    @Override
    public List<Map<String, String>> getSalesDataSalesPersonWise(String type, Date from, Date to) {
        List<Object[]> dataList = customerDAO.getSalesDataSalesPersonWise(type, from, to);
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        Map<String, String> map;
        int c = 0;
        for (Object[] objects : dataList) {
            map = new HashMap<String, String>();
            map.put("SL", c+"");
            map.put("NAME", objects[0].toString());
            map.put("SALES", objects[1].toString());
            map.put("TOTAL_COLLECTION", MirrorUtil.toCommaFormattedTaka(String.valueOf(((Double) objects[2]).intValue())));
            map.put("COLLECTION", MirrorUtil.toCommaFormattedTaka(String.valueOf(((Double) objects[3]).intValue())));
            result.add(map);
            c++;
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> getSalesDataDetailsSalesPersonWise(String type, Date from, Date to) {
        List<Object[]> dataList = customerDAO.getSalesDataDetailsSalesPersonWise(type, from, to);
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        for (Object[] objects : dataList) {
            map = new HashMap<String, Object>();
            MID salesMan = (MID) objects[0];
            map.put("SALES_MAN", salesMan);
            map.put("CUST_LIST", salesMan.getCustomersReferred());
            map.put("SALES", objects[1].toString());
            map.put("TOTAL_COLLECTION", objects[2] == null ? "" : MirrorUtil.toCommaFormattedTaka(String.valueOf(((Double) objects[2]).intValue())));
            map.put("COLLECTION", objects[2] == null ? "" : MirrorUtil.toCommaFormattedTaka(String.valueOf(((Double) objects[3]).intValue())));
            result.add(map);
        }
        return result;
    }

    @Override
    public List getSalesByBOD(Date from, Date to) {
        return reportDAO.getSalesByBOD(from, to);
    }

    @Override
    public void getSalesBySalesPersonExcel(Date from, Date to, String type, int limit) {
        reportDAO.getSalesBySalesPersonExcel(from, to, type, limit);
    }

    @Override
    public List<Map<String, Object>> getCollectionDataAllYear() {
        return reportDAO.getCollectionDataAllYear();
    }

    @Override
    public void createReport(HttpServletResponse response, Map<String, Object> params, String fileName,
                             JRDataSource dataSource) throws JRException, IOException {
        InputStream jasperStream = this.getClass().getResourceAsStream("/com/dpl/report/resources/" + fileName + ".jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "inline; filename=" + fileName + ".pdf");
        final OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
        //JasperExportManager.exportReportToPdf(jasperPrint);
    }

    @Override
    public String getRealPath(String pathExtension) {
        String filePath = "com/dpl/report/resources" + pathExtension;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String realPath = classLoader.getResource(filePath).getPath();
        return realPath;
    }


}
