package com.creditease.rc.vo;

import java.util.List;

import com.creditease.rc.domain.LocalReserveListDTO;
import com.creditease.rc.domain.LocalWebServiceResponse;
/**
 * 
 * @author haoqiang
 *
 */
public class LocalReserveDTOVo extends LocalWebServiceResponse {
	private List<LocalReserveListDTO> localReserveListDTOs;

	public List<LocalReserveListDTO> getLocalReserveListDTOs() {
		return localReserveListDTOs;
	}

	public void setLocalReserveListDTOs(List<LocalReserveListDTO> localReserveListDTOs) {
		this.localReserveListDTOs = localReserveListDTOs;
	}

}
