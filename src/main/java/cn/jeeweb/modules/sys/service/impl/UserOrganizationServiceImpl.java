package cn.jeeweb.modules.sys.service.impl;

import cn.jeeweb.core.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.sys.entity.UserOrganization;
import cn.jeeweb.modules.sys.service.IUserOrganizationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("userOrganizationService")
public class UserOrganizationServiceImpl extends CommonServiceImpl<UserOrganization>
		implements IUserOrganizationService {

}
