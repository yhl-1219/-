package com.itheima.health.config;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 集成{@link ApiResponses}的一些通用{@link ApiResponse}，避免开发者重复编写相同代码
 *
 * @author wangweili
 */
@ApiResponses({
        @ApiResponse(code = 200, message = "响应成功"),
        @ApiResponse(code = 400, message = "请求参数没填好"),
        @ApiResponse(code = 404, message = "请求路径没有或页面跳转不对"),
        @ApiResponse(code = 500, message = "服务器内部出错")
})
public @interface Swagger2CommonConfiguration {
}