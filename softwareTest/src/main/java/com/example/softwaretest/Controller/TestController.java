package com.example.softwaretest.Controller;

import com.example.softwaretest.Entity.Calendar;
import com.example.softwaretest.Entity.Commission;
import com.example.softwaretest.Entity.Phone;
import com.example.softwaretest.Entity.Triangle;
import com.example.softwaretest.Result.Response;
import com.example.softwaretest.Service.CalendarService;
import com.example.softwaretest.Service.CommissionService;
import com.example.softwaretest.Service.PhoneService;
import com.example.softwaretest.Service.TriangleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;


@RestController
public class TestController {

    @Autowired
    private TriangleService triangleService;

    @Autowired
    private CommissionService commissionService;

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private PhoneService phoneService;

    /**
     * @return List<Triangle>
     * 显示三角形的测试用例
     * @author cj
     * @date 2022/6/18 20:19
     */
    @RequestMapping(path = "/queryTriangle", method = RequestMethod.GET)
    public List<Triangle> queryTriangle() {
        return triangleService.getTestCases();
    }

    /**
     * @return List<Commission>
     * 显示佣金的测试用例
     * @author cj
     * @date 2022/6/18 20:19
     */
    @RequestMapping(path = "/queryCommission", method = RequestMethod.GET)
    public List<Commission> queryCommission() {
        return commissionService.getTestCases();
    }

    /**
     * @return List<Calendar>
     * 显示日历的测试用例
     * @author cj
     * @date 2022/6/18 20:19
     */
    @RequestMapping(path = "/queryCalendar", method = RequestMethod.GET)
    public List<Calendar> queryCalendar() {
        return calendarService.getTestCases();
    }

    /**
     * @return List<Phone>
     * @author cj
     * @date 2022/6/18 23:22
     * 获取电话问题的测试用例
     */
    @RequestMapping(path = "/queryPhone", method = RequestMethod.GET)
    public List<Phone> queryPhone() {
        return phoneService.getTestCases();
    }

    /**
     * @return Double
     * @author cj
     * @date 2022/6/19 13:09
     * 获取万年历通过率
     */
    @RequestMapping(path = "/queryCalendarPass", method = RequestMethod.GET)
    public Double queryCalendarPass() {
        double pass = calendarService.queryPass();
        double total = calendarService.total();
        if (total != 0) {
            return pass / total;
        } else {
            return -1.0;
        }
    }

    /**
     * @return Double
     * @author cj
     * @date 2022/6/19 13:10
     * 查询佣金问题通过率
     */
    @RequestMapping(path = "/queryCommissionPass", method = RequestMethod.GET)
    public Double queryCommissionPass() {
        double pass = commissionService.queryPass();
        double total = commissionService.total();

        if (total != 0) {
            return pass / total;
        } else {
            return -1.0;
        }
    }

    /**
     * @return Double
     * @author cj
     * @date 2022/6/19 13:11
     * 获取电话问题通过率
     */
    @RequestMapping(path = "/queryPhonePass", method = RequestMethod.GET)
    public Double queryPhonePass() {
        double pass = phoneService.queryPass();
        double total = phoneService.total();

        if (total != 0) {
            return pass / total;
        } else {
            return -1.0;
        }
    }

    /**
     * @return Double
     * @author cj
     * @date 2022/6/19 13:11
     * 获取三角形通过率
     */
    @RequestMapping(path = "/queryTrianglePass", method = RequestMethod.GET)
    public Double queryTrianglePass() {
        double pass = triangleService.queryPass();
        double total = triangleService.total();

        if (total != 0) {
            return pass / total;
        } else {
            return -1.0;
        }
    }

    /**
     * @param multipartFile
     * @return Response
     * 上传日历的测试用例
     * @author cj
     * @date 2022/6/18 20:20
     */
    @RequestMapping(value = "/uploadCalendar", method = RequestMethod.POST)
    public Response uploadCalendar(@RequestParam("file") MultipartFile multipartFile) throws Exception {

        //首先清空数据库
        calendarService.cleanTestCases();
        File file = null;
        file = File.createTempFile("tmp", null);
        file.deleteOnExit();
        if (multipartFile.getOriginalFilename().endsWith(".CSV")) {
            System.out.println(multipartFile.getOriginalFilename());
            BufferedReader reader = null;
            String temp = null;
            try {
                multipartFile.transferTo(file); //MultipartFile转File
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));//解决服务器上乱码
                reader.readLine();
                while ((temp = reader.readLine()) != null) {
                    Calendar calendar = new Calendar();
                    String[] s = temp.split(",");
                    calendar.setYear(Integer.valueOf(s[0]));
                    calendar.setMonth(Integer.valueOf(s[1]));
                    calendar.setDay(Integer.valueOf(s[2]));
                    calendar.setExpectResult(s[3]);

                    String result = calendarService.testCalendar(calendar);
                    calendar.setActualResult(result);
                    if (result.equals(calendar.getExpectResult())) {
                        calendar.setPass(1);
                    } else {
                        calendar.setPass(0);
                    }

                    calendarService.saveTestCases(calendar);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return Response.success().message("上传成功");
    }

    /**
     * @param multipartFile
     * @return Response
     * 上传三角形的测试用例
     * @author cj
     * @date 2022/6/18 20:20
     */
    @RequestMapping(value = "/uploadTriangle", method = RequestMethod.POST)
    public Response uploadTriangle(@RequestParam("file") MultipartFile multipartFile) throws Exception {

        //首先清空数据库
        triangleService.cleanTestCases();
        File file = null;
        file = File.createTempFile("tmp", null);
        file.deleteOnExit();
        if (multipartFile.getOriginalFilename().endsWith(".CSV")) {
            System.out.println(multipartFile.getOriginalFilename());
            BufferedReader reader = null;
            String temp = null;
            try {
                multipartFile.transferTo(file); //MultipartFile转File
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));//解决服务器上乱码
                reader.readLine();
                while ((temp = reader.readLine()) != null) {
                    Triangle triangle = new Triangle();
                    String[] s = temp.split(",");
                    triangle.setA(Double.valueOf(s[0]));
                    triangle.setB(Double.valueOf(s[1]));
                    triangle.setC(Double.valueOf(s[2]));
                    triangle.setExpectResult(s[3]);

                    triangle.setActualResult(triangleService.testTriangle(triangle));
                    if (triangle.getActualResult().equals(triangle.getExpectResult())) {
                        triangle.setPass(1);
                    } else {
                        triangle.setPass(0);
                    }
                    triangleService.saveTestCases(triangle);

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return Response.success().message("上传成功");
    }

    /**
     * @param multipartFile
     * @return Response
     * 上传并测试佣金问题
     * @author cj
     * @date 2022/6/18 23:23
     */
    @RequestMapping(value = "/uploadCommission", method = RequestMethod.POST)
    public Response uploadCommission(@RequestParam("file") MultipartFile multipartFile) throws Exception {

        //首先清空数据库
        commissionService.cleanTestCases();
        System.out.println("cj");
        File file = null;
        file = File.createTempFile("tmp", null);
        file.deleteOnExit();
        if (multipartFile.getOriginalFilename().endsWith(".CSV")) {
            System.out.println(multipartFile.getOriginalFilename());
            BufferedReader reader = null;
            String temp = null;
            try {
                multipartFile.transferTo(file); //MultipartFile转File
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));//解决服务器上乱码
                reader.readLine();
                while ((temp = reader.readLine()) != null) {
                    Commission commission = new Commission();
                    String[] s = temp.split(",");
                    commission.setHost(Integer.valueOf(s[0]));
                    commission.setDisplay(Integer.valueOf(s[1]));
                    commission.setPeripheral(Integer.valueOf(s[2]));
                    commission.setAfter(Integer.valueOf(s[3]));
                    commission.setSales(Integer.valueOf(s[4]));
                    commission.setCommission(Double.valueOf(s[5]));

                    double result = commissionService.testCommission(commission);
                    commission.setActualCommission(result);
                    if (result == commission.getCommission()) {
                        commission.setPass(1);
                    } else {
                        commission.setPass(0);
                    }

                    commissionService.saveTestCases(commission);

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return Response.success().message("上传成功");
    }

    /**
     * @param multipartFile
     * @return Response
     * 上传并测试电话费用问题
     * @author cj
     * @date 2022/6/18 23:24
     */
    @RequestMapping(value = "/uploadPhone", method = RequestMethod.POST)
    public Response uploadPhone(@RequestParam("file") MultipartFile multipartFile) throws Exception {

        //首先清空数据库
        phoneService.cleanTestCases();
        File file = null;
        file = File.createTempFile("tmp", null);
        file.deleteOnExit();
        if (multipartFile.getOriginalFilename().endsWith(".CSV")) {
            System.out.println(multipartFile.getOriginalFilename());
            BufferedReader reader = null;
            String temp = null;
            try {
                multipartFile.transferTo(file); //MultipartFile转File
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));//解决服务器上乱码
                reader.readLine();
                while ((temp = reader.readLine()) != null) {
                    Phone phone = new Phone();
                    String[] s = temp.split(",");
                    phone.setLength(Integer.valueOf(s[0]));
                    phone.setTimes(Integer.valueOf(s[1]));
                    phone.setExpectOff(Double.valueOf(s[2]));
                    phone.setExpectResult(Double.valueOf(s[3]));


                    double result = phoneService.testPhone(phone);
                    phone.setActualOff(result);
                    if (result != -1) {
                        phone.setActualResult(25 + 0.15 * phone.getLength() * (1 - result));
                    }
                    if (result == phone.getExpectOff()) {
                        phone.setPass(1);
                    } else {
                        phone.setPass(0);
                    }
                    phoneService.saveTestCases(phone);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return Response.success().message("上传成功");
    }


    /**
     *单独测试日历接口
     *@author cj
     *@date 2022/6/20 12:13
     * @param calendar
     * @return Response
     */
    @RequestMapping(value = "/testOneCalendar", method = RequestMethod.POST)
    public Response testOneCalendar(@RequestBody Calendar calendar){

         String result =calendarService.testCalendar(calendar);
         if(result.equals(calendar.getExpectResult())){
             calendar.setPass(1);
             calendar.setActualResult(result);
         }else{
             calendar.setPass(0);
             calendar.setActualResult(result);
         }
         return Response.success().message("测试成功").data("Calendar",calendar);
    }

    /**
     *单独测试佣金
     *@author cj
     *@date 2022/6/20 12:17
     * @param commission
     * @return Response
     */
    @RequestMapping(value = "/testOneCommission", method = RequestMethod.POST)
    public Response testCommission(@RequestBody Commission commission){

        Double result =commissionService.testCommission(commission);
        if(result.equals(commission.getCommission())){
            commission.setPass(1);
            commission.setActualCommission(result);
        }else{
            commission.setPass(0);
            commission.setActualCommission(result);
        }
        return Response.success().message("测试成功").data("Commission",commission);
    }

    /**
     *单独测试电话
     *@author cj
     *@date 2022/6/20 12:17
     * @param phone
     * @return Response
     */
    @RequestMapping(value = "/testOnePhone", method = RequestMethod.POST)
    public Response testOnePhone(@RequestBody Phone phone){

        Double result =phoneService.testPhone(phone);
        if(result.equals(phone.getExpectResult())){
            phone.setPass(1);
            phone.setActualResult(result);
        }else{
            phone.setPass(0);
            phone.setActualResult(result);
        }
        return Response.success().message("测试成功").data("Phone",phone);
    }

    /**
     *单独测试三角形
     *@author cj
     *@date 2022/6/20 12:19
     * @param triangle
     * @return Response
     */
    @RequestMapping(value = "/testOneTriangle", method = RequestMethod.POST)
    public Response testOneTriangle(@RequestBody Triangle triangle){

        String result =triangleService.testTriangle(triangle);
        if(result.equals(triangle.getExpectResult())){
            triangle.setPass(1);
            triangle.setActualResult(result);
        }else{
            triangle.setPass(0);
            triangle.setActualResult(result);
        }
        return Response.success().message("测试成功").data("Triangle",triangle);
    }



}
