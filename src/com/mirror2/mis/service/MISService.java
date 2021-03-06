package com.mirror2.mis.service;


import com.mirror2.csd.model.Customer;
import com.mirror2.mis.bean.SearchBean;
import net.sf.jasperreports.engine.JRDataSource;
import org.hibernate.criterion.Criterion;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Copyright @ Soft Engine Inc.
 * Created on 14/09/15
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 14/09/15
 * Version : 1.0
 */

public interface MISService {

    public List<Map<String, String>> getCustomerDataListAsPerLastPayment(String type, Date targetDate);

    public  List<Map<String, String>> getCustomerListCustomized(SearchBean searchBean);

    public Object[] getPaymentStatementDataList(Customer customer, Date from, Date to);

    public Object[] getCollectionModeWise(Date from, Date to);

    List<Map<String, String>> getCustomerOfferWise(Long offerId, Date bookingFrom, Date bookingTo);

    public String getRealPath(String pathExtension);

    public ByteArrayOutputStream generateReport(HttpServletResponse response, Map<String, Object> params, JRDataSource dataSource);
}
