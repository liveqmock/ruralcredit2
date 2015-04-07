package com.creditease.rc.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.SpaceDAO;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.Space;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.SpaceService;
import com.creditease.rc.util.CommonUtil;

@Service
public class SpaceServiceImpl implements SpaceService {

	@Resource
	private SpaceDAO spaceDAO;
	@Override
	public Pagination spaceList(Pagination pagination,Space space, String page, String rows,
			HttpServletRequest request, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("space", space);
		return spaceDAO.selectSpaceList(map, pagination);
	}
	@Override
	public Long saveSpace(Space space) {
		return spaceDAO.insert(space);
	}
	@Override
	public Message updateSpaceStud(String value) {
		Message message = new Message();
		if(CommonUtil.isNotEmpty(value)){
			int updateSpaceStud = spaceDAO.updateSpaceStud(value);
			if(updateSpaceStud>0){
				message.setSuccess(true);
				message.setMsg("延期成功");
			}else{
				message.setSuccess(false);
				message.setMsg("延期成功");
			}
		}else{
			message.setSuccess(false);
			message.setMsg("展业主键id为空");
		}
		return message;
	}
	@Override
	public Message saveExecuteSpace(Space space) {
		Message message = new Message();
		space.setAreadepartmentid(null);
		int saveExecuteSpace = spaceDAO.saveExecuteSpace(space);
		if(saveExecuteSpace>0){
			message.setSuccess(true);
			message.setMsg("保存成功,请不要操作等待导出excel表格");
		}else{
			message.setSuccess(false);
			message.setMsg("保存失败");
		}
		return message;
	}
	@Override
	public Pagination downloadExcel(Pagination pagination, Space space,
			String page, String rows, HttpServletRequest request,
			HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("space", space);
		return spaceDAO.downloadExcel(map, pagination);
	}
}
