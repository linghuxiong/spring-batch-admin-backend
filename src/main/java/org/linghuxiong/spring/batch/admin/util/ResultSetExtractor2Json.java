package org.linghuxiong.spring.batch.admin.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @author linghuxiong
 * @date 2019/11/5 10:56 下午
 */
public class ResultSetExtractor2Json implements RowMapper<JSONObject> {

    @Override
    public JSONObject mapRow(ResultSet rs, int rowNum) throws SQLException {
        JSONObject jsonObject = new JSONObject();
        ResultSetMetaData rsd = rs.getMetaData();
        int length = rsd.getColumnCount();
        String columnName;
        for (int i = 0; i < length; i++) {
            columnName = rsd.getColumnLabel(i + 1);
            columnName = PropertyNamingStrategyCamel.toCamel(columnName.toLowerCase());
            jsonObject.put(columnName, rs.getObject(i + 1));
        }

        return jsonObject;
    }
}
