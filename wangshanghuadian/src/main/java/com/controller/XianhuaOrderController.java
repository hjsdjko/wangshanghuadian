
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 鲜花订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/xianhuaOrder")
public class XianhuaOrderController {
    private static final Logger logger = LoggerFactory.getLogger(XianhuaOrderController.class);

    private static final String TABLE_NAME = "xianhuaOrder";

    @Autowired
    private XianhuaOrderService xianhuaOrderService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private AddressService addressService;//收货地址
    @Autowired
    private CartService cartService;//购物车
    @Autowired
    private ChatService chatService;//客服聊天
    @Autowired
    private DictionaryService dictionaryService;//字典表
    @Autowired
    private ForumService forumService;//论坛交流
    @Autowired
    private GonggaoService gonggaoService;//公告信息
    @Autowired
    private XianhuaService xianhuaService;//鲜花
    @Autowired
    private XianhuaCollectionService xianhuaCollectionService;//鲜花收藏
    @Autowired
    private XianhuaCommentbackService xianhuaCommentbackService;//鲜花评价
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = xianhuaOrderService.queryPage(params);

        //字典表数据转换
        List<XianhuaOrderView> list =(List<XianhuaOrderView>)page.getList();
        for(XianhuaOrderView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        XianhuaOrderEntity xianhuaOrder = xianhuaOrderService.selectById(id);
        if(xianhuaOrder !=null){
            //entity转view
            XianhuaOrderView view = new XianhuaOrderView();
            BeanUtils.copyProperties( xianhuaOrder , view );//把实体数据重构到view中
            //级联表 收货地址
            //级联表
            AddressEntity address = addressService.selectById(xianhuaOrder.getAddressId());
            if(address != null){
            BeanUtils.copyProperties( address , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setAddressId(address.getId());
            }
            //级联表 鲜花
            //级联表
            XianhuaEntity xianhua = xianhuaService.selectById(xianhuaOrder.getXianhuaId());
            if(xianhua != null){
            BeanUtils.copyProperties( xianhua , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setXianhuaId(xianhua.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(xianhuaOrder.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody XianhuaOrderEntity xianhuaOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,xianhuaOrder:{}",this.getClass().getName(),xianhuaOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            xianhuaOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        xianhuaOrder.setCreateTime(new Date());
        xianhuaOrder.setInsertTime(new Date());
        xianhuaOrderService.insert(xianhuaOrder);

        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody XianhuaOrderEntity xianhuaOrder, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,xianhuaOrder:{}",this.getClass().getName(),xianhuaOrder.toString());
        XianhuaOrderEntity oldXianhuaOrderEntity = xianhuaOrderService.selectById(xianhuaOrder.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            xianhuaOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            xianhuaOrderService.updateById(xianhuaOrder);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<XianhuaOrderEntity> oldXianhuaOrderList =xianhuaOrderService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        xianhuaOrderService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<XianhuaOrderEntity> xianhuaOrderList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            XianhuaOrderEntity xianhuaOrderEntity = new XianhuaOrderEntity();
//                            xianhuaOrderEntity.setXianhuaOrderUuidNumber(data.get(0));                    //订单编号 要改的
//                            xianhuaOrderEntity.setAddressId(Integer.valueOf(data.get(0)));   //收货地址 要改的
//                            xianhuaOrderEntity.setXianhuaId(Integer.valueOf(data.get(0)));   //鲜花 要改的
//                            xianhuaOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            xianhuaOrderEntity.setBuyNumber(Integer.valueOf(data.get(0)));   //购买数量 要改的
//                            xianhuaOrderEntity.setXianhuaOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            xianhuaOrderEntity.setXianhuaOrderCourierName(data.get(0));                    //派送人 要改的
//                            xianhuaOrderEntity.setXianhuaOrderCourierNumber(data.get(0));                    //联系方式 要改的
//                            xianhuaOrderEntity.setXianhuaOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            xianhuaOrderEntity.setXianhuaOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            xianhuaOrderEntity.setInsertTime(date);//时间
//                            xianhuaOrderEntity.setCreateTime(date);//时间
                            xianhuaOrderList.add(xianhuaOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单编号
                                if(seachFields.containsKey("xianhuaOrderUuidNumber")){
                                    List<String> xianhuaOrderUuidNumber = seachFields.get("xianhuaOrderUuidNumber");
                                    xianhuaOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> xianhuaOrderUuidNumber = new ArrayList<>();
                                    xianhuaOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("xianhuaOrderUuidNumber",xianhuaOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单编号
                        List<XianhuaOrderEntity> xianhuaOrderEntities_xianhuaOrderUuidNumber = xianhuaOrderService.selectList(new EntityWrapper<XianhuaOrderEntity>().in("xianhua_order_uuid_number", seachFields.get("xianhuaOrderUuidNumber")));
                        if(xianhuaOrderEntities_xianhuaOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(XianhuaOrderEntity s:xianhuaOrderEntities_xianhuaOrderUuidNumber){
                                repeatFields.add(s.getXianhuaOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        xianhuaOrderService.insertBatch(xianhuaOrderList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = xianhuaOrderService.queryPage(params);

        //字典表数据转换
        List<XianhuaOrderView> list =(List<XianhuaOrderView>)page.getList();
        for(XianhuaOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        XianhuaOrderEntity xianhuaOrder = xianhuaOrderService.selectById(id);
            if(xianhuaOrder !=null){


                //entity转view
                XianhuaOrderView view = new XianhuaOrderView();
                BeanUtils.copyProperties( xianhuaOrder , view );//把实体数据重构到view中

                //级联表
                    AddressEntity address = addressService.selectById(xianhuaOrder.getAddressId());
                if(address != null){
                    BeanUtils.copyProperties( address , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setAddressId(address.getId());
                }
                //级联表
                    XianhuaEntity xianhua = xianhuaService.selectById(xianhuaOrder.getXianhuaId());
                if(xianhua != null){
                    BeanUtils.copyProperties( xianhua , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setXianhuaId(xianhua.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(xianhuaOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody XianhuaOrderEntity xianhuaOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,xianhuaOrder:{}",this.getClass().getName(),xianhuaOrder.toString());
            XianhuaEntity xianhuaEntity = xianhuaService.selectById(xianhuaOrder.getXianhuaId());
            if(xianhuaEntity == null){
                return R.error(511,"查不到该鲜花");
            }
            // Double xianhuaNewMoney = xianhuaEntity.getXianhuaNewMoney();

            if(false){
            }
            else if(xianhuaEntity.getXianhuaNewMoney() == null){
                return R.error(511,"现价不能为空");
            }
            else if((xianhuaEntity.getXianhuaKucunNumber() -xianhuaOrder.getBuyNumber())<0){
                return R.error(511,"购买数量不能大于库存数量");
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");
            double balance = yonghuEntity.getNewMoney() - xianhuaEntity.getXianhuaNewMoney()*xianhuaOrder.getBuyNumber();//余额
            if(balance<0)
                return R.error(511,"余额不够支付");
            xianhuaOrder.setXianhuaOrderTypes(101); //设置订单状态为已支付
            xianhuaOrder.setXianhuaOrderTruePrice(xianhuaEntity.getXianhuaNewMoney()*xianhuaOrder.getBuyNumber()); //设置实付价格
            xianhuaOrder.setYonghuId(userId); //设置订单支付人id
            xianhuaOrder.setXianhuaOrderUuidNumber(String.valueOf(new Date().getTime()));
            xianhuaOrder.setXianhuaOrderPaymentTypes(1);
            xianhuaOrder.setInsertTime(new Date());
            xianhuaOrder.setCreateTime(new Date());
                xianhuaEntity.setXianhuaKucunNumber( xianhuaEntity.getXianhuaKucunNumber() -xianhuaOrder.getBuyNumber());
                xianhuaService.updateById(xianhuaEntity);
                xianhuaOrderService.insert(xianhuaOrder);//新增订单
            //更新第一注册表
            yonghuEntity.setNewMoney(balance);//设置金额
            yonghuService.updateById(yonghuEntity);


            return R.ok();
    }
    /**
     * 添加订单
     */
    @RequestMapping("/order")
    public R add(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("order方法:,,Controller:{},,params:{}",this.getClass().getName(),params.toString());
        String xianhuaOrderUuidNumber = String.valueOf(new Date().getTime());

        //获取当前登录用户的id
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        Integer addressId = Integer.valueOf(String.valueOf(params.get("addressId")));

            Integer xianhuaOrderPaymentTypes = Integer.valueOf(String.valueOf(params.get("xianhuaOrderPaymentTypes")));//支付类型

        String data = String.valueOf(params.get("xianhuas"));
        JSONArray jsonArray = JSON.parseArray(data);
        List<Map> xianhuas = JSON.parseObject(jsonArray.toString(), List.class);

        //获取当前登录用户的个人信息
        YonghuEntity yonghuEntity = yonghuService.selectById(userId);

        //当前订单表
        List<XianhuaOrderEntity> xianhuaOrderList = new ArrayList<>();
        //商品表
        List<XianhuaEntity> xianhuaList = new ArrayList<>();
        //购物车ids
        List<Integer> cartIds = new ArrayList<>();

        BigDecimal zhekou = new BigDecimal(1.0);

        //循环取出需要的数据
        for (Map<String, Object> map : xianhuas) {
           //取值
            Integer xianhuaId = Integer.valueOf(String.valueOf(map.get("xianhuaId")));//商品id
            Integer buyNumber = Integer.valueOf(String.valueOf(map.get("buyNumber")));//购买数量
            XianhuaEntity xianhuaEntity = xianhuaService.selectById(xianhuaId);//购买的商品
            String id = String.valueOf(map.get("id"));
            if(StringUtil.isNotEmpty(id))
                cartIds.add(Integer.valueOf(id));

            //判断商品的库存是否足够
            if(xianhuaEntity.getXianhuaKucunNumber() < buyNumber){
                //商品库存不足直接返回
                return R.error(xianhuaEntity.getXianhuaName()+"的库存不足");
            }else{
                //商品库存充足就减库存
                xianhuaEntity.setXianhuaKucunNumber(xianhuaEntity.getXianhuaKucunNumber() - buyNumber);
            }

            //订单信息表增加数据
            XianhuaOrderEntity xianhuaOrderEntity = new XianhuaOrderEntity<>();

            //赋值订单信息
            xianhuaOrderEntity.setXianhuaOrderUuidNumber(xianhuaOrderUuidNumber);//订单编号
            xianhuaOrderEntity.setAddressId(addressId);//收货地址
            xianhuaOrderEntity.setXianhuaId(xianhuaId);//鲜花
                        xianhuaOrderEntity.setYonghuId(userId);//用户
            xianhuaOrderEntity.setBuyNumber(buyNumber);//购买数量 ？？？？？？
            xianhuaOrderEntity.setXianhuaOrderTypes(101);//订单类型
            xianhuaOrderEntity.setXianhuaOrderPaymentTypes(xianhuaOrderPaymentTypes);//支付类型
            xianhuaOrderEntity.setInsertTime(new Date());//订单创建时间
            xianhuaOrderEntity.setCreateTime(new Date());//创建时间

            //判断是什么支付方式 1代表余额 2代表积分
            if(xianhuaOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = new BigDecimal(xianhuaEntity.getXianhuaNewMoney()).multiply(new BigDecimal(buyNumber)).multiply(zhekou).doubleValue();

                if(yonghuEntity.getNewMoney() - money <0 ){
                    return R.error("余额不足,请充值！！！");
                }else{
                    //计算所获得积分
                    Double buyJifen =0.0;
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() - money); //设置金额


                    xianhuaOrderEntity.setXianhuaOrderTruePrice(money);

                }
            }
            xianhuaOrderList.add(xianhuaOrderEntity);
            xianhuaList.add(xianhuaEntity);

        }
        xianhuaOrderService.insertBatch(xianhuaOrderList);
        xianhuaService.updateBatchById(xianhuaList);
        yonghuService.updateById(yonghuEntity);
        if(cartIds != null && cartIds.size()>0)
            cartService.deleteBatchIds(cartIds);

        return R.ok();
    }


    /**
    * 退款
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            XianhuaOrderEntity xianhuaOrder = xianhuaOrderService.selectById(id);//当前表service
            Integer buyNumber = xianhuaOrder.getBuyNumber();
            Integer xianhuaOrderPaymentTypes = xianhuaOrder.getXianhuaOrderPaymentTypes();
            Integer xianhuaId = xianhuaOrder.getXianhuaId();
            if(xianhuaId == null)
                return R.error(511,"查不到该鲜花");
            XianhuaEntity xianhuaEntity = xianhuaService.selectById(xianhuaId);
            if(xianhuaEntity == null)
                return R.error(511,"查不到该鲜花");
            Double xianhuaNewMoney = xianhuaEntity.getXianhuaNewMoney();
            if(xianhuaNewMoney == null)
                return R.error(511,"鲜花价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
            return R.error(511,"用户金额不能为空");
            Double zhekou = 1.0;

            //判断是什么支付方式 1代表余额 2代表积分
            if(xianhuaOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = xianhuaEntity.getXianhuaNewMoney() * buyNumber  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额


            }

            xianhuaEntity.setXianhuaKucunNumber(xianhuaEntity.getXianhuaKucunNumber() + buyNumber);

            xianhuaOrder.setXianhuaOrderTypes(102);//设置订单状态为已退款
            xianhuaOrderService.updateAllColumnById(xianhuaOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            xianhuaService.updateById(xianhuaEntity);//更新订单中鲜花的信息

            return R.ok();
    }

    /**
    * 评价
    */
    @RequestMapping("/commentback")
    public R commentback(Integer id, String commentbackText, Integer xianhuaCommentbackPingfenNumber, HttpServletRequest request){
        logger.debug("commentback方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
            XianhuaOrderEntity xianhuaOrder = xianhuaOrderService.selectById(id);
        if(xianhuaOrder == null)
            return R.error(511,"查不到该订单");
        Integer xianhuaId = xianhuaOrder.getXianhuaId();
        if(xianhuaId == null)
            return R.error(511,"查不到该鲜花");

        XianhuaCommentbackEntity xianhuaCommentbackEntity = new XianhuaCommentbackEntity();
            xianhuaCommentbackEntity.setId(id);
            xianhuaCommentbackEntity.setXianhuaId(xianhuaId);
            xianhuaCommentbackEntity.setYonghuId((Integer) request.getSession().getAttribute("userId"));
            xianhuaCommentbackEntity.setXianhuaCommentbackText(commentbackText);
            xianhuaCommentbackEntity.setInsertTime(new Date());
            xianhuaCommentbackEntity.setReplyText(null);
            xianhuaCommentbackEntity.setUpdateTime(null);
            xianhuaCommentbackEntity.setCreateTime(new Date());
            xianhuaCommentbackService.insert(xianhuaCommentbackEntity);

            xianhuaOrder.setXianhuaOrderTypes(105);//设置订单状态为已评价
            xianhuaOrderService.updateById(xianhuaOrder);//根据id更新
            return R.ok();
    }

    /**
     * 发货
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id ,String xianhuaOrderCourierNumber, String xianhuaOrderCourierName , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        XianhuaOrderEntity  xianhuaOrderEntity = xianhuaOrderService.selectById(id);
        xianhuaOrderEntity.setXianhuaOrderTypes(103);//设置订单状态为已发货
        xianhuaOrderEntity.setXianhuaOrderCourierNumber(xianhuaOrderCourierNumber);
        xianhuaOrderEntity.setXianhuaOrderCourierName(xianhuaOrderCourierName);
        xianhuaOrderService.updateById( xianhuaOrderEntity);

        return R.ok();
    }


    /**
     * 收货
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        XianhuaOrderEntity  xianhuaOrderEntity = xianhuaOrderService.selectById(id);
        xianhuaOrderEntity.setXianhuaOrderTypes(104);//设置订单状态为收货
        xianhuaOrderService.updateById( xianhuaOrderEntity);
        return R.ok();
    }

}

