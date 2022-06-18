package com.example.softwaretest.Controller;

import com.example.softwaretest.Entity.Calendar;
import com.example.softwaretest.Entity.Commission;
import com.example.softwaretest.Entity.Triangle;
import com.example.softwaretest.Mapper.CalendarMapper;
import com.example.softwaretest.Mapper.CommissionMapper;
import com.example.softwaretest.Mapper.TriangleMapper;
import com.example.softwaretest.Result.Response;
import com.example.softwaretest.Service.CalendarService;
import com.example.softwaretest.Service.CommissionService;
import com.example.softwaretest.Service.TriangleService;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;
import java.util.Map;


@RestController
public class TestController {

    @Autowired
    private TriangleService triangleService;

    @Autowired
    private CommissionService commissionService;

    @Autowired
    private CalendarService calendarService;

    /**
     *
     *@author cj
     *@date 2022/6/18 20:19
     * @return List<Triangle>
     * 显示三角形的测试用例
     */
    @RequestMapping(path ="/queryTriangle", method = RequestMethod.GET)
    public List<Triangle> queryTriangle(){
        return triangleService.getTestCases();
    }

    /**
     *
     *@author cj
     *@date 2022/6/18 20:19
     * @return List<Commission>
     * 显示佣金的测试用例
     */
    @RequestMapping(path ="/queryCommission", method = RequestMethod.GET)
    public List<Commission> queryCommission(){
        return commissionService.getTestCases();
    }

    /**
     *
     *@author cj
     *@date 2022/6/18 20:19
     * @return List<Calendar>
     * 显示日历的测试用例
     */
    @RequestMapping(path ="/queryCalendar", method = RequestMethod.GET)
    public List<Calendar> queryCalendar(){
        return calendarService.getTestCases();
    }

    /**
     *
     *@author cj
     *@date 2022/6/18 20:19
     * @param testCases
     * @return Response
     * 测试三角形
     */
    @RequestMapping(path ="/testTriangle" ,method =RequestMethod.POST )
    public Response testTriangle(List<Triangle> testCases){
        List<Integer> results;
        results = triangleService.testTriangle(testCases);
        return Response.success().data((Map<String, Object>) results);
    }

    /**
     *
     *@author cj
     *@date 2022/6/18 20:20
     * @param multipartFile
     * @return Response
     * 上传日历的测试用例
     */
    @RequestMapping(value = "/uploadCalendar",method = RequestMethod.POST)
    public Response  uploadCalendar(@RequestParam("file") MultipartFile multipartFile) throws Exception  {

        //首先清空数据库
        calendarService.cleanTestCases();
        File file = null;
        file = File.createTempFile("tmp", null);
        file.deleteOnExit();
        if(multipartFile.getOriginalFilename().endsWith(".csv")){
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
                    calendar.setYear(Integer.valueOf(s[3]));
                    calendar.setOYear(Integer.valueOf(s[4]));
                    calendar.setOMonth(Integer.valueOf(s[5]));
                    calendar.setODay(Integer.valueOf(s[6]));

                    calendarService.saveTestCases(calendar);
                }
            }catch (Exception e){
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
     *
     *@author cj
     *@date 2022/6/18 20:20
     * @param multipartFile
     * @return Response
     * 上传三角形的测试用例
     */
    @RequestMapping(value = "/uploadTriangle",method = RequestMethod.POST)
    public Response  uploadTriangle(@RequestParam("file") MultipartFile multipartFile) throws Exception  {

        //首先清空数据库
        triangleService.cleanTestCases();
        File file = null;
        file = File.createTempFile("tmp", null);
        file.deleteOnExit();
        if(multipartFile.getOriginalFilename().endsWith(".csv")){
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
                    triangle.setA(Integer.valueOf(s[0]));
                    triangle.setB(Integer.valueOf(s[1]));
                    triangle.setC(Integer.valueOf(s[2]));
                    triangle.setExpectResult(s[3]);


                    if(triangleService.testTriangle(triangle).equals(triangle.getExpectResult())){
                        triangle.setPass(1);
                    }else{
                        triangle.setPass(0);
                    }
                    triangleService.saveTestCases(triangle);

                }
            }catch (Exception e){
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

    @RequestMapping(value = "/uploadCommission",method = RequestMethod.POST)
    public Response  uploadCommission(@RequestParam("file") MultipartFile multipartFile) throws Exception  {

        //首先清空数据库
        commissionService.cleanTestCases();
        File file = null;
        file = File.createTempFile("tmp", null);
        file.deleteOnExit();
        if(multipartFile.getOriginalFilename().endsWith(".csv")){
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
                    commission.setSales(Integer.valueOf(s[3]));
                    commission.setCommission(Double.valueOf(s[4]));



                    commissionService.saveTestCases(commission);

                }
            }catch (Exception e){
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
}
