package com.xinmy.springbootbase.act;

import com.xinmy.springbootbase.act.vo.UserVo;
import com.xinmy.springbootbase.context.Context;
import com.xinmy.springbootbase.context.ContextHolder;
import com.xinmy.springbootbase.entity.User;
import com.xinmy.springbootbase.helper.Result;
import com.xinmy.springbootbase.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Api(value = "系统用户", description = "系统用户")
@RestController
@RequestMapping(value = "users")
public class UserAct extends CommonAct {
    private static final Logger LOG = LoggerFactory.getLogger("UserAct");
    @Autowired
    private UserService userService;


    //
    @ApiOperation(value = "分页查询系统用户", notes = "分页查询系统用户")
    @RequestMapping(method = RequestMethod.GET)
    @ApiImplicitParams({@ApiImplicitParam(name = "size", value = "分页大小", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "当前页面", paramType = "query")})
    public Result<Page<UserVo>> findByPage(@SortDefault(sort = "id", direction = Sort.Direction.DESC) final Pageable pageable, //
                                           final UserVo user) {
        //
        Page<User> page = userService.findByPage(ContextHolder.currentContext(), user, pageable);
        //
        return new Result(true, page.map(c -> new UserVo(c)));
    }

    @ApiOperation(value = "删除系统用户")
    @RequestMapping(value = "{id}", method = {RequestMethod.DELETE})
    public Result delete(@PathVariable final Long id) {
        Context context = ContextHolder.currentContext();
        try {
            userService.delete(context, id);
            return new Result(true, "成功删除系统用户", null);
        } catch (DataIntegrityViolationException e) {
            LOG.error("删除电表失败", e);
            return new Result(false, "删除系统用户失败", "该系统用户不能删除，存在其他关联数据");
        } catch (Exception e) {
            LOG.error("删除系统用户失败", e);
            return new Result(false, "删除系统用户失败", e.getMessage());
        }
    }

    @ApiOperation(value = "查询单个系统用户")
    @RequestMapping(value = "{id}", method = {RequestMethod.GET})
    public Result<UserVo> one(@PathVariable final Long id) {
        try {
            Context context = ContextHolder.currentContext();
            User user = userService.findOne(id);
            return new Result(true, new UserVo(user));
        } catch (Exception e) {
            LOG.error("查询单个系统用户失败", e);
            return new Result(false, new UserVo());
        }
    }

    @ApiOperation(value = "添加系统用户")
    @RequestMapping(method = {RequestMethod.POST})
    public Result add(@Valid @RequestBody final UserVo user, final BindingResult result) {
        Context context = ContextHolder.currentContext();
        try {
            if (result.hasErrors()) {
                Map<String, String> map = this.getErrors(result);
                String errorMsg = map.entrySet().iterator().next().getValue();
                return new Result(false, "保存系统用户失败", errorMsg, map);
            } else {
                User saved = userService.save(context, user.toUser());
                UserVo vo = new UserVo(saved);
                return new Result(true, "成功保存系统用户", null);
            }
        } catch (Exception e) {
            LOG.error("添加系统用户失败", e);
            return new Result(false, "保存系统用户失败", e.getMessage());
        }
    }
}
