
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
 * 鲜花
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/xianhua")
public class XianhuaController {
    private static final Logger logger = LoggerFactory.getLogger(XianhuaController.class);

    private static final String TABLE_NAME = "xianhua";

    @Autowired
    private XianhuaService xianhuaService;


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
    private XianhuaCollectionService xianhuaCollectionService;//鲜花收藏
    @Autowired
    private XianhuaCommentbackService xianhuaCommentbackService;//鲜花评价
    @Autowired
    private XianhuaOrderService xianhuaOrderService;//鲜花订单
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
        params.put("xianhuaDeleteStart",1);params.put("xianhuaDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = xianhuaService.queryPage(params);

        //字典表数据转换
        List<XianhuaView> list =(List<XianhuaView>)page.getList();
        for(XianhuaView c:list){
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
        XianhuaEntity xianhua = xianhuaService.selectById(id);
        if(xianhua !=null){
            //entity转view
            XianhuaView view = new XianhuaView();
            BeanUtils.copyProperties( xianhua , view );//把实体数据重构到view中
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
    public R save(@RequestBody XianhuaEntity xianhua, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,xianhua:{}",this.getClass().getName(),xianhua.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<XianhuaEntity> queryWrapper = new EntityWrapper<XianhuaEntity>()
            .eq("xianhua_name", xianhua.getXianhuaName())
            .eq("xianhua_huayu", xianhua.getXianhuaHuayu())
            .eq("zan_number", xianhua.getZanNumber())
            .eq("cai_number", xianhua.getCaiNumber())
            .eq("xianhua_types", xianhua.getXianhuaTypes())
            .eq("xianhua_kucun_number", xianhua.getXianhuaKucunNumber())
            .eq("shangxia_types", xianhua.getShangxiaTypes())
            .eq("xianhua_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XianhuaEntity xianhuaEntity = xianhuaService.selectOne(queryWrapper);
        if(xianhuaEntity==null){
            xianhua.setZanNumber(1);
            xianhua.setCaiNumber(1);
            xianhua.setXianhuaClicknum(1);
            xianhua.setShangxiaTypes(1);
            xianhua.setXianhuaDelete(1);
            xianhua.setInsertTime(new Date());
            xianhua.setCreateTime(new Date());
            xianhuaService.insert(xianhua);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody XianhuaEntity xianhua, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,xianhua:{}",this.getClass().getName(),xianhua.toString());
        XianhuaEntity oldXianhuaEntity = xianhuaService.selectById(xianhua.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(xianhua.getXianhuaPhoto()) || "null".equals(xianhua.getXianhuaPhoto())){
                xianhua.setXianhuaPhoto(null);
        }
        if("".equals(xianhua.getXianhuaContent()) || "null".equals(xianhua.getXianhuaContent())){
                xianhua.setXianhuaContent(null);
        }

            xianhuaService.updateById(xianhua);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<XianhuaEntity> oldXianhuaList =xianhuaService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<XianhuaEntity> list = new ArrayList<>();
        for(Integer id:ids){
            XianhuaEntity xianhuaEntity = new XianhuaEntity();
            xianhuaEntity.setId(id);
            xianhuaEntity.setXianhuaDelete(2);
            list.add(xianhuaEntity);
        }
        if(list != null && list.size() >0){
            xianhuaService.updateBatchById(list);
        }

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
            List<XianhuaEntity> xianhuaList = new ArrayList<>();//上传的东西
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
                            XianhuaEntity xianhuaEntity = new XianhuaEntity();
//                            xianhuaEntity.setXianhuaName(data.get(0));                    //鲜花名称 要改的
//                            xianhuaEntity.setXianhuaUuidNumber(data.get(0));                    //鲜花编号 要改的
//                            xianhuaEntity.setXianhuaPhoto("");//详情和图片
//                            xianhuaEntity.setXianhuaHuayu(data.get(0));                    //花语 要改的
//                            xianhuaEntity.setZanNumber(Integer.valueOf(data.get(0)));   //赞 要改的
//                            xianhuaEntity.setCaiNumber(Integer.valueOf(data.get(0)));   //踩 要改的
//                            xianhuaEntity.setXianhuaTypes(Integer.valueOf(data.get(0)));   //鲜花类型 要改的
//                            xianhuaEntity.setXianhuaKucunNumber(Integer.valueOf(data.get(0)));   //鲜花数量 要改的
//                            xianhuaEntity.setXianhuaOldMoney(data.get(0));                    //鲜花原价 要改的
//                            xianhuaEntity.setXianhuaNewMoney(data.get(0));                    //现价 要改的
//                            xianhuaEntity.setXianhuaClicknum(Integer.valueOf(data.get(0)));   //鲜花热度 要改的
//                            xianhuaEntity.setXianhuaContent("");//详情和图片
//                            xianhuaEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            xianhuaEntity.setXianhuaDelete(1);//逻辑删除字段
//                            xianhuaEntity.setInsertTime(date);//时间
//                            xianhuaEntity.setCreateTime(date);//时间
                            xianhuaList.add(xianhuaEntity);


                            //把要查询是否重复的字段放入map中
                                //鲜花编号
                                if(seachFields.containsKey("xianhuaUuidNumber")){
                                    List<String> xianhuaUuidNumber = seachFields.get("xianhuaUuidNumber");
                                    xianhuaUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> xianhuaUuidNumber = new ArrayList<>();
                                    xianhuaUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("xianhuaUuidNumber",xianhuaUuidNumber);
                                }
                        }

                        //查询是否重复
                         //鲜花编号
                        List<XianhuaEntity> xianhuaEntities_xianhuaUuidNumber = xianhuaService.selectList(new EntityWrapper<XianhuaEntity>().in("xianhua_uuid_number", seachFields.get("xianhuaUuidNumber")).eq("xianhua_delete", 1));
                        if(xianhuaEntities_xianhuaUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(XianhuaEntity s:xianhuaEntities_xianhuaUuidNumber){
                                repeatFields.add(s.getXianhuaUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [鲜花编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        xianhuaService.insertBatch(xianhuaList);
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
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<XianhuaView> returnXianhuaViewList = new ArrayList<>();

        //查询订单
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        params1.put("shangxiaTypes",1);
        params1.put("xianhuaYesnoTypes",2);
        PageUtils pageUtils = xianhuaOrderService.queryPage(params1);
        List<XianhuaOrderView> orderViewsList =(List<XianhuaOrderView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(XianhuaOrderView orderView:orderViewsList){
            Integer xianhuaTypes = orderView.getXianhuaTypes();
            if(typeMap.containsKey(xianhuaTypes)){
                typeMap.put(xianhuaTypes,typeMap.get(xianhuaTypes)+1);
            }else{
                typeMap.put(xianhuaTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("xianhuaTypes",type);
            params2.put("shangxiaTypes",1);
            params2.put("xianhuaYesnoTypes",2);
            PageUtils pageUtils1 = xianhuaService.queryPage(params2);
            List<XianhuaView> xianhuaViewList =(List<XianhuaView>)pageUtils1.getList();
            returnXianhuaViewList.addAll(xianhuaViewList);
            if(returnXianhuaViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        params.put("shangxiaTypes",1);
        params.put("xianhuaYesnoTypes",2);
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = xianhuaService.queryPage(params);
        if(returnXianhuaViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnXianhuaViewList.size();//要添加的数量
            List<XianhuaView> xianhuaViewList =(List<XianhuaView>)page.getList();
            for(XianhuaView xianhuaView:xianhuaViewList){
                Boolean addFlag = true;
                for(XianhuaView returnXianhuaView:returnXianhuaViewList){
                    if(returnXianhuaView.getId().intValue() ==xianhuaView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnXianhuaViewList.add(xianhuaView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnXianhuaViewList = returnXianhuaViewList.subList(0, limit);
        }

        for(XianhuaView c:returnXianhuaViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnXianhuaViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = xianhuaService.queryPage(params);

        //字典表数据转换
        List<XianhuaView> list =(List<XianhuaView>)page.getList();
        for(XianhuaView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        XianhuaEntity xianhua = xianhuaService.selectById(id);
            if(xianhua !=null){

                //点击数量加1
                xianhua.setXianhuaClicknum(xianhua.getXianhuaClicknum()+1);
                xianhuaService.updateById(xianhua);

                //entity转view
                XianhuaView view = new XianhuaView();
                BeanUtils.copyProperties( xianhua , view );//把实体数据重构到view中

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
    public R add(@RequestBody XianhuaEntity xianhua, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,xianhua:{}",this.getClass().getName(),xianhua.toString());
        Wrapper<XianhuaEntity> queryWrapper = new EntityWrapper<XianhuaEntity>()
            .eq("xianhua_name", xianhua.getXianhuaName())
            .eq("xianhua_uuid_number", xianhua.getXianhuaUuidNumber())
            .eq("xianhua_huayu", xianhua.getXianhuaHuayu())
            .eq("zan_number", xianhua.getZanNumber())
            .eq("cai_number", xianhua.getCaiNumber())
            .eq("xianhua_types", xianhua.getXianhuaTypes())
            .eq("xianhua_kucun_number", xianhua.getXianhuaKucunNumber())
            .eq("xianhua_clicknum", xianhua.getXianhuaClicknum())
            .eq("shangxia_types", xianhua.getShangxiaTypes())
            .eq("xianhua_delete", xianhua.getXianhuaDelete())
//            .notIn("xianhua_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XianhuaEntity xianhuaEntity = xianhuaService.selectOne(queryWrapper);
        if(xianhuaEntity==null){
                xianhua.setZanNumber(1);
                xianhua.setCaiNumber(1);
            xianhua.setXianhuaClicknum(1);
            xianhua.setXianhuaDelete(1);
            xianhua.setInsertTime(new Date());
            xianhua.setCreateTime(new Date());
        xianhuaService.insert(xianhua);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

