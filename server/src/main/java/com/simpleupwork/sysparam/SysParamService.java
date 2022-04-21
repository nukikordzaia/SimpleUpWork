package com.simpleupwork.sysparam;

import com.simpleupwork.model.sysparams.SystemParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.simpleupwork.repository.sysparam.SysParamRepository;
import com.simpleupwork.utils.ListResult;

import java.util.Optional;

@Service
public class SysParamService {

	@Autowired
	private SysParamRepository sysParamRepository;

	public Optional<SystemParameter> findById(Long id) {
		return sysParamRepository.findById(id);
	}

	public ListResult<SystemParameter> filterByAttributes(String code, String description, String value, int limit, int page) {
		return sysParamRepository.filterByAttributes(code, description, value, limit, page);
	}

	public SystemParameter save(SystemParameter systemParameter) {
		return sysParamRepository.save(systemParameter);
	}

	public void delete(Long id) {
		sysParamRepository.deleteById(id);
	}
}
