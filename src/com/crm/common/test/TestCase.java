package com.crm.common.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.crm.business.dao.CustomerDao;
import com.crm.business.dao.IAgreementDao;
import com.crm.business.service.IAgreementService;
import com.crm.common.PageModel;
import com.crm.common.utils.DateUtil;
import com.crm.common.utils.TreeUtil;
import com.crm.common.utils.WriteGridUtil;
import com.crm.model.Agreement;
import com.crm.model.AgreementDetail;
import com.crm.model.Customer;
import com.crm.model.Privilege;
import com.crm.model.Role;
import com.crm.model.User;
import com.crm.sys.service.IPrivilegeService;
import com.crm.sys.service.IRoleService;
import com.crm.sys.service.IUserService;




/**
 * 
 * 该测试为集成测试，非单元测试
 * 
 * Version: 1.0
 */
//@Ignore

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test_applicationContext.xml"
		,"classpath:test_dispatcher-servlet.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class TestCase {
    
    @Autowired
    @Qualifier("userService")
    private IUserService userService;
    
    @Autowired
    @Qualifier("privilegeService")
    private IPrivilegeService privilegeService;
    
    @Autowired
    @Qualifier("customerDao")
    private CustomerDao customerDao;
    
    @Autowired
    @Qualifier("agreementService")
    private IAgreementService agreementService;
    
    @Autowired
    @Qualifier("roleService")
    private IRoleService roleService;
//    @Test
	public void test1(){
		PageModel<User> list = userService.getList(0, 10);
		System.out.println(WriteGridUtil.writeGrid(list));
	}

//    @Test
    public void test2(){
    	String str = privilegeService.getPrivilegeTree();
    	System.out.println(str);
    }
    
//    @Test
	public void test3(){
    	String hql ="update Customer c set c.status = 1 , c.creater.id=6 where c.id = 10";
    	customerDao.update(hql);
    	 hql = "from Customer c where c.id=10";
    	Customer c = customerDao.getList(hql).get(0);
    	System.out.println(c.getCustomerName()+ "---"+c.getCreater().getId() + "-----"+c.getStatus());
	}
    
//    @Test
    public void test4(){
    	Agreement agreement = new Agreement();
    	agreement.setAmount(1111L);
    	agreement.setCdate(DateUtil.getFormatedCurrentDate());
    	agreement.setCustomerSigner("111");
    	agreement.setFinishDate(DateUtil.getFormatedCurrentDate());
    	agreement.setIsReceipt(1);
    	agreement.setIsValid(1);
    	agreement.setMySigner("3333");
    	agreement.setProjectName("2222");
    	agreement.setStatus(4);
    	AgreementDetail detail = new AgreementDetail();
    	detail.setIsValid(1);
    	detail.setValue1("1");
    	detail.setValue2("2");
    	detail.setValue3("1");
    	detail.setValue4("2");
    	agreement.setDetail(detail);
    	System.out.println(detail.getJson());
    }
    
    @Test
    public void test5(){
    	Role role = new Role();
    	role.setCount(1);
    	role.setName("1111");
    	roleService.add(role);
    	System.out.println(role.getJson());
    }
    
}


