package com.mirror2.report.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Copyright &copy; Soft Engine Inc.
 * Created on 03/03/16
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 03/03/16
 * Version : 1.0
 */

public interface ReportDAO {

    public List<Map<String, Object>> getCollectionDataAllYear();

    public List getSalesByYear(Date from, Date to);

    public List getSalesByMonth(Date from, Date to);

    public List getSalesBySalesPersonType(Date from, Date to);

    public List getSalesBySalesPerson(Date from, Date to, String type, int limit);
    public List getSalesByBOD(Date from, Date to);

    public void getSalesBySalesPersonExcel(Date from, Date to, String type, int limit);
}
