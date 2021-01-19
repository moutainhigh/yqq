package com.yqq.framework.shiro.client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.bind.DatatypeConverter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.SerializationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.yqq.framework.model.TemplateIdMap;
import com.yqq.framework.shiro.model.AuthPermissionContext;

/**
 * 
 * <功能描述>
 * 
 * @author  libiao 工号
 * @see  [相关类/方法]
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class RemoteServiceImpl implements RemoteService {
    final static Logger logger = LoggerFactory.getLogger(RemoteServiceImpl.class);
    MultiValueMap<String, Object> headers;
    @Resource
    private SessionDAO sessionDAO;
    
    @Autowired
    private RestTemplate restTemplate;
    
    String globalAuthUrl;

    public RemoteServiceImpl(String globalAuthUrl) {
        this.globalAuthUrl = globalAuthUrl + "/center/auth/";
        this.headers = new LinkedMultiValueMap<>();
        this.headers.add("Accept", "application/json; charset=UTF-8");
        this.headers.add("Content-Type", "application/json");
    }

    @Override
    public Session getSession(String appKey, Serializable sessionId) {
        logger.debug("client:getSession:{}", appKey);
        Map<String,Object> param = new HashMap(2);
        param.put("sessionId", sessionId);
        param.put("appKey", appKey);
        HttpEntity<Map> request = new HttpEntity(param, this.headers);
        try {
            ResponseEntity<Map> responseEntity = restTemplate.postForEntity(globalAuthUrl + "get", request, Map.class);
            Object result = responseEntity.getBody().get("result");
            if (!StringUtils.isEmpty(result)) {
                try {
                    byte[] parseBase64Binary = DatatypeConverter.parseBase64Binary(result.toString());
                    Session session = (Session) SerializationUtils.deserialize(parseBase64Binary);
                    return session;
                }
                catch (Exception ex) {
                    logger.error("client:getSession 反序列化Session异常 ", ex);
                }
            }
        }
        catch (Exception ex) {
            logger.error("client:getSession 获取Session 请求异常 ", ex);
        }
        return null;
    }

    @Override
    public Serializable createSession(String appKey, Session session) {
        logger.debug("client:createSession:{}", appKey);
        Map param = new HashMap(2);
        byte[] sessionBytes = SerializationUtils.serialize(session);
        param.put("session", sessionBytes);
        param.put("appKey", appKey);
        HttpEntity<Map> request = new HttpEntity(param, this.headers);
        try {
            ResponseEntity<Map> responseEntity = restTemplate.postForEntity(globalAuthUrl + "create", request,
                    Map.class);
            Object result = responseEntity.getBody().get("result");
            return (Serializable) result;
        }
        catch (Exception ex) {
            logger.error("client:createSession 创建Session 请求异常 ", ex);
        }
        return null;
    }

    @Override
    public void updateSession(String appKey, Session session) {
        logger.debug("client:updateSession:{}", appKey);
        Map param = new HashMap(2);
        byte[] sessionBytes = SerializationUtils.serialize(session);
        param.put("session", sessionBytes);
        param.put("appKey", appKey);
        HttpEntity<Map> request = new HttpEntity(param, this.headers);
        try {
            restTemplate.postForEntity(globalAuthUrl + "update", request, String.class);
        }
        catch (Exception ex) {
            logger.error("client:updateSession 更新Session 请求异常 ", ex);
        }
    }

    @Override
    public void deleteSession(String appKey, Session session) {
        logger.debug("client:deleteSession:{}", appKey);
        Map param = new HashMap(2);
        byte[] sessionBytes = SerializationUtils.serialize(session);
        param.put("session", sessionBytes);
        param.put("appKey", appKey);
        HttpEntity<Map> request = new HttpEntity(param, this.headers);
        try {
            restTemplate.postForEntity(globalAuthUrl + "delete", request, String.class);
        }
        catch (Exception ex) {
            logger.error("client:deleteSession 删除Session 请求异常 ", ex);
        }
    }

    @Override
    public AuthPermissionContext getPermissions(String appKey, String username) {
        logger.debug("client:getPermissions:{}", appKey);
        PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
        byte[] bs = SerializationUtils.serialize(principalCollection);
        Map param = new HashMap(3);
        param.put("appKey", appKey);
        param.put("username", username);
        param.put("principals", bs);
        HttpEntity<Map> request = new HttpEntity(param, this.headers);
        try {
            ResponseEntity<AuthPermissionContext> responseEntity = restTemplate
                    .postForEntity(globalAuthUrl + "permissions", request, AuthPermissionContext.class);
            return responseEntity.getBody();
        }
        catch (Exception ex) {
            logger.error("client:getPermissions 获取用户权限 请求异常 ", ex);
        }
        return null;
    }

	@Override
	public String queryParamterListByAppNum(String appNum) {
		logger.debug("client:queryParamterListByAppNum:{}", appNum);
		Map param = new HashMap(1);
        param.put("appNum", appNum);
        HttpEntity<Map> request = new HttpEntity(param, this.headers);
        try {
            ResponseEntity<String> responseEntity = restTemplate
                    .postForEntity(globalAuthUrl + "queryParamterListByAppNum", request, String.class);
            return responseEntity.getBody();
        }
        catch (Exception ex) {	
            logger.error("client:queryParamterListByAppNum 获取系统参数 请求异常 ", ex);
        }
		return null;
	}

	@Override
	public String getApplicationByAppNum(String appNum) {
		logger.debug("client:getApplicationByAppNum:{}", appNum);
		Map param = new HashMap(1);
        param.put("appNum", appNum);
        HttpEntity<Map> request = new HttpEntity(param, this.headers);
        try {
            ResponseEntity<String> responseEntity = restTemplate
                    .postForEntity(globalAuthUrl + "getApplicationByAppNum", request, String.class);
            return responseEntity.getBody();
        }
        catch (Exception ex) {	
            logger.error("client:getApplicationByAppNum 获取应用信息 ", ex);
        }
		return null;
	}

	@Override
    public List<com.yqq.framework.model.Resource> getResourcesByAppNum(String appNum) {
        logger.debug("getResourcesByAppNum:{}",appNum);
        Map param = new HashMap(1);
        param.put("appNum", appNum);
        HttpEntity<Map> request = new HttpEntity(param, this.headers);
        try {

            ParameterizedTypeReference<List<com.yqq.framework.model.Resource>> typeRef = new ParameterizedTypeReference<List<com.yqq.framework.model.Resource>>() {
            };
            ResponseEntity<List<com.yqq.framework.model.Resource>> responseEntity =
                    restTemplate.exchange(globalAuthUrl + "getResourcesByAppNum",
                            HttpMethod.POST,request, typeRef);

            return responseEntity.getBody();
        }
        catch (Exception ex) {  
            logger.error("client:getApplicationByAppNum 获取应用信息 ", ex);
        }
        return null;
    }

    @Override
    public boolean isRoleAccess(String appNum, String uri , String currentRoleId) {
        logger.debug("isRoleAccess:appNum:{},uri:{} currentRoleId:{} ",appNum,uri, currentRoleId); 
        Map param = new HashMap();
        param.put("appNum", appNum);
        param.put("uri", uri);
        param.put("currentRoleId", currentRoleId);
        HttpEntity<Map> request = new HttpEntity(param, this.headers);
        try {
            ParameterizedTypeReference<Boolean> typeRef = new ParameterizedTypeReference<Boolean>() {};
            ResponseEntity<Boolean> responseEntity =
                    restTemplate.exchange(globalAuthUrl + "isRoleAccess", HttpMethod.POST,request,typeRef);
            return responseEntity.getBody();
        }
        catch (Exception ex) {
            logger.error("client:isRoleAccess 判断异常 ", ex);
        }
        return false;
    }

    @Override
    public TemplateIdMap getTemplateIdMap(String tenantNum, String trigerNum) { 
        Map param = new HashMap();
        param.put("tenantNum", tenantNum);
        param.put("trigerNum", trigerNum);
        HttpEntity<Map> request = new HttpEntity(param, this.headers);
        try {
            ParameterizedTypeReference<TemplateIdMap> typeRef = new ParameterizedTypeReference<TemplateIdMap>() {};
            ResponseEntity<TemplateIdMap> responseEntity =
                    restTemplate.exchange(globalAuthUrl + "getTemplateIdMap", HttpMethod.POST,request,typeRef);
            return responseEntity.getBody();
        }
        catch (Exception ex) {
            logger.error("client:getTemplateIdMap ", ex);
        }
        return null;
    }

}
