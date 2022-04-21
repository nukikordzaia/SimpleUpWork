package com.simpleupwork.sysparam;

import com.simpleupwork.model.sysparams.SystemParameter;
import com.simpleupwork.utils.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/systemParameters")
public class SystemParameterController {

	@Autowired
	SysParamService sysParamService;

	@GetMapping("/all")
	public ResponseEntity<ListResult<SystemParameter>> filterByAttributes(@RequestParam(required = false) String code,
																		  @RequestParam(required = false) String description,
																		  @RequestParam(required = false) String value,
																		  @RequestParam(required = false) int limit,
																		  @RequestParam(required = false) int page) {
		return ResponseEntity.ok(sysParamService.filterByAttributes(code, description, value, limit, page));
	}

	@DeleteMapping(path = "/delete/{id}")
	public void delete(@PathVariable Long id) {
		sysParamService.delete(id);
	}

	@PostMapping("/create")
	public void create(@RequestBody SystemParameter parameter) {
		SystemParameter newParameter = new SystemParameter();
		newParameter.setParamType(parameter.getParamType());
		newParameter.setCode(parameter.getCode());
		newParameter.setDescription(parameter.getDescription());
		newParameter.setValue(parameter.getValue());
		sysParamService.save(newParameter);
	}

	@PutMapping("/update")
	public void update(@RequestBody SystemParameter parameter) {
		sysParamService.findById(parameter.getId())
			.map(paramInDB -> {
				paramInDB.setParamType(parameter.getParamType());
				paramInDB.setCode(parameter.getCode());
				paramInDB.setDescription(parameter.getDescription());
				paramInDB.setValue(parameter.getValue());
				return sysParamService.save(paramInDB);
			});
	}
}
