package com.vcyber.user.controller;

import com.vcyber.user.entity.UserEntity;
import com.vcyber.user.jpa.UserJPA;
import com.vcyber.user.util.JWT;
import com.vcyber.user.util.ResponseData;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * Date：2018/2/2
 * Time：23:32
 * ========================
 */
@RestController
@RequestMapping("/vcyber")
public class UserController{
    //logback
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserJPA userJPA;

    /**
     * 注：@GetMapping("/{id}")是spring 4.3的新注解等价于：
     * @RequestMapping(value = "/id", method = RequestMethod.GET)
     * 类似的注解还有@PostMapping等等
     * http://127.0.0.1:8080/1    rest方式
     * @param id
     * @return user信息
     */
    @GetMapping("/{id}")
    public UserEntity findById(@PathVariable Long id) {
        UserEntity findOne = this.userJPA.findOne(id);
        return findOne;
    }
    /**
     * 添加、更新用户方法
     * @param entity
     * http://localhost:8080/save?name=admin&age=22&address=jinan3
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public UserEntity save(UserEntity entity)
    {
        return userJPA.save(entity);
    }

    @RequestMapping(value = "/list")
    public List<UserEntity> list()
    {
        logger.info("访问了list方法");
        return userJPA.findAll();
    }

    @RequestMapping(value = "/add")
    public String add()
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("测试");
        userEntity.setAddress("测试地址");
        userEntity.setAge(21);
        userJPA.save(userEntity);
        return "用户信息添加成功";
    }

    @RequestMapping(value = "/delete")
    public String delete(Long userId)
    {
        userJPA.delete(userId);
        return "用户信息删除成功";
    }

    @RequestMapping(value = "/age")
    public List<UserEntity> age(int uAge){
        return userJPA.nativeQuery(uAge);
    }


    /**
     * 根据条件自定义编写删除SQL
     * @return
     */
    @RequestMapping(value = "/deleteWhere")
    public String deleteWhere()
    {
        userJPA.deleteQuery("admin","123456");
        return "自定义SQL删除数据成功";
    }

    /**
     * 分页查询测试  127.0.0.1:8080/cutpage?page=1
     * @param page 传入页码，从1开始
     * @return
     */
    @RequestMapping(value = "/cutpage")
    public List<UserEntity> cutPage(int page)
    {
        UserEntity user = new UserEntity();
        user.setSize(2);
        user.setSord("desc");
        user.setPage(page);

        //获取排序对象
        Sort.Direction sort_direction = Sort.Direction.ASC.toString().equalsIgnoreCase(user.getSord()) ? Sort.Direction.ASC : Sort.Direction.DESC;
        //设置排序对象参数
        Sort sort = new Sort(sort_direction, user.getSidx());
        //创建分页对象
        PageRequest pageRequest = new PageRequest(user.getPage() - 1,user.getSize(),sort);
        //执行分页查询
        return userJPA.findAll(pageRequest).getContent();
    }


    /**
     * 模拟rest接口请求，
     * 参数可以放到header或者body里，
     * 返回ResponseData会自动解析成json格式的数据
     *  @ApiImplicitParam注解中的paramType 有五个可选值 ： path, query, body, header, form
     * @param
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value="模拟rest接口请求", notes="参数可以放到header或者body里,返回ResponseData会自动解析成json格式的数据",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "json", value = "json", required = true, dataType = "String",paramType = "body")
    })
    @ResponseBody
    public ResponseData login(@RequestHeader String username, @RequestHeader String password, @RequestBody String json) {

        ResponseData responseData = new ResponseData();
        if ("admin".equals(username) && "123456".equals(password)) {

            UserEntity user = new UserEntity();
            user.setId(new Long(2));
            user.setName(username);
            responseData.setCode("0000");
            responseData.setMessage("成功");
            responseData.putDataValue("user",user);
            String token = JWT.sign(user, 30L * 24L * 3600L * 1000L);
            if (token != null) {
                responseData.putDataValue("token",token);
            }
            return responseData;
        }
       return responseData;
    }


}
