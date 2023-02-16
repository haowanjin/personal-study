package com.ddup.proxy;

/**
 * @author haowanjin
 * @date 2023/2/15 20:34
 * @description
 */
public class UserServiceImpl implements UserService {
    @Override
    public void doSth() {
        String str = """
                {
                		"teamCount": 2,
                		"teamMembers": [{
                			"checkUser": "id110323",
                			"checkUserName": "韩佳燕",
                			"belongOrgan": "00911",
                			"belongOrganName": "信息科技部"
                		}, {
                			"checkUser": "id300133",
                			"checkUserName": "袁慧芬",
                			"belongOrgan": "00911",
                			"belongOrganName": "信息科技部"
                		}, {
                			"checkUser": "wb961001 ",
                			"checkUserName": "钟淑萍",
                			"belongOrgan": "00911",
                			"belongOrganName": "信息科技部"
                		}]
                	}
                """;
        System.out.println(getClass().getName() + " doSth");
    }
}
