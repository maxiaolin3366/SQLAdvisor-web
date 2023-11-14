package top.maxiaolin.sqladvisorweb.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.maxiaolin.sqladvisorweb.dto.BusinessDTO;

import java.io.*;

/**
 * @author maxiaolin
 * @date 2023/11/2
 */
@RestController
@RequestMapping("/api")
@Slf4j
@Api(tags = "接口")
public class BusinessController {
	@PostMapping("advisor")
	@ApiOperation("验证")
	public String advisor(@RequestBody BusinessDTO businessDTO) throws IOException, InterruptedException {
		//./sqladvisor -h 127.0.0.1  -P 3306  -u root -p 'root' -d sttb_efficacy -q "select id from dictionary" -v 1
		String[] command = new String[]{"./sqladvisor", "-h", businessDTO.getHost(), "-P", businessDTO.getPort(),
				"-u", businessDTO.getUsername(), "-p", businessDTO.getPassword(), "-d", businessDTO.getDatabase(), "-q", businessDTO.getSql(), "-v", businessDTO.getVerbose()};
		log.info("执行命令：{}", command);
		Process process = Runtime.getRuntime().exec(command);

		// 处理命令的标准输出
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
		StringBuilder stringBuilder = new StringBuilder();
		String stdLine;
		while ((stdLine = stdInput.readLine()) != null) {
			stringBuilder.append(stdLine);
			stringBuilder.append("\n");
			log.info(stdLine);
		}

		// 处理命令的错误输出
		BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
		String errorLine;
		while ((errorLine = stdError.readLine()) != null) {
			stringBuilder.append(errorLine);
			stringBuilder.append("\n");
			log.error(errorLine);
		}
		int exitCode = process.waitFor();
		log.info("命令执行完毕，退出码：" + exitCode);
		return stringBuilder.toString();
	}
}
