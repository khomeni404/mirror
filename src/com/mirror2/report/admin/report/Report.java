package com.mirror2.report.admin.report;

import java.awt.Container;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JFrame;

import com.mirror2.report.admin.Database;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;


public class Report extends JFrame {

    HashMap hm = null;
    Connection con = null;
    String reportName;

    public Report() {
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    public Report(HashMap map) {
        this.hm = map;
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    public Report(HashMap map, Connection con) {
        this.hm = map;
        this.con = con;
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Report Viewer");

    }

    public void setReportName(String rptName) {
        this.reportName = rptName;
    }

    public void callReport(String name) {
        JasperPrint jasperPrint = generateReport(name);
        JRViewer viewer = new JRViewer(jasperPrint);
        Container c = getContentPane();
        c.add(viewer);
        this.setVisible(true);
    }

    public void callConnectionLessReport() {
        JasperPrint jasperPrint = generateEmptyDataSourceReport();
        JRViewer viewer = new JRViewer(jasperPrint);
        Container c = getContentPane();
        c.add(viewer);
        this.setVisible(true);
    }

    public void closeReport() {
        //jasperViewer.setVisible(false);
    }

    /**
     * this method will call the report from data source
     */
    public JasperPrint generateReport(String name) {
        try {
            if (con == null) {
                try {
                    con = Database.getConnection();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            JasperPrint jasperPrint = null;
            if (hm == null) {
                hm = new HashMap();
            }
            //InputStream inStream = null;
            try {
                /**You can also test this line if you want to display
                 * report from any absolute path other than the project root path*/

                 //reportName = "report3";
                //String fullPath = "E:\\Projects\\Spring\\Store\\dplreport\\src\\main\\java\\com\\dpl\\report\\resources\\";
                //String workingDir = System.getProperty("user.dir").replaceAll("\\\\", "/");
                //jasperPrint = JasperFillManager.fillReport(workingDir+"/src/main/java/com/dpl/report/resources/Director.jasper", hm, con);
                // jasperPrint = JasperFillManager.fillReport(workingDir+"\\src\\main\\java\\com\\dpl\\report\\resources\\" + reportName + ".jasper", hm, con);

                // OK
                  jasperPrint = JasperFillManager.fillReport("D:/report/" + name + ".jasper", hm, con);
                 //jasperPrint = JasperFillManager.fillReport("E:/Projects/Spring/Store/dplreport/src/main/java/com/dpl/report/resources/" + reportName + ".jasper", hm, con);
                // jasperPrint = JasperFillManager.fillReport("E:/Projects/Spring/Store/dplreport/src/main/java/com/dpl/report/resources/" + reportName + ".jasper", hm, con);
                // jasperPrint = JasperFillManager.fillReport(reportName + ".jasper", hm, con);


            } catch (JRException e) {
                e.printStackTrace();
            }
            return jasperPrint;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }


    }

    /**
     * call this method when your report has an empty data source
     */
    public JasperPrint generateEmptyDataSourceReport() {
        try {
            JasperPrint jasperPrint = null;
            if (hm == null) {
                hm = new HashMap();
            }
            try {
                reportName = "report3";
                jasperPrint = JasperFillManager.fillReport("E:/Projects/Spring/Store/dplreport/src/main/java/com/dpl/report/resources/" + reportName + ".jasper", hm, new JREmptyDataSource());
            } catch (JRException e) {
                e.printStackTrace();
            }
            return jasperPrint;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }
}
