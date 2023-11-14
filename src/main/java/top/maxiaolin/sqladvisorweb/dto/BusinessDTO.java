package top.maxiaolin.sqladvisorweb.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author maxiaolin
 * @date 2023/11/2
 */
@Data
@ApiModel("sql请求体")
public class BusinessDTO {
	@ApiModelProperty(value = "主机地址", example = "127.0.0.1")
	private String host;
	@ApiModelProperty(value = "端口", example = "3306")
	private String port;
	@ApiModelProperty(value = "数据库用户名", example = "root")
	private String username;
	@ApiModelProperty(value = "数据库密码", example = "root")
	private String password;
	@ApiModelProperty(value = "数据库名称", example = "test")
	private String database;
	@ApiModelProperty(value = "需要分析的sql，多条使用;隔开，字符串参数使用单引号", example = "select name from a where a.name='1';select code from a where a.code='2'")
	private String sql;
	@ApiModelProperty(value = "1:output logs 0:output nothing", example = "1")
	private String verbose;
}
