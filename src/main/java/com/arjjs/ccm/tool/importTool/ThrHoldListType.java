package com.arjjs.ccm.tool.importTool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.arjjs.ccm.common.utils.Collections3;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.sys.entity.Dict;
import com.arjjs.ccm.modules.sys.service.DictService;

public class ThrHoldListType {
	@Autowired
	private static DictService dictService;

    public ThrHoldListType() {
    }

    public static Object getValue(String val) {
    	Dict dict = new Dict();
    	dict.setType("ccm_three_case");
        List<Dict> fourHislist = new ArrayList<Dict>();
        List<Dict> allFourHisList = dictService.findList(dict);
        String[] var6;
        int var5 = (var6 = StringUtils.split(val, ",")).length;

        for(int var4 = 0; var4 < var5; ++var4) {
            String s = var6[var4];
            Iterator var8 = allFourHisList.iterator();

            while(var8.hasNext()) {
            	Dict e = (Dict)var8.next();
                if (StringUtils.trimToEmpty(s).equals(e.getLabel())) {
                	fourHislist.add(e);
                }
            }
        }

        return fourHislist.size() > 0 ? fourHislist : null;
    }

    public static String setValue(Object val) {
        if (val != null) {
            List<Dict> roleList = (List)val;
            return Collections3.extractToString(roleList, "value", ", ");
        } else {
            return "";
        }
    }
}
