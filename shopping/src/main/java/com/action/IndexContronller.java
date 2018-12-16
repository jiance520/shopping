package com.action;

import com.alibaba.fastjson.JSON;
import com.entity.Bill;
import com.entity.Provider;
import com.entity.Users;
import com.service.IBillService;
import com.service.IProviderService;
import com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class IndexContronller {
    @Autowired
    private IUserService userService;
    @Autowired
    private IProviderService providerService;
    @Autowired
    private IBillService billService;
//根据参数加载所有订单数据
    @RequestMapping(value = "/allAction", produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public Object allAction(String productname,Integer providerid,Integer ispayment,Integer page,Integer rows,String sort,String order) {
        System.out.println(" page = "+page);//第几页
        System.out.println(" rows = "+rows);//一页几行
        System.out.println(" sort = "+sort);//按哪个字段排
        System.out.println(" order = "+order);// 正序还是反序
        System.out.println(" productname = "+productname);

        System.out.println("---allAction");
        boolean flag = (productname!=null&&!("").equals(productname))||(providerid!=null)||(ispayment!=null);
        List<Bill> listAll = billService.selectAll();
        Map<String,Object> map = new HashMap<String,Object>();
        Map<String,Object> mapgrid = new HashMap<String,Object>();
        Integer startIndex = 0;
        if(page!=null){
            startIndex=(page-1)*rows;
        }
        if(rows==null){
            rows=8;
        }
        if(flag==true){
            //如果不为空，进行模糊查询
            map.put("productname",productname);
            map.put("providerid",providerid);
            map.put("ispayment",ispayment);
            map.put("startIndex",startIndex);
            map.put("rows",rows);
            map.put("sort",sort);
            map.put("order",order);
            List<Map<String,Object>> list = billService.selectSearchBill(map);
            System.out.println("map:"+map);
            mapgrid.put("total",listAll.size());//是表中总记录数，不是分页的记录数！
            mapgrid.put("rows",list);
            System.out.println("模糊查询:"+mapgrid);
        }
        else{
            map.put("startIndex",startIndex);
            map.put("rows",rows);
            map.put("sort",sort);
            map.put("order",order);
            System.out.println("map:"+map);
            List<Map<String,Object>> list = billService.selectAllMap(map);
            mapgrid.put("total",listAll.size());//是表中总记录数，不是分页的记录数！
            mapgrid.put("rows",list);
            System.out.println("查所有:"+mapgrid);
        }
        return mapgrid;
    }
//加载所有供应商数据
    @RequestMapping(value = "/prviderAction", produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public String pronameAction() {
        String js = "";
        System.out.println("---prviderAction");
        List<Provider> list = providerService.selectAll();
        js = JSON.toJSONString(list);
        return js;
    }
//加载所有订单数据
    @RequestMapping(value = "/billAction", produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public String billAction() {
        String js = "";
        System.out.println("---billAction");
        List<Bill> list = billService.selectAll();
        js = JSON.toJSONString(list);
        return js;
    }
    @RequestMapping("/loginAction")
    public void loginAction(HttpServletResponse response, String usercode, String userpassword, HttpSession session) throws IOException {
        PrintWriter out = response.getWriter();
        System.out.println(usercode);
        System.out.println(userpassword);
        Map map = new HashMap();
        map.put("usercode",usercode);
        map.put("userpassword",userpassword);
        Users users = userService.selectLogin(map);
        if(users!=null){
            session.setAttribute("users",users);
        }
        String js = "";
        js = JSON.toJSONString(users);
        System.out.println(js);
        if(users!=null){
            out.print(js);
        }
        out.flush();
        out.close();
    }
    @ResponseBody
    @RequestMapping(value="/exitAction",produces={"application/json;chart=UTF-8"})
    public Object exitAction(HttpSession session) throws IOException {
        session.removeAttribute("users");
        String js = "";
        js = JSON.toJSONString("true");
        return js;
    }
    @RequestMapping("/updateBill")
    public void updateBill(HttpServletResponse response,@RequestParam Map<String,String> row) throws Exception{
        PrintWriter out = response.getWriter();
        System.out.println("row"+row);
        Bill bill = new Bill();
        bill.setId(Long.valueOf((String)row.get("id")));
        bill.setBillcode((String)row.get("billCode"));
        bill.setProductname((String)row.get("productName"));
        bill.setTotalprice(BigDecimal.valueOf(Long.parseLong((String)row.get("totalPrice"))));
        bill.setIspayment(Integer.valueOf((String)row.get("isPayment")));
        System.out.println(row.get("creationDate"));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);//创建带有时区的日期格式。
        Date date = dateFormat.parse(row.get("creationDate"));
        bill.setCreationdate(date);
        long providerId = Long.valueOf((String)row.get("providerId"));
        bill.setProviderid(providerId);
        int num = billService.updateByPrimaryKeySelective(bill);
        if(num > 0){
            out.print("true");
        }else{
            out.print("false");
        }
        out.flush();
        out.close();
    }
    @ResponseBody
    @RequestMapping(value="/delBill",produces={"application/json;chart=UTF-8"})
    public Object delBill(String billcode){
        System.out.println("billCode:"+billcode);
        int num  = billService.deleteByBillCode(billcode);
        String json = JSON.toJSONString(num);
        System.out.println(" json = "+json);
        return json;
    }
    @ResponseBody
    @RequestMapping(value="/addBill",produces={"application/json;chart=UTF-8"})
    public Object addBill(@RequestParam Map<String,String> map){
        System.out.println("map"+map);
        Bill bill = new Bill();
        bill.setBillcode((String)map.get("billCode"));
        bill.setProductname((String)map.get("productName"));
        bill.setProductunit((String)map.get("productUnit"));
        bill.setProductcount(BigDecimal.valueOf(Long.parseLong((String)map.get("productCount"))));
        bill.setTotalprice(BigDecimal.valueOf(Long.parseLong((String)map.get("totalPrice"))));
        bill.setIspayment(Integer.valueOf((String)map.get("isPayment")));
        bill.setProviderid(Long.valueOf((String)map.get("proName")));
        Date date = new Date();
        bill.setCreationdate(date);
        bill.setCreatedby((long)1);
        int num = billService.insertSelective(bill);
        String json = JSON.toJSONString(num);
        System.out.println(" json = "+json);
        return json;
    }
}