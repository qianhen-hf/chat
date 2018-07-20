package com.fan.service;

import com.fan.mapper.AnchorMapper;
import com.fan.po.Anchor;
import com.fan.po.AnchorExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/7/17 9:19
 * @UpdateUser: hf
 * @UpdateDate: 2018/7/17 9:19
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */

@Service
public class AnchorService {
    @Autowired
    AnchorMapper anchorMapper;

    public Anchor selectAnchorByName(String name){
        AnchorExample anchorExample = new AnchorExample();
        AnchorExample.Criteria criteria = anchorExample.createCriteria();
        criteria.andAnchorNameEqualTo(name);
        List<Anchor> anchors = anchorMapper.selectByExample(anchorExample);
        if (anchors != null && !anchors.isEmpty()) {
            return anchors.get(0);
        }
        return null;
    }
}
