package com.mirror2.mis.service;


import com.mirror2.csd.dao.CustomerDAO;
import com.mirror2.csd.dao.MoneyReceiptDAO;
import com.mirror2.csd.model.Customer;
import com.mirror2.csd.model.MoneyReceipt;
import com.mirror2.mis.bean.SearchBean;
import com.mirror2.mis.dao.MisDAO;
import com.mirror2.util.MirrorDataList;
import com.mirror2.util.MirrorUtil;
import com.mirror2.util.NumberUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Copyright @ Soft Engine Inc.
 * Created on 14/09/15
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 14/09/15
 * Version : 1.0
 */

@Service
public class MISServiceImpl implements MISService {

    @Autowired
    private MoneyReceiptDAO moneyReceiptDAO;
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private MisDAO misDAO;

    @Override
    public Object[] getPaymentStatementDataList(Customer customer, Date from, Date to) {
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

        List<MoneyReceipt> receipts = moneyReceiptDAO.findAllMoneyReceipt(customer, from, to);
        Map<String, String> map;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Double total = 0.0;
        if (receipts.size() > 0) {
            for (MoneyReceipt mr : receipts) {
                map = new HashMap<String, String>();
                map.put("DATE", sdf.format(mr.getReceiptDate()));
                map.put("CASH_DATE", sdf.format(mr.getCashDate()));
                map.put("NOTE", mr.getNote());
                map.put("TYPE", "MR");
                map.put("MR_NO", mr.getReceiptNo());
                map.put("MODE", mr.getPaymentMethod());
                map.put("DR", "");
                total += mr.getAmount();
                map.put("CR", MirrorUtil.toCommaFormattedTaka(mr.getAmount()));
                map.put("STATUS", mr.getStatus());
                dataList.add(map);
            }
        } else {
            map = new HashMap<String, String>();
            map.put("DATE", "");
            map.put("CASH_DATE", "No Transaction Found !");
            map.put("NOTE", "");
            map.put("TYPE", "...");
            map.put("MR_NO", "...");
            map.put("MODE", "...");
            map.put("DR", "...");
            map.put("CR", "...");
            map.put("STATUS", "...");
            dataList.add(map);
        }
        return new Object[]{dataList, MirrorUtil.toCommaFormattedTaka(total), NumberUtil.inWord(total.longValue())};

    }

    @Override
    public List<Map<String, String>> getCustomerDataListAsPerLastPayment(String type, Date targetDate) {

        type = type.toUpperCase();
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

        List<Object[]> receipts = moneyReceiptDAO.findAllMaxDatedMoneyReceipt(type, targetDate);
        Map<String, String> map;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (receipts.size() > 0) {
            for (Object[] data : receipts) {
                Date lastDate = (Date) data[4];
                if ((type.equals("BEFORE") && lastDate.compareTo(targetDate) < 0)
                        || (type.equals("AFTER") && lastDate.compareTo(targetDate) >= 0)) {
                    String id = String.valueOf(data[0]);
                    String cid = String.valueOf(data[1]);
                    String name = String.valueOf(data[2]);
                    String refName = String.valueOf(data[3]);
                    String date = sdf.format(lastDate);
                    map = new HashMap<String, String>();
                    map.put("ID", id);
                    map.put("CID", cid);
                    map.put("NAME", name);
                    map.put("REF_NAME", refName);
                    map.put("LAST_DATE", date);

                    dataList.add(map);
                }
            }
        } else {
            map = new HashMap<String, String>();
            map.put("ID", "");
            map.put("CID", "");
            map.put("NAME", "");
            map.put("REF_NAME", "");
            map.put("LAST_DATE", "");
            dataList.add(map);
        }
        return dataList;

    }

    @Override
    public List<Map<String, String>> getCustomersDataByHandoverYYYY(String year) {
        SearchBean searchBean = new SearchBean();
        searchBean.setHandoverYear(year);
        searchBean.setNotStatus(MirrorDataList.CUST_STATUS_REFUNDED);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM yy");
        List<Map<String, Object>> customerList = misDAO.getCustomerDataListMap(searchBean);
        Map<Long, Double> payableInstAmtMap = misDAO.getCustomersPayableInstAmtMap(searchBean);
        Map<Long, Double> payableOPAmtMap = misDAO.getCustomersPayableOPAmtMap(searchBean);
        Map<Long, Double> paidInstAmtMap = misDAO.getCustomersPaidInstAmtMap(searchBean);

        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        Map<String, String> map;
        int sl = 1;
        for (Map<String, Object> o : customerList) {
            map = new HashMap<String, String>();
            Long id = (Long) o.get("ID");
            map.put("SL", String.valueOf(sl++));
            map.put("ID", String.valueOf(id));
            map.put("CID", (String) o.get("CID"));
            map.put("NAME", (String) o.get("NAME"));
            map.put("AID", o.get("B_AL") + "-" + o.get("AID"));
            map.put("HOD", sdf.format((Date) o.get("HOD")));
            map.put("SIZE", String.valueOf(o.get("SIZE")));

            Double payableInstAmt = payableInstAmtMap.get(id);
            payableInstAmt = payableInstAmt == null ? 0.0 : payableInstAmt;
            Double payableOPAmt = payableOPAmtMap.get(id);
            payableOPAmt = payableOPAmt == null ? 0.0 : payableOPAmt;
            Double paidAmt = paidInstAmtMap.get(id);
            paidAmt = paidAmt == null ? 0.0 : paidAmt;

            Double due = payableInstAmt + payableOPAmt;
            Double overDue = due - paidAmt;
            map.put("PAID", NumberUtil.toCommaFormattedTaka(paidAmt));
            if (overDue >= 0) {
                map.put("O_DUE", NumberUtil.toCommaFormattedTaka(overDue));
            } else {
                map.put("O_DUE", "("+NumberUtil.toCommaFormattedTaka(-overDue)+")");
            }
            dataList.add(map);
        }
        return dataList;

    }

    public Object[] getCollectionModeWise(Date from, Date to) {
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

        List<Object[]> objectList = moneyReceiptDAO.getCollectionDataModeWise(from, to);
        int sl = 1;
        Double cashAndProfit = 0.0;
        Map<String, String> map;
        for (Object[] objects : objectList) {
            map = new HashMap<String, String>();
            map.put("SL", String.valueOf(sl));
            map.put("MODE_NAME", String.valueOf(objects[0]));
            map.put("TOTAL_TR", String.valueOf(objects[1]));
            map.put("TR_AMOUNT", MirrorUtil.toCommaFormattedTaka(MirrorUtil.toJasperAmt(objects[2])));
            dataList.add(map);
            sl++;
        }
        /*<option value="Cash">Cash</option>
        <option value="Cheque">Cheque</option>
        <option value="EFT">EFT</option>
        <option value="TT">TT</option>
        <option value="DD">DD</option>
        <option value="Incentive">Incentive</option>
        <option value="Profit">Profit</option>
        <option value="Others">Others</option>*/
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Double total = 0.0;
        return new Object[]{dataList, MirrorUtil.toCommaFormattedTaka(0), NumberUtil.inWord(total.longValue())};
    }

    @Override
    public List<Map<String, String>> getCustomerOfferWise(Long offerId, Date bookingFrom, Date bookingTo) {

        List<Object[]> objectList = misDAO.getCustomerData(offerId);

        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        Map<String, String> map;
        int sl = 1;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (Object[] o : objectList) {
            map = new HashMap<String, String>();
            map.put("SL", String.valueOf(sl++));
            //CID, name, bookingDate, floorSize, building.buildingName,5 AID,6 referenceBy.mid, 7 c.offer.offerName
            map.put("CID", String.valueOf(o[0]).replace("DPL ", ""));
            map.put("NAME", String.valueOf(o[1]));
            map.put("BOOKING", sdf.format((Date) o[2]));
            map.put("SIZE", String.valueOf(o[3]));
            String buildingAlias = String.valueOf(o[4]);
            map.put("AID", buildingAlias + "-" + String.valueOf(o[5]));
            map.put("MID", String.valueOf(o[6]));
            map.put("OFFER", String.valueOf(o[7]));
            dataList.add(map);
        }

        return dataList;
    }

    @Override
    public String getRealPath(String pathExtension) {
        try {
            String filePath = "com/mirror2/mis/jasper" + pathExtension;
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            return classLoader.getResource(filePath).getPath();
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public ByteArrayOutputStream generateReport(HttpServletResponse response, Map<String, Object> params, JRDataSource dataSource) {
        try {
            String format = (String) params.get("format");
            String fileName = (String) params.get("fileName");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            InputStream jasperStream = this.getClass().getResourceAsStream("/com/mirror2/mis/jasper/" + fileName + ".jasper");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

            if (format.equals("pdf")) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            } else if (format.equals("html")) {
                JRHtmlExporter htmlExporter = new JRHtmlExporter();
                htmlExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                htmlExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
                // File imageDir = new File(context.substring(0, context.indexOf("WEB-INF")) + "\\images");
                // htmlExporter.setParameter(JRHtmlExporterParameter.IMAGES_DIR, imageDir);
                htmlExporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR, Boolean.FALSE);
                htmlExporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                htmlExporter.exportReport();
            } else if (format.equals("excel")) {
                JRXlsExporter exporterXLS = new JRXlsExporter();
                exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
                exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, stream);
                exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                exporterXLS.exportReport();
            } else if (format.equals("csv")) {
                JRCsvExporter exporter = new JRCsvExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
                exporter.exportReport();
            } else if (format.equals("download")) {
                response.setContentType("application/x-pdf");
                response.setHeader("Content-disposition", "inline; filename=" + fileName + ".pdf");
                final OutputStream outStream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
                //JasperExportManager.exportReportToPdf(jasperPrint);
            } else {
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            }

            return stream;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
